package com.ropoocha.moddilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DiamondGenerator extends Block {

  public DiamondGenerator() {
    super(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f));
  }
}
