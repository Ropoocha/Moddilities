package com.ropoocha.moddilities.data;

import com.ropoocha.moddilities.registries.RegistryBlock;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

  Block diamondGenerator = RegistryBlock.DIAMOND_GENERATOR_BLOCK.get();

  public LootTables(DataGenerator generator) {
    super(generator);
  }

  @Override
  protected void addTables() {
    lootTables.put(diamondGenerator,
        createStandardTable("diamond_generator", diamondGenerator));
  }
}
