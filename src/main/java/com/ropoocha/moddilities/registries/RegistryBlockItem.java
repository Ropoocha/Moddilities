package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.setup.ModSetup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;

public class RegistryBlockItem {

  public static void createBlockItems(final RegistryEvent.Register<Item> event) {
    RegistryBlock.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
      final Item.Properties properties = new Item.Properties().group(ModSetup.ITEM_GROUP);
      final BlockItem blockItem = new BlockItem(block, properties);
      if (block.getRegistryName() != null) {
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
      }
    });
  }
}
