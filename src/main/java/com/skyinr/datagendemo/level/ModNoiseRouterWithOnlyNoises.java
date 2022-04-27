package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.datagen.ModWorldgenProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoiseRouterWithOnlyNoises {
    private static final DensityFunction SHIFT_X = getFunction(ModDensityFunction.SHIFT_X);
    private static final DensityFunction SHIFT_Z = getFunction(ModDensityFunction.SHIFT_Z);
    private static final DensityFunction FACTOR = getFunction(ModDensityFunction.FACTOR);
    private static final DensityFunction DEPTH = getFunction(ModDensityFunction.DEPTH);
    private static final DensityFunction SLOPED_CHEESE = getFunction(ModDensityFunction.SLOPED_CHEESE);
    public static final NoiseRouterWithOnlyNoises NOISE_ROUTER_WITH_ONLY_NOISES_DEMO = new NoiseRouterWithOnlyNoises(
            DensityFunctions.zero(),
            DensityFunctions.zero(),
            DensityFunctions.zero(),
            DensityFunctions.zero(),
            DensityFunctions.shiftedNoise2d(SHIFT_X, SHIFT_Z, 0.25D, getNoise(Noises.TEMPERATURE)),
            DensityFunctions.shiftedNoise2d(SHIFT_X, SHIFT_Z, 0.25D, getNoise(Noises.VEGETATION)),
            getFunction(ModDensityFunction.CONTINENTS),
            getFunction(ModDensityFunction.EROSION),
            DEPTH,
            getFunction(ModDensityFunction.RIDGES),
            ModDensityFunction.noiseGradientDensity(DensityFunctions.cache2d(FACTOR), DEPTH),
            ModDensityFunction.postProcess(ModNoiseSettings.NOISE_SETTINGS_DEMO, SLOPED_CHEESE),
            DensityFunctions.zero(),
            DensityFunctions.zero(),
            DensityFunctions.zero()
    );

    //NoiseRouterData
    public static Holder<NormalNoise.NoiseParameters> getNoise(ResourceKey<NormalNoise.NoiseParameters> key) {
        return ModNoisesParameters.NOISE_PARAMETERS_REGISTRY.getHolderOrThrow(key);
    }

    public static DensityFunction getFunction(ResourceKey<DensityFunction> key) {
        return new DensityFunctions.HolderHolder(ModDensityFunction.DENSITY_FUNCTION_REGISTRY.getHolderOrThrow(key));
    }

    protected class ModDensityFunction {
        public static final WritableRegistry<DensityFunction> DENSITY_FUNCTION_REGISTRY = ModWorldgenProvider.registrable.ownedWritableRegistryOrThrow(Registry.DENSITY_FUNCTION_REGISTRY);
        //DensityFunction copy start
        private static final DensityFunction BLENDING_FACTOR = DensityFunctions.constant(10.0D);
        private static final DensityFunction BLENDING_JAGGEDNESS = DensityFunctions.zero();
        private static final ResourceKey<DensityFunction> ZERO = createKey("zero");
        private static final ResourceKey<DensityFunction> Y = createKey("y");
        private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
        private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
        private static final ResourceKey<DensityFunction> BASE_3D_NOISE = createKey("overworld/base_3d_noise");
        private static final ResourceKey<DensityFunction> CONTINENTS = createKey("overworld/continents");
        private static final ResourceKey<DensityFunction> EROSION = createKey("overworld/erosion");
        private static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");
        private static final ResourceKey<DensityFunction> FACTOR = createKey("overworld/factor");
        private static final ResourceKey<DensityFunction> DEPTH = createKey("overworld/depth");
        private static final ResourceKey<DensityFunction> SLOPED_CHEESE = createKey("overworld/sloped_cheese");
        private static final ResourceKey<DensityFunction> CONTINENTS_LARGE = createKey("overworld_large_biomes/continents");
        private static final ResourceKey<DensityFunction> EROSION_LARGE = createKey("overworld_large_biomes/erosion");
        private static final ResourceKey<DensityFunction> FACTOR_LARGE = createKey("overworld_large_biomes/factor");
        private static final ResourceKey<DensityFunction> DEPTH_LARGE = createKey("overworld_large_biomes/depth");
        private static final ResourceKey<DensityFunction> SLOPED_CHEESE_LARGE = createKey("overworld_large_biomes/sloped_cheese");
        private static final ResourceKey<DensityFunction> SLOPED_CHEESE_END = createKey("end/sloped_cheese");
        private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey("overworld/caves/spaghetti_roughness_function");
        private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
        private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
        private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
        private static final ResourceKey<DensityFunction> SPAGHETTI_2D_THICKNESS_MODULATOR = createKey("overworld/caves/spaghetti_2d_thickness_modulator");
        private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");

        static {

            register(ZERO, DensityFunctions.zero());
            int i = DimensionType.MIN_Y * 2;
            int j = DimensionType.MAX_Y * 2;
            register(Y, DensityFunctions.yClampedGradient(i, j, i, j));
            DensityFunction densityfunction = register(SHIFT_X, DensityFunctions.flatCache(DensityFunctions.cache2d(DensityFunctions.shiftA(getNoise(Noises.SHIFT)))));
            DensityFunction densityfunction1 = register(SHIFT_Z, DensityFunctions.flatCache(DensityFunctions.cache2d(DensityFunctions.shiftB(getNoise(Noises.SHIFT)))));
            register(BASE_3D_NOISE, BlendedNoise.UNSEEDED);
            DensityFunction densityfunction2 = register(CONTINENTS, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, getNoise(Noises.CONTINENTALNESS))));
            DensityFunction densityfunction3 = register(EROSION, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, getNoise(Noises.EROSION))));
            DensityFunction densityfunction4 = register(RIDGES, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, getNoise(Noises.RIDGE))));
            DensityFunction densityfunction5 = DensityFunctions.noise(getNoise(Noises.JAGGED), 1500.0D, 0.0D);
            DensityFunction densityfunction6 = splineWithBlending(densityfunction2, densityfunction3, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.OFFSET, -0.81D, 2.5D, DensityFunctions.blendOffset());
            DensityFunction densityfunction7 = register(FACTOR, splineWithBlending(densityfunction2, densityfunction3, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.FACTOR, 0.0D, 8.0D, BLENDING_FACTOR));
            DensityFunction densityfunction8 = register(DEPTH, DensityFunctions.add(DensityFunctions.yClampedGradient(-64, 320, 1.5D, -1.5D), densityfunction6));
            register(SLOPED_CHEESE, slopedCheese(densityfunction2, densityfunction3, densityfunction4, densityfunction7, densityfunction8, densityfunction5));
            DensityFunction densityfunction9 = register(CONTINENTS_LARGE, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, getNoise(Noises.CONTINENTALNESS_LARGE))));
            DensityFunction densityfunction10 = register(EROSION_LARGE, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, getNoise(Noises.EROSION_LARGE))));
            DensityFunction densityfunction11 = splineWithBlending(densityfunction9, densityfunction10, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.OFFSET, -0.81D, 2.5D, DensityFunctions.blendOffset());
            DensityFunction densityfunction12 = register(FACTOR_LARGE, splineWithBlending(densityfunction9, densityfunction10, densityfunction4, DensityFunctions.TerrainShaperSpline.SplineType.FACTOR, 0.0D, 8.0D, BLENDING_FACTOR));
            DensityFunction densityfunction13 = register(DEPTH_LARGE, DensityFunctions.add(DensityFunctions.yClampedGradient(-64, 320, 1.5D, -1.5D), densityfunction11));
            register(SLOPED_CHEESE_LARGE, slopedCheese(densityfunction9, densityfunction10, densityfunction4, densityfunction12, densityfunction13, densityfunction5));
            register(SLOPED_CHEESE_END, DensityFunctions.add(DensityFunctions.endIslands(0L), ModNoiseRouterWithOnlyNoises.getFunction(BASE_3D_NOISE)));
            register(SPAGHETTI_ROUGHNESS_FUNCTION, spaghettiRoughnessFunction());
            register(SPAGHETTI_2D_THICKNESS_MODULATOR, DensityFunctions.cacheOnce(DensityFunctions.mappedNoise(getNoise(Noises.SPAGHETTI_2D_THICKNESS), 2.0D, 1.0D, -0.6D, -1.3D)));
            register(SPAGHETTI_2D, spaghetti2D());
            register(ENTRANCES, entrances());
            register(NOODLE, noodle());
            register(PILLARS, pillars());
        }

        private static ResourceKey<DensityFunction> createKey(String p_209537_) {
            return ResourceKey.create(Registry.DENSITY_FUNCTION_REGISTRY, new ResourceLocation(p_209537_));
        }

        private static DensityFunction register(ResourceKey<DensityFunction> p_209545_, DensityFunction p_209546_) {
            return new DensityFunctions.HolderHolder(BuiltinRegistries.register(BuiltinRegistries.DENSITY_FUNCTION, p_209545_, p_209546_));
        }

        private static DensityFunction slopedCheese(DensityFunction p_209482_, DensityFunction p_209483_, DensityFunction p_209484_, DensityFunction p_209485_, DensityFunction p_209486_, DensityFunction p_209487_) {
            DensityFunction densityfunction = splineWithBlending(p_209482_, p_209483_, p_209484_, DensityFunctions.TerrainShaperSpline.SplineType.JAGGEDNESS, 0.0D, 1.28D, BLENDING_JAGGEDNESS);
            DensityFunction densityfunction1 = DensityFunctions.mul(densityfunction, p_209487_.halfNegative());
            DensityFunction densityfunction2 = noiseGradientDensity(p_209485_, DensityFunctions.add(p_209486_, densityfunction1));
            return DensityFunctions.add(densityfunction2, getFunction(BASE_3D_NOISE));
        }

        private static DensityFunction noiseGradientDensity(DensityFunction p_212272_, DensityFunction p_212273_) {
            DensityFunction densityfunction = DensityFunctions.mul(p_212273_, p_212272_);
            return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction.quarterNegative());
        }

        private static DensityFunction spaghettiRoughnessFunction() {
            DensityFunction densityfunction = DensityFunctions.noise(getNoise(Noises.SPAGHETTI_ROUGHNESS));
            DensityFunction densityfunction1 = DensityFunctions.mappedNoise(getNoise(Noises.SPAGHETTI_ROUGHNESS_MODULATOR), 0.0D, -0.1D);
            return DensityFunctions.cacheOnce(DensityFunctions.mul(densityfunction1, DensityFunctions.add(densityfunction.abs(), DensityFunctions.constant(-0.4D))));
        }

        private static DensityFunction splineWithBlending(DensityFunction p_209489_, DensityFunction p_209490_, DensityFunction p_209491_, DensityFunctions.TerrainShaperSpline.SplineType p_209492_, double p_209493_, double p_209494_, DensityFunction p_209495_) {
            DensityFunction densityfunction = DensityFunctions.terrainShaperSpline(p_209489_, p_209490_, p_209491_, p_209492_, p_209493_, p_209494_);
            DensityFunction densityfunction1 = DensityFunctions.lerp(DensityFunctions.blendAlpha(), p_209495_, densityfunction);
            return DensityFunctions.flatCache(DensityFunctions.cache2d(densityfunction1));
        }

        private static DensityFunction pillars() {
            DensityFunction densityfunction = DensityFunctions.noise(getNoise(Noises.PILLAR), 25.0D, 0.3D);
            DensityFunction densityfunction1 = DensityFunctions.mappedNoise(getNoise(Noises.PILLAR_RARENESS), 0.0D, -2.0D);
            DensityFunction densityfunction2 = DensityFunctions.mappedNoise(getNoise(Noises.PILLAR_THICKNESS), 0.0D, 1.1D);
            DensityFunction densityfunction3 = DensityFunctions.add(DensityFunctions.mul(densityfunction, DensityFunctions.constant(2.0D)), densityfunction1);
            return DensityFunctions.cacheOnce(DensityFunctions.mul(densityfunction3, densityfunction2.cube()));
        }

        private static DensityFunction spaghetti2D() {
            DensityFunction densityfunction = DensityFunctions.noise(getNoise(Noises.SPAGHETTI_2D_MODULATOR), 2.0D, 1.0D);
            DensityFunction densityfunction1 = DensityFunctions.weirdScaledSampler(densityfunction, getNoise(Noises.SPAGHETTI_2D), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE2);
            DensityFunction densityfunction2 = DensityFunctions.mappedNoise(getNoise(Noises.SPAGHETTI_2D_ELEVATION), 0.0D, Math.floorDiv(-64, 8), 8.0D);
            DensityFunction densityfunction3 = getFunction(SPAGHETTI_2D_THICKNESS_MODULATOR);
            DensityFunction densityfunction4 = DensityFunctions.add(densityfunction2, DensityFunctions.yClampedGradient(-64, 320, 8.0D, -40.0D)).abs();
            DensityFunction densityfunction5 = DensityFunctions.add(densityfunction4, densityfunction3).cube();
            DensityFunction densityfunction6 = DensityFunctions.add(densityfunction1, DensityFunctions.mul(DensityFunctions.constant(0.083D), densityfunction3));
            return DensityFunctions.max(densityfunction6, densityfunction5).clamp(-1.0D, 1.0D);
        }

        private static DensityFunction entrances() {
            DensityFunction densityfunction = DensityFunctions.cacheOnce(DensityFunctions.noise(getNoise(Noises.SPAGHETTI_3D_RARITY), 2.0D, 1.0D));
            DensityFunction densityfunction1 = DensityFunctions.mappedNoise(getNoise(Noises.SPAGHETTI_3D_THICKNESS), -0.065D, -0.088D);
            DensityFunction densityfunction2 = DensityFunctions.weirdScaledSampler(densityfunction, getNoise(Noises.SPAGHETTI_3D_1), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE1);
            DensityFunction densityfunction3 = DensityFunctions.weirdScaledSampler(densityfunction, getNoise(Noises.SPAGHETTI_3D_2), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE1);
            DensityFunction densityfunction4 = DensityFunctions.add(DensityFunctions.max(densityfunction2, densityfunction3), densityfunction1).clamp(-1.0D, 1.0D);
            DensityFunction densityfunction5 = getFunction(SPAGHETTI_ROUGHNESS_FUNCTION);
            DensityFunction densityfunction6 = DensityFunctions.noise(getNoise(Noises.CAVE_ENTRANCE), 0.75D, 0.5D);
            DensityFunction densityfunction7 = DensityFunctions.add(DensityFunctions.add(densityfunction6, DensityFunctions.constant(0.37D)), DensityFunctions.yClampedGradient(-10, 30, 0.3D, 0.0D));
            return DensityFunctions.cacheOnce(DensityFunctions.min(densityfunction7, DensityFunctions.add(densityfunction5, densityfunction4)));
        }

        private static DensityFunction noodle() {
            DensityFunction densityfunction = getFunction(Y);
            DensityFunction densityfunction1 = yLimitedInterpolatable(densityfunction, DensityFunctions.noise(getNoise(Noises.NOODLE), 1.0D, 1.0D), -60, 320, -1);
            DensityFunction densityfunction2 = yLimitedInterpolatable(densityfunction, DensityFunctions.mappedNoise(getNoise(Noises.NOODLE_THICKNESS), 1.0D, 1.0D, -0.05D, -0.1D), -60, 320, 0);
            DensityFunction densityfunction3 = yLimitedInterpolatable(densityfunction, DensityFunctions.noise(getNoise(Noises.NOODLE_RIDGE_A), 2.6666666666666665D, 2.6666666666666665D), -60, 320, 0);
            DensityFunction densityfunction4 = yLimitedInterpolatable(densityfunction, DensityFunctions.noise(getNoise(Noises.NOODLE_RIDGE_B), 2.6666666666666665D, 2.6666666666666665D), -60, 320, 0);
            DensityFunction densityfunction5 = DensityFunctions.mul(DensityFunctions.constant(1.5D), DensityFunctions.max(densityfunction3.abs(), densityfunction4.abs()));
            return DensityFunctions.rangeChoice(densityfunction1, -1000000.0D, 0.0D, DensityFunctions.constant(64.0D), DensityFunctions.add(densityfunction2, densityfunction5));
        }

        private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
            return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, p_209474_, p_209475_ + 1, p_209473_, DensityFunctions.constant(p_209476_)));
        }

        private static DensityFunction postProcess(NoiseSettings p_212275_, DensityFunction p_212276_) {
            DensityFunction densityfunction = DensityFunctions.slide(p_212275_, p_212276_);
            DensityFunction densityfunction1 = DensityFunctions.blendDensity(densityfunction);
            return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction1), DensityFunctions.constant(0.64D)).squeeze();
        }
        //NoiseRouterData copy end
    }
}
