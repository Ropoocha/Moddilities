package com.ropoocha.moddilities.items;

import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.common.data.ForgeItemTagsProvider;

public class ItemTungstenPickaxe extends PickaxeItem {

  public ItemTungstenPickaxe() {
    super(ItemTier.DIAMOND, 1, -2.8F, (new Item.Properties()).group(ModSetup.ITEM_GROUP));
  }
}
