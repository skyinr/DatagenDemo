package com.skyinr.datagendemo.datagen.loottable;

import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootProvider extends BlockLoot {
    private final String modID;
    private final Set<Block> skipBlocks = new HashSet<>();

    public ModBlockLootProvider(String modid, Block... blocks) {
        this.modID = modid;
        skip(blocks);
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
    protected void addTables() {
        dropSelfWithContents(Registry.BLOCK.stream()
                .filter(block -> modID.equals(Registry.BLOCK.getKey(block).getNamespace()))
                .collect(Collectors.toSet()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return skipBlocks;
    }
}
