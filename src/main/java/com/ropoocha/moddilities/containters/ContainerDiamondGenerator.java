package com.ropoocha.moddilities.containters;

import com.ropoocha.moddilities.energy.SettableEnergyStorage;
import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.registries.RegistryContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ContainerDiamondGenerator extends Container {

  private final TileEntity tileEntity;
  private final World world;
  private final IItemHandler inventory;

  public ContainerDiamondGenerator(int windowId, BlockPos pos, World world,
      PlayerInventory inventory) {
    super(RegistryContainer.DIAMOND_GENERATOR_CONTAINER.get(), windowId);
    this.inventory = new InvWrapper(inventory);
    tileEntity = world.getTileEntity(pos);
    this.world = world;

    tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
      addSlot(new SlotItemHandler(h, 0, 80, 35));
    });
    layoutPlayerInventorySlots(8, 84);

    trackInt(new IntReferenceHolder() {
      @Override
      public int get() {
        return getEnergy();
      }

      @Override
      public void set(int value) {
        tileEntity.getCapability(CapabilityEnergy.ENERGY)
            .ifPresent(h -> ((SettableEnergyStorage) h).setEnergy(value));
      }
    });
  }

  public int getEnergy() {
    return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored)
        .orElse(0);
  }

  private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
    for (int i = 0; i < amount; i++) {
      addSlot(new SlotItemHandler(handler, index, x, y));
      x += dx;
      index++;
    }
    return index;
  }

  private int addSlotBox(IItemHandler handler, int index, int x, int y,
      int horizontalAmount, int dx, int verticalAmount, int dy) {
    for (int j = 0; j < verticalAmount; j++) {
      index = addSlotRange(handler, index, x, y, horizontalAmount, dx);
      y += dy;
    }
    return index;
  }

  private void layoutPlayerInventorySlots(int leftColumn, int topRow) {
    addSlotBox(inventory, 9, leftColumn, topRow, 9, 18, 3, 18);

    topRow += 58;
    addSlotRange(inventory, 0, leftColumn, topRow, 9, 18);
  }

  @Override
  public boolean canInteractWith(PlayerEntity playerIn) {
    return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()),
        playerIn, RegistryBlock.DIAMOND_GENERATOR_BLOCK.get());
  }

  @Override
  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
    ItemStack itemStack = ItemStack.EMPTY;
    Slot slot = this.inventorySlots.get(index);
    if (slot != null && slot.getHasStack()) {
      ItemStack stack = slot.getStack();
      itemStack = stack.copy();
      if (index == 0) {
        if (!this.mergeItemStack(stack, 1, 37, true)) {
          return ItemStack.EMPTY;
        }
        slot.onSlotChange(stack, itemStack);
      } else {
        if (stack.getItem() == Items.DIAMOND) {
          if (!this.mergeItemStack(stack, 0, 1, false)) {
            return ItemStack.EMPTY;
          }
        } else if (index < 28) {
          if (!this.mergeItemStack(stack, 28, 37, false)) {
            return ItemStack.EMPTY;
          }
        } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
          return ItemStack.EMPTY;
        }
      }

      if (stack.isEmpty()) {
        slot.putStack(ItemStack.EMPTY);
      } else {
        slot.onSlotChanged();
      }

      if (stack.getCount() == itemStack.getCount()) {
        return ItemStack.EMPTY;
      }

      slot.onTake(playerIn, stack);
    }

    return itemStack;
  }
}
