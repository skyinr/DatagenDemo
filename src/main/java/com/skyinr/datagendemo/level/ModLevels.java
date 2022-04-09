package com.skyinr.datagendemo.level;

import com.mojang.serialization.Lifecycle;
import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import com.skyinr.datagendemo.datagen.ModWorldgenProvider;
import net.minecraft.core.*;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.OptionalLong;

public class ModLevels {
    private static final RegistryAccess registryaccess = RegistryAccess.builtinCopy();
    public static final ResourceKey<LevelStem> DATA_GEN_LEVEL_STEM_DEMO_KEY = ResourceKey.create(ModWorldgenProvider.levelStems.key(), DataGenDemo.modLoc("data_gen_level_stem_demo"));
    public static final ResourceKey<NoiseGeneratorSettings> DATA_GEN_LEVEL_DEMO_SETTINGS_KEY = ResourceKey.create(ModWorldgenProvider.noiseGeneratorSettings.key(), DataGenDemo.modLoc("data_gen_level_demo_settings"));
    public static final ResourceKey<DimensionType> DATA_GEN_DIMENSION_TYPE_KEY = ResourceKey.create(ModWorldgenProvider.dimensionTypes.key(), DataGenDemo.modLoc("data_gen_dimension_type"));
    public static final WritableRegistry<DimensionType> DIMENSION_TYPE_REGISTRY;

    public static final NoiseSettings DATA_GEN_LEVEL_DEMO_NOISE_SETTINGS = new NoiseSettings(
            -64,
            128,
            new NoiseSamplingSettings(1.0D, 3.0D, 80.0D, 60.0D),
            new NoiseSlider(0.9375D, 3, 0),
            new NoiseSlider(2.5D, 4, -1),
            1,
            2,
            new TerrainShaper(CubicSpline.constant(0.0F), CubicSpline.constant(0.0F), CubicSpline.constant(0.0F))
    );

    public static final NoiseGeneratorSettings NOISE_GENERATOR_SETTINGS_DEMO = new NoiseGeneratorSettings(DATA_GEN_LEVEL_DEMO_NOISE_SETTINGS,
            ModBlocks.BLOCK_DEMO.get().defaultBlockState(),
            Blocks.WATER.defaultBlockState(),
            overworldWithNewCaves(),
            SurfaceRuleData.overworld(),
            63,
            false,
            true,
            true,
            false
    );

    public static final DimensionType DIMENSION_TYPE_DEMO = DimensionType.create(
            OptionalLong.empty(),
            true,
            false,
            false,
            true,
            1.0D,
            false,
            true,
            false,
            true,
            true,
            -64,
            256,
            256,
            BlockTags.INFINIBURN_OVERWORLD,
            DimensionType.OVERWORLD_EFFECTS,
            0.0F
    );

    public static final LevelStem DATA_GEN_LEVEL_STEM_DEMO;

    public static final NoiseBasedChunkGenerator DATA_GEN_CHUNK_GENERATOR_DEMO;

    static {
        DIMENSION_TYPE_REGISTRY = RegistryAccess.builtinCopy().ownedWritableRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        DIMENSION_TYPE_REGISTRY.register(DATA_GEN_DIMENSION_TYPE_KEY, DIMENSION_TYPE_DEMO, Lifecycle.experimental());
        BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, DATA_GEN_LEVEL_DEMO_SETTINGS_KEY, NOISE_GENERATOR_SETTINGS_DEMO);
        DATA_GEN_CHUNK_GENERATOR_DEMO = new NoiseBasedChunkGenerator(
                registryaccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                registryaccess.registryOrThrow(Registry.NOISE_REGISTRY),
                new FixedBiomeSource(Holder.direct(ModBiomes.DEMO_BIOME.get())),
                59L,
                BuiltinRegistries.NOISE_GENERATOR_SETTINGS.getOrCreateHolder(DATA_GEN_LEVEL_DEMO_SETTINGS_KEY)
        );
        DATA_GEN_LEVEL_STEM_DEMO = new LevelStem(
                registryaccess.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY)
                        .getOrCreateHolder(DATA_GEN_DIMENSION_TYPE_KEY),
                DATA_GEN_CHUNK_GENERATOR_DEMO
        );
    }



    // NoiseRouterData的拷贝开始
    private static NoiseRouterWithOnlyNoises overworldWithNewCaves() {
        DensityFunction densityfunction = DensityFunctions.noise(getNoise(Noises.AQUIFER_BARRIER), 0.5D);
        DensityFunction densityfunction1 = DensityFunctions.noise(getNoise(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D);
        DensityFunction densityfunction2 = DensityFunctions.noise(getNoise(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D);
        DensityFunction densityfunction3 = DensityFunctions.noise(getNoise(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = DensityFunctions.zero();
        DensityFunction densityfunction5 = DensityFunctions.zero();
        DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, getNoise(Noises.TEMPERATURE_LARGE));
        DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, getNoise(Noises.VEGETATION_LARGE));
        DensityFunction densityfunction10 = DensityFunctions.zero();
        DensityFunction densityfunction14 = DensityFunctions.min(DensityFunctions.zero(), DensityFunctions.zero());
        DensityFunction densityfunction16 = DensityFunctions.zero();
        DensityFunction densityfunction17 = DensityFunctions.zero();
        DensityFunction densityfunction18 = DensityFunctions.zero();
        DensityFunction densityfunction19 = DensityFunctions.add(DensityFunctions.constant(-0.08F), DensityFunctions.max(densityfunction17, densityfunction18));
        DensityFunction densityfunction20 = DensityFunctions.noise(getNoise(Noises.ORE_GAP));
        return new NoiseRouterWithOnlyNoises(densityfunction, densityfunction1, densityfunction2, densityfunction3, densityfunction6, densityfunction7, DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), densityfunction10, densityfunction14, densityfunction16, densityfunction19, densityfunction20);
    }

    private static Holder<NormalNoise.NoiseParameters> getNoise(ResourceKey<NormalNoise.NoiseParameters> p_209543_) {
        return BuiltinRegistries.NOISE.getHolderOrThrow(p_209543_);
    }
    //NoiseRouterData的拷贝结束
}
