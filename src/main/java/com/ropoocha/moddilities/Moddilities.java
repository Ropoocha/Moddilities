package com.ropoocha.moddilities;

import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.registries.RegistryBlockItem;
import com.ropoocha.moddilities.registries.RegistryContainer;
import com.ropoocha.moddilities.registries.RegistryItem;
import com.ropoocha.moddilities.registries.RegistryTileEntities;
import com.ropoocha.moddilities.setup.ConfigHolder;
import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Moddilities.MODID)
public class Moddilities {

  public static final String MODID = "moddilities";

  public Moddilities() {

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    ModLoadingContext context = ModLoadingContext.get();

    // General setup
    modBus.addListener(ModSetup::setup);
    modBus.addListener(ModSetup::clientSidedSetup);
    modBus.addGenericListener(Item.class, RegistryBlockItem::createBlockItems);

    // Register Deferred Registries
    RegistryBlock.BLOCKS.register(modBus);
    RegistryItem.ITEMS.register(modBus);
    RegistryTileEntities.TILE_ENTITY_TYPES.register(modBus);
    RegistryContainer.CONTAINERS.register(modBus);

    // Register Configs
    context.registerConfig(Type.CLIENT, ConfigHolder.CLIENT_SPEC);
    context.registerConfig(Type.COMMON, ConfigHolder.COMMON_SPEC);
  }
}
