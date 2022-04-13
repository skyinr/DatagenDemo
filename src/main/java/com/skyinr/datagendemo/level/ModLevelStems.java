package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.Registry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModLevelStems {
    public static final DeferredRegister<LevelStem> LEVEL_STEMS = DeferredRegister.createOptional(Registry.LEVEL_STEM_REGISTRY, DataGenDemo.MODID);
    public static final RegistryObject<LevelStem> LEVEL_STEM_DEMO = LEVEL_STEMS.register("level_stem_demo", () -> new LevelStem(
            ModDimensionTypes.DIMENSION_TYPE_DEMO.getHolder().get(),
            ModNoiseBasedChunkGenerators.getNoiseBasedChunkGenerator()
    ));


}
