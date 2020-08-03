package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.blocks.DiamondGenerator;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
  public static DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, Moddilities.MODID);

  public static RegistryObject<Block> DIAMOND_GENERATOR_BLOCK =
      BLOCKS.register("diamond_generator", DiamondGenerator::new);
}
