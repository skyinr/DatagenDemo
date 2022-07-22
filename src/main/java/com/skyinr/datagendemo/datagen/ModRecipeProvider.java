package com.skyinr.datagendemo.datagen;

import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        ShapedRecipeBuilder.shaped(Blocks.BREWING_STAND)
                .define('#', ItemTags.STONE_BRICKS)
                .pattern("###")
                .pattern("###")
                .pattern(" # ")
                .unlockedBy("s1",has(ItemTags.STONE_BRICKS))
                .save(pFinishedRecipeConsumer);
    }
}
