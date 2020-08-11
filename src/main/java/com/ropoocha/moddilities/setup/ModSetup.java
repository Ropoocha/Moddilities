package com.ropoocha.moddilities.setup;

import com.ropoocha.moddilities.entities.EntityWeirdMob;
import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.registries.RegistryContainer;
import com.ropoocha.moddilities.registries.RegistryEntity;
import com.ropoocha.moddilities.render.RenderWeirdMob;
import com.ropoocha.moddilities.screens.ScreenDiamondGenerator;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
    DeferredWorkQueue.runLater(() -> {
      GlobalEntityTypeAttributes.put(RegistryEntity.WEIRD_MOB_ENTITY.get(), MobEntity.func_233666_p_()
          .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1)
          .createMutableAttribute(Attributes.MAX_HEALTH, 2.0)
          .create());
    });
  }

  public static void clientSidedSetup(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(RegistryEntity.WEIRD_MOB_ENTITY.get(),
        RenderWeirdMob::new);

    DeferredWorkQueue.runLater(() -> {
      ScreenManager.registerFactory(RegistryContainer.DIAMOND_GENERATOR_CONTAINER.get(),
          ScreenDiamondGenerator::new);
    });
  }
}
