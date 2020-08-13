package com.ropoocha.moddilities.setup;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.geometries.GeometryMimic;
import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.registries.RegistryContainer;
import com.ropoocha.moddilities.registries.RegistryEntity;
import com.ropoocha.moddilities.render.RenderPLFlag;
import com.ropoocha.moddilities.screens.ScreenDiamondGenerator;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModSetup {

  public static final ItemGroup ITEM_GROUP = new ItemGroup("moddilities") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(RegistryBlock.DIAMOND_GENERATOR_BLOCK.get());
    }
  };

  public static void setup(final FMLCommonSetupEvent event) {
    DeferredWorkQueue.runLater(() -> {
      GlobalEntityTypeAttributes
          .put(RegistryEntity.POLISH_FLAG_ENTITY.get(), MobEntity.func_233666_p_()
              .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1)
              .createMutableAttribute(Attributes.MAX_HEALTH, 2.0)
              .create());
    });
  }

  public static void clientSidedSetup(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(RegistryEntity.POLISH_FLAG_ENTITY.get(),
        RenderPLFlag::new);

    DeferredWorkQueue.runLater(() -> {
      ScreenManager.registerFactory(RegistryContainer.DIAMOND_GENERATOR_CONTAINER.get(),
          ScreenDiamondGenerator::new);

      RenderTypeLookup.setRenderLayer(RegistryBlock.MIMIC_BLOCK.get(), renderType -> true);
    });
  }

  public static void modelLoaderHandler(ModelRegistryEvent event) {
    ModelLoaderRegistry.registerLoader(
        new ResourceLocation(Moddilities.MODID, "mimic_loader"),
        new GeometryMimic.Loader());
  }
}
