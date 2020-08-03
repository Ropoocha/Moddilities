package com.ropoocha.moddilities;
import com.ropoocha.moddilities.registries.BlockItemRegistry;
import com.ropoocha.moddilities.registries.BlockRegistry;
import com.ropoocha.moddilities.registries.ItemRegistry;
import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Moddilities.MODID)
public class Moddilities {
  public static final String MODID = "moddilities";
  public static ModSetup setup = new ModSetup();

  public Moddilities(){

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    // General setup
    modBus.addListener(ModSetup::init);
    modBus.addGenericListener(Item.class, BlockItemRegistry::createBlockItems);

    // Register Deferred Registries
    BlockRegistry.BLOCKS.register(modBus);
    ItemRegistry.ITEMS.register(modBus);
  }
}
