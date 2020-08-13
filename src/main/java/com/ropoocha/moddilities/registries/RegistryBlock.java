package com.ropoocha.moddilities.registries;

import com.mojang.brigadier.Command;
import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.blocks.BlockDiamondGenerator;
import com.ropoocha.moddilities.blocks.BlockMimic;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryBlock {

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, Moddilities.MODID);

  public static final RegistryObject<Block> DIAMOND_GENERATOR_BLOCK =
      BLOCKS.register("diamond_generator", BlockDiamondGenerator::new);

  public static final RegistryObject<Block> MIMIC_BLOCK =
      BLOCKS.register("mimic", BlockMimic::new);
}
