package com.ropoocha.moddilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDiamondGenerator extends Block {

  public BlockDiamondGenerator() {
    super(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f));
  }
}
