package com.skyinr.datagendemo.datagen;

import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.BLOCK_DEMO.get());

        //读者可以选择使用把某些方块抽象成一个接口来批量生成
        //如下例
        /*
        Collection<RegistryObject<Block>> entries = ModBlocks.BLOCKS.getEntries();
        entries.stream().map(RegistryObject::get)
                .filter(block -> block instanceof RotatedPillarBlock)
                .map(block -> (RotatedPillarBlock)block)
                .forEach(this::logBlock);
         */
    }

}
