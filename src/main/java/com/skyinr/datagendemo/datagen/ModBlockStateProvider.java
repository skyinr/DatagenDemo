package com.skyinr.datagendemo.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.stream.Collectors;

public class ModBlockStateProvider extends BlockStateProvider {
    private final DeferredRegister<? extends Block> deferredRegister;
    private final Set<Block> skipBlocks = new HashSet<>();

    public ModBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper, DeferredRegister<? extends Block> deferredRegister) {
        super(gen, modid, exFileHelper);
        this.deferredRegister = deferredRegister;
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Block> blocks = getBlocks();
        blocks.removeAll(skipBlocks);

        registerBlock(blocks);
    }

    protected void skipBlock(Block... blocks) {
        Collections.addAll(skipBlocks, blocks);
    }

    protected void skipBlock(Collection<Block> blocks) {
        skipBlocks.addAll(blocks);
    }

    protected Set<Block> getBlocks() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    protected void registerBlock(Set<Block> blocks) {
        blocks.forEach(this::simpleBlock);
    }
}
