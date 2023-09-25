package com.skyinr.datagendemo.datagen;

import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);

    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.BLOCK_DEMO.get());

    }

}




