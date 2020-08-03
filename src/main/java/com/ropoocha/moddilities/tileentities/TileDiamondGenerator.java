package com.ropoocha.moddilities.tileentities;

import com.ropoocha.moddilities.registries.RegistryTileEntities;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileDiamondGenerator extends TileEntity implements ITickableTileEntity {

  private final LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

  public TileDiamondGenerator() {
    super(RegistryTileEntities.DIAMOND_GENERATOR_TILE_ENTITY.get());
  }

  @Override
  public void tick() {
  }

  @Override
  public void read(BlockState state, CompoundNBT tag) {
    CompoundNBT invTag = tag.getCompound("inv");
    handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
    super.read(state, tag);
  }

  @Override
  public CompoundNBT write(CompoundNBT tag) {
    handler.ifPresent(h -> {
      CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
      tag.put("inv", compound);
    });
    return super.write(tag);
  }

  private IItemHandler createHandler() {
    return new ItemStackHandler(1) {
      @Override
      public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stack.getItem() == Items.DIAMOND;
      }
    };
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, handler);
  }
}
