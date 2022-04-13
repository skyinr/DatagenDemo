package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModNoiseGeneratorSettings {
    public static final DeferredRegister<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS = DeferredRegister.createOptional(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, DataGenDemo.MODID);

    public static final RegistryObject<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS_DEMO = NOISE_GENERATOR_SETTINGS.register("noise_generator_settings_demo", () -> new NoiseGeneratorSettings(
            NoiseSettings.create(-64, 384, new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-0.078125D, 2, 0), new NoiseSlider(0.4D, 3, 0), 1, 2, TerrainProvider.overworld(true)),
            ModBlocks.BLOCK_DEMO.get().defaultBlockState(),
            Blocks.WATER.defaultBlockState(),
            overworldWithNewCaves(MODNoiseSettings.getNoiseSettings()),
            SurfaceRuleData.overworld(),
            63,
            false,
            true,
            true,
            false)
    );

    //NoiseRouterData的拷贝开始
    private static NoiseRouterWithOnlyNoises overworldWithNewCaves(NoiseSettings p_212283_) {
        DensityFunction densityfunction = DensityFunctions.noise(getNoise(Noises.AQUIFER_BARRIER), 0.5D);
        DensityFunction densityfunction1 = DensityFunctions.noise(getNoise(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D);
        DensityFunction densityfunction2 = DensityFunctions.noise(getNoise(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D);
        DensityFunction densityfunction3 = DensityFunctions.noise(getNoise(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = getFunction();
        DensityFunction densityfunction5 = getFunction();
        DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, getNoise(Noises.TEMPERATURE_LARGE));
        DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, getNoise(Noises.VEGETATION_LARGE));
        DensityFunction densityfunction8 = getFunction();
        DensityFunction densityfunction9 = getFunction();
        DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
        DensityFunction densityfunction11 = getFunction();
        DensityFunction densityfunction12 = DensityFunctions.min(densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0D), getFunction()));
        DensityFunction densityfunction13 = DensityFunctions.rangeChoice(densityfunction11, -1000000.0D, 1.5625D, densityfunction12, underground(densityfunction11));
        DensityFunction densityfunction14 = DensityFunctions.min(postProcess(p_212283_, densityfunction13), getFunction());
        DensityFunction densityfunction15 = getFunction();
        int j = -64;
        int k = 368;
        DensityFunction densityfunction16 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(getNoise(Noises.ORE_VEININESS), 1.5D, 1.5D), j, k);
        DensityFunction densityfunction17 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(getNoise(Noises.ORE_VEIN_A), 4.0D, 4.0D), j, k).abs();
        DensityFunction densityfunction18 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(getNoise(Noises.ORE_VEIN_B), 4.0D, 4.0D), j, k).abs();
        DensityFunction densityfunction19 = DensityFunctions.add(DensityFunctions.constant((double) -0.08F), DensityFunctions.max(densityfunction17, densityfunction18));
        DensityFunction densityfunction20 = DensityFunctions.noise(getNoise(Noises.ORE_GAP));
        return new NoiseRouterWithOnlyNoises(densityfunction, densityfunction1, densityfunction2, densityfunction3, densityfunction6, densityfunction7, getFunction(), getFunction(), getFunction(), getFunction(), densityfunction10, densityfunction14, densityfunction16, densityfunction19, densityfunction20);
    }

    private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_) {
        return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, p_209474_, p_209475_ + 1, p_209473_, DensityFunctions.constant(0)));
    }
    private static DensityFunction underground(DensityFunction p_209470_) {
        DensityFunction densityfunction = getFunction();
        DensityFunction densityfunction1 = getFunction();
        DensityFunction densityfunction2 = DensityFunctions.noise(getNoise(Noises.CAVE_LAYER), 8.0D);
        DensityFunction densityfunction3 = DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction2.square());
        DensityFunction densityfunction4 = DensityFunctions.noise(getNoise(Noises.CAVE_CHEESE), 0.6666666666666666D);
        DensityFunction densityfunction5 = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.27D), densityfunction4).clamp(-1.0D, 1.0D), DensityFunctions.add(DensityFunctions.constant(1.5D), DensityFunctions.mul(DensityFunctions.constant(-0.64D), p_209470_)).clamp(0.0D, 0.5D));
        DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
        DensityFunction densityfunction7 = DensityFunctions.min(DensityFunctions.min(densityfunction6, getFunction()), DensityFunctions.add(densityfunction, densityfunction1));
        DensityFunction densityfunction8 = getFunction();
        DensityFunction densityfunction9 = DensityFunctions.rangeChoice(densityfunction8, -1000000.0D, 0.03D, DensityFunctions.constant(-1000000.0D), densityfunction8);
        return DensityFunctions.max(densityfunction7, densityfunction9);
    }

    private static Holder<NormalNoise.NoiseParameters> getNoise(ResourceKey<NormalNoise.NoiseParameters> p_209543_) {
        return BuiltinRegistries.NOISE.getHolderOrThrow(p_209543_);
    }

    private static DensityFunction getFunction() {
        return DensityFunctions.zero();
    }

    private static DensityFunction noiseGradientDensity(DensityFunction p_212272_, DensityFunction p_212273_) {
        DensityFunction densityfunction = DensityFunctions.mul(p_212273_, p_212272_);
        return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction.quarterNegative());
    }

    private static DensityFunction postProcess(NoiseSettings p_212275_, DensityFunction p_212276_) {
        DensityFunction densityfunction = DensityFunctions.slide(p_212275_, p_212276_);
        DensityFunction densityfunction1 = DensityFunctions.blendDensity(densityfunction);
        return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction1), DensityFunctions.constant(0.64D)).squeeze();
    }
    //NoiseRouterData的拷贝结束

    private static class MODNoiseSettings {

        public static NoiseSettings getNoiseSettings() {
            return NoiseSettings.create(
                    -64,
                    128,
                    new NoiseSamplingSettings(1.0D, 3.0D, 80.0D, 60.0D),
                    new NoiseSlider(0.9375D, 3, 0),
                    new NoiseSlider(2.5D, 4, -1),
                    1,
                    2,
                    new TerrainShaper(CubicSpline.constant(0.0F), CubicSpline.constant(0.0F), CubicSpline.constant(0.0F)));
        }

        private MODNoiseSettings() {

        }
    }
}
