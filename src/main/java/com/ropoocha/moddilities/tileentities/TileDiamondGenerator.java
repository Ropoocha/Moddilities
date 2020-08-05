package com.ropoocha.moddilities.tileentities;

import com.ropoocha.moddilities.containters.ContainerDiamondGenerator;
import com.ropoocha.moddilities.energy.SettableEnergyStorage;
import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.registries.RegistryTileEntities;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileDiamondGenerator extends TileEntity implements ITickableTileEntity,
    INamedContainerProvider {

  // Handlers
  private ItemStackHandler itemHandler = createItemHandler();
  private SettableEnergyStorage energyStorage = createEnergyStorage();

  // Capabilities
  private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
  private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

  private int counter;

  public TileDiamondGenerator() {
    super(RegistryTileEntities.DIAMOND_GENERATOR_TILE_ENTITY.get());
  }

  // TileEntity methods

  @Override
  public void read(BlockState state, CompoundNBT tag) {
    itemHandler.deserializeNBT(tag.getCompound("inv"));
    energyStorage.setEnergy(tag.getInt("energy"));

    super.read(state, tag);
  }

  @Override
  public CompoundNBT write(CompoundNBT tag) {
    tag.put("inv", itemHandler.serializeNBT());
    tag.putInt("energy", energyStorage.getEnergyStored());

    return super.write(tag);
  }

  // Capabilities suppliers

  private ItemStackHandler createItemHandler() {
    return new ItemStackHandler(1) {
      @Override
      public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stack.getItem() == Items.DIAMOND;
      }
    };
  }

  private SettableEnergyStorage createEnergyStorage() {
    return new SettableEnergyStorage(2137, 0);
  }

  // ICapabilityProvider method

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      return handler.cast();
    }
    if (cap == CapabilityEnergy.ENERGY) {
      return energy.cast();
    }
    return super.getCapability(cap, side);
  }

  // INamedContainer methods

  @Override
  public ITextComponent getDisplayName() {
    return new TranslationTextComponent(
        RegistryBlock.DIAMOND_GENERATOR_BLOCK.get().getTranslationKey());
  }

  @Nullable
  @Override
  public Container createMenu(int i, PlayerInventory inv, PlayerEntity player) {
    return new ContainerDiamondGenerator(i, pos, world, inv);
  }

  // ITickableTileEntity method

  @Override
  public void tick() {
    if (counter > 0) {
      counter--;
      if (counter <= 0) {
        energy.ifPresent(e -> ((SettableEnergyStorage) e).addEnergy(20));
      }
    } else {
      handler.ifPresent(h -> {
        ItemStack stack = h.getStackInSlot(0);
        if (stack.getItem() == Items.DIAMOND) {
          h.extractItem(0, 1, false);
          counter = 20;
        }
      });
    }
  }
}
