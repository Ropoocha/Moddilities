package com.ropoocha.moddilities.registries;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;

public class BlockItemRegistry {

  public static void createBlockItems(final RegistryEvent.Register<Item> event) {
    BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
      final Item.Properties properties = new Item.Properties().group(ItemGroup.DECORATIONS);
      final BlockItem blockItem = new BlockItem(block, properties);
      if (block.getRegistryName() != null) {
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
      }
    });
  }
}
