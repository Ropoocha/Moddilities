package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.items.ItemTungstenPickaxe;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryItem {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Moddilities.MODID);

  public static final RegistryObject<Item> TUNGSTEN_PICKAXE_ITEM =
      ITEMS.register("tungsten_pickaxe", ItemTungstenPickaxe::new);
}
