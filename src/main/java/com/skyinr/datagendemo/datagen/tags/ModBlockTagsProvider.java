package com.skyinr.datagendemo.datagen.tags;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DataGenDemo.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        addPickaxe(ModBlocks.BLOCK_DEMO.get());
        addAxe();
        addShovel();
        addHoe();
        addStoneTool(ModBlocks.BLOCK_DEMO.get());
        addIronTool();
        addDiamondTool();
    }

    protected final void addPickaxe(Block... blocks) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blocks);
    }

    protected final void addAxe(Block... blocks) {
        tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
    }

    protected final void addShovel(Block... blocks) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(blocks);
    }

    protected final void addHoe(Block... blocks) {
        tag(BlockTags.MINEABLE_WITH_HOE).add(blocks);
    }

    protected final void addStoneTool(Block... blocks) {
        tag(BlockTags.NEEDS_STONE_TOOL).add(blocks);
    }

    protected final void addIronTool(Block... blocks) {
        tag(BlockTags.NEEDS_IRON_TOOL).add(blocks);
    }

    protected final void addDiamondTool(Block... blocks) {
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(blocks);
    }


    @Override
    @NotNull
    public String getName() {
        return "BlockTagsGen:" + DataGenDemo.MODID;
    }


}
