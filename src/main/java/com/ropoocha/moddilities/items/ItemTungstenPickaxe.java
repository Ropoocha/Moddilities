package com.ropoocha.moddilities.items;

import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;

public class ItemTungstenPickaxe extends PickaxeItem {

  public ItemTungstenPickaxe() {
    super(ItemTier.DIAMOND, 1, -2.8F, (new Item.Properties()).group(ModSetup.ITEM_GROUP));
  }
}
