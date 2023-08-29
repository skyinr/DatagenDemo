package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoisesParameters {
    public static final ResourceKey<NormalNoise.NoiseParameters> NOISE_PARAMETERS_DEMO_KEY = ResourceKey.create(Registries.NOISE, DataGenDemo.modLoc("noise_parameters_demo"));

    public static void bootstrap(BootstapContext<NormalNoise.NoiseParameters> pContext) {
        pContext.register(NOISE_PARAMETERS_DEMO_KEY, new NormalNoise.NoiseParameters(
                -4,
                new DoubleArrayList(new double[]{1.0D, 0.5D, 0.23D, 4.32D})));
    }

}
