package com.skyinr.datagendemo.block;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DataGenDemo.MODID);

    public static final RegistryObject<Block> BLOCK_DEMO = BLOCKS.register("block_demo", () -> new Block(getDefaultProperties()));

    public static BlockBehaviour.Properties getDefaultProperties() {
        return BlockBehaviour.Properties.copy(Blocks.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0F);
    }

}
