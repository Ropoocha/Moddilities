package com.ropoocha.moddilities;

import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.registries.RegistryBlockItem;
import com.ropoocha.moddilities.registries.RegistryItem;
import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Moddilities.MODID)
public class Moddilities {

  public static final String MODID = "moddilities";

  public Moddilities() {

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    // General setup
    modBus.addListener(ModSetup::init);
    modBus.addGenericListener(Item.class, RegistryBlockItem::createBlockItems);

    // Register Deferred Registries
    RegistryBlock.BLOCKS.register(modBus);
    RegistryItem.ITEMS.register(modBus);
  }
}
