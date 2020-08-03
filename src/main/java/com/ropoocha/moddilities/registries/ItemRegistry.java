package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
  public static DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Moddilities.MODID);


}
