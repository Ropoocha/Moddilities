package com.ropoocha.moddilities.tileentities;

import com.ropoocha.moddilities.registries.RegistryTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileDiamondGenerator extends TileEntity implements ITickableTileEntity {

  public TileDiamondGenerator(final TileEntityType<?> type) {
    super(type);
  }

  public TileDiamondGenerator() {
    this(RegistryTileEntities.DIAMOND_GENERATOR_TILE_ENTITY.get());
  }

  @Override
  public void tick() {
    if (world.isRemote) {
      System.out.println("TileDiamondGenerator.tick");
    }
  }
}
