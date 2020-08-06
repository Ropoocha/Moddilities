package com.ropoocha.moddilities.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGenerators {
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    generator.addProvider(new Recipes(generator));
    generator.addProvider(new LootTables(generator));
  }

}
