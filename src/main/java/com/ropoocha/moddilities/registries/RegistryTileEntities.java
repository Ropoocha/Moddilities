package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.tileentities.TileDiamondGenerator;
import com.ropoocha.moddilities.tileentities.TileMimic;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryTileEntities {

  public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES =
      DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Moddilities.MODID);

  public static final RegistryObject<TileEntityType<TileDiamondGenerator>> DIAMOND_GENERATOR_TILE_ENTITY =
      TILE_ENTITY_TYPES.register(
          "diamond_generator",
          () -> TileEntityType.Builder
              .create(TileDiamondGenerator::new, RegistryBlock.DIAMOND_GENERATOR_BLOCK.get())
              .build(null));

  public static final RegistryObject<TileEntityType<TileMimic>> MIMIC_TILE_ENTITY =
      TILE_ENTITY_TYPES.register("mimic",
          () -> TileEntityType.Builder
              .create(TileMimic::new, RegistryBlock.MIMIC_BLOCK.get())
              .build(null));
}