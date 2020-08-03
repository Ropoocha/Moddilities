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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileDiamondGenerator extends TileEntity implements ITickableTileEntity {

  private ItemStackHandler handler;

  public TileDiamondGenerator(final TileEntityType<?> type) {
    super(type);
  }

  public TileDiamondGenerator() {
    this(RegistryTileEntities.DIAMOND_GENERATOR_TILE_ENTITY.get());
  }

  @Override
  public void tick() {
  }

  @Override
  public void read(BlockState state, CompoundNBT tag) {
    CompoundNBT invTag = tag.getCompound("inv");
    getHandler().deserializeNBT(invTag);
    super.read(state, tag);
  }

  @Override
  public CompoundNBT write(CompoundNBT tag) {
    CompoundNBT compound = getHandler().serializeNBT();
    tag.put("inv", compound);
    return super.write(tag);
  }

  private ItemStackHandler getHandler() {
    if (handler == null) {
      handler = new ItemStackHandler(1) {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
          return stack.getItem() == Items.DIAMOND;
        }
      };
    }
    return handler;
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      return LazyOptional.of(() -> (T) getHandler());
    }
    return super.getCapability(cap, side);
  }
}
