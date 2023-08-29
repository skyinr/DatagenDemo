package com.skyinr.datagendemo.datagen.loottable;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootProvider extends BlockLootSubProvider {
    private final Set<Block> skipBlocks = new HashSet<>();

    public ModBlockLootProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void add(Block pBlock, LootTable.Builder pLootTableBuilder) {
        super.add(pBlock, pLootTableBuilder);
        skipBlocks.add(pBlock);
    }

    protected void skip(Block... blocks) {
        Collections.addAll(skipBlocks, blocks);
    }

    protected void skip(Collection<Block> blocks) {
        skipBlocks.addAll(blocks);
    }

    protected void dropSelfWithContents(Set<Block> blocks) {
        for (Block block : blocks) {
            if (skipBlocks.contains(block)) {
                continue;
            }
            add(block, createSingleItemTable(block));
        }
    }

    @Override
    protected void generate() {
        dropSelfWithContents(ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> DataGenDemo.MODID.equals(ForgeRegistries.BLOCKS.getKey(block).getNamespace()))
                .collect(Collectors.toSet()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return skipBlocks;
    }
}
