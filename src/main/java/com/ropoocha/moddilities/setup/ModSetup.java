package com.ropoocha.moddilities.setup;

import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.registries.RegistryContainer;
import com.ropoocha.moddilities.screens.ScreenDiamondGenerator;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

  public static final ItemGroup ITEM_GROUP = new ItemGroup("moddilities") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(RegistryBlock.DIAMOND_GENERATOR_BLOCK.get());
    }
  };

  public static void setup(final FMLCommonSetupEvent event) {

  }

  public static void clientSidedSetup(FMLClientSetupEvent event) {
    DeferredWorkQueue.runLater(() -> {
      ScreenManager.registerFactory(RegistryContainer.DIAMOND_GENERATOR_CONTAINER.get(), ScreenDiamondGenerator::new);
    });
  }
}
