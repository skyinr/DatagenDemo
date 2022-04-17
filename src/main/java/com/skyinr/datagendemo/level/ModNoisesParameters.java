package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModNoisesParameters {
    public static final DeferredRegister<NormalNoise.NoiseParameters> NOISE_PARAMETERS = DeferredRegister.create(Registry.NOISE_REGISTRY, DataGenDemo.MODID);

    public static final RegistryObject<NormalNoise.NoiseParameters> NOISE_PARAMETERS_DEMO = NOISE_PARAMETERS.register("noise_parameters_demo", () -> new NormalNoise.NoiseParameters(
            -4,
            new DoubleArrayList(new double[]{1.0D})
    ));
}
