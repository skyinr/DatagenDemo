package com.skyinr.datagendemo.level;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoiseRouter {
    public static NoiseRouter createDemoNoiseRouter(HolderGetter<NormalNoise.NoiseParameters> getter) {
        return new NoiseRouter(
                DensityFunctions.noise(getter.getOrThrow(Noises.AQUIFER_BARRIER), 0.5D),
                DensityFunctions.noise(getter.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D),
                DensityFunctions.noise(getter.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D),
                DensityFunctions.noise(getter.getOrThrow(Noises.AQUIFER_LAVA)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.lerp(
                        DensityFunctions.yClampedGradient(-64, -40, 0.0D, 1.0D),
                        0.4D,
                        DensityFunctions.lerp(
                                DensityFunctions.yClampedGradient(302, 320, 1.0D, 0.0D),
                                -0.078125D,
                                DensityFunctions.add(DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)), DensityFunctions.constant(-0.703125D))
                                        .clamp(-64, 64)
                        )),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY)),
                DensityFunctions.noise(getter.getOrThrow(ModNoisesParameters.NOISE_PARAMETERS_DEMO_KEY))
        );
    }
}
