package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.items.ItemModdedSpawnEgg;
import com.ropoocha.moddilities.items.ItemTungstenPickaxe;
import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryItem {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Moddilities.MODID);

  public static final RegistryObject<Item> TUNGSTEN_PICKAXE_ITEM =
      ITEMS.register("tungsten_pickaxe", ItemTungstenPickaxe::new);

  public static final RegistryObject<ItemModdedSpawnEgg> WEIRD_MOD_SPAWN_EGG =
      ITEMS.register("weird_mob_spawn_egg",
          () -> new ItemModdedSpawnEgg(
              RegistryEntity.WEIRD_MOB_ENTITY,
              0xE9E8E7,
              0xD4213D,
              new Item.Properties().group(ModSetup.ITEM_GROUP)));
}
