package com.ropoocha.moddilities.data;

import java.util.function.Consumer;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import com.ropoocha.moddilities.registries.RegistryBlock;
import net.minecraftforge.common.Tags;

public class Recipes extends RecipeProvider {

  public Recipes(DataGenerator generatorIn) {
    super(generatorIn);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    ShapedRecipeBuilder.shapedRecipe(RegistryBlock.DIAMOND_GENERATOR_BLOCK.get())
        .patternLine("###")
        .patternLine("#@#")
        .patternLine("###")
        .key('#', Tags.Items.INGOTS_GOLD)
        .key('@', Tags.Items.GEMS_DIAMOND)
        .setGroup("moddilities")
        .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
        .build(consumer);
  }
}
