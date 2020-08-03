package com.ropoocha.moddilities.setup;

import com.ropoocha.moddilities.registries.BlockRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

  public static final ItemGroup ITEM_GROUP = new ItemGroup("moddilities") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(BlockRegistry.DIAMOND_GENERATOR_BLOCK.get());
    }
  };

  public static void init(final FMLCommonSetupEvent event) {

  }
}
