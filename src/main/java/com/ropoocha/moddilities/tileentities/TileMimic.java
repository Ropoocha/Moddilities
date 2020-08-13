package com.ropoocha.moddilities.tileentities;

import com.ropoocha.moddilities.registries.RegistryTileEntities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileMimic extends TileEntity {

  public TileMimic() {
    super(RegistryTileEntities.MIMIC_TILE_ENTITY.get());
  }
}
