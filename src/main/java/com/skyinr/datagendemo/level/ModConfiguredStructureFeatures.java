package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.HashMap;

public class ModConfiguredStructureFeatures {
    public static final DeferredRegister<ConfiguredStructureFeature<?, ?>> FEATURES = DeferredRegister.createOptional(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, DataGenDemo.MODID);

//    public static final RegistryObject<ConfiguredStructureFeature<?, ?>> FEATURE_DEMO = FEATURES.register("feature_demo", () -> new ConfiguredStructureFeature(
//            StructureFeature.MINESHAFT,
//            new OreConfiguration(Collections.singletonList(OreConfiguration.target(new BlockStateMatchTest(ModBlocks.BLOCK_DEMO.get().defaultBlockState()), ModBlocks.BLOCK_DEMO.get().defaultBlockState())),5),
//            HolderSet.direct(ModBiomes.BIOME_DEMO.getHolder().get()),
//            true,
//            new HashMap<>(Collections.singletonMap(ModBiomes.BIOME_DEMO.getHolder().get(), 1))
//    )
//    );

}
