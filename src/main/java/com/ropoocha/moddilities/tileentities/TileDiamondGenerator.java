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
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileDiamondGenerator extends TileEntity implements ITickableTileEntity,
    INamedContainerProvider {

  // Capabilities
  private final LazyOptional<IItemHandler> itemHander = LazyOptional.of(this::createItemHandler);
  private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(this::createEnergyHandler);

  private int counter;

  public TileDiamondGenerator() {
    super(RegistryTileEntities.DIAMOND_GENERATOR_TILE_ENTITY.get());
  }

  // TileEntity methods

  @Override
  public void read(BlockState state, CompoundNBT tag) {
    CompoundNBT invTag = tag.getCompound("inv");
    itemHander.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
    energyHandler.ifPresent(h -> ((SettableEnergyStorage) h).setEnergy(invTag.getInt("energy")));
    super.read(state, tag);
  }

  @Override
  public CompoundNBT write(CompoundNBT tag) {
    itemHander.ifPresent(h -> {
      CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
      tag.put("inv", compound);
    });
    energyHandler.ifPresent(h -> tag.putInt("energy", h.getEnergyStored()));
    return super.write(tag);
  }

  // Capabilities suppliers

  private IItemHandler createItemHandler() {
    return new ItemStackHandler(1) {
      @Override
      public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stack.getItem() == Items.DIAMOND;
      }
    };
  }

  private IEnergyStorage createEnergyHandler() {
    return new SettableEnergyStorage(2137, 0);
  }

  // ICapabilityProvider method

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      return itemHander.cast();
    }
    if (cap == CapabilityEnergy.ENERGY) {
      return energyHandler.cast();
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
        energyHandler.ifPresent(e -> ((SettableEnergyStorage) e).addEnergy(20));
      }
    } else {
      itemHander.ifPresent(h -> {
        ItemStack stack = h.getStackInSlot(0);
        if (stack.getItem() == Items.DIAMOND) {
          h.extractItem(0, 1, false);
          counter = 20;
        }
      });
    }
  }
}
