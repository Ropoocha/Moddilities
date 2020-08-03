package com.ropoocha.moddilities.items;

import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.item.Item;

public class ItemTungstenPickaxe extends Item {

  public ItemTungstenPickaxe() {
    super(new Item.Properties()
        .maxStackSize(1)
        .group(ModSetup.ITEM_GROUP));
  }
}
