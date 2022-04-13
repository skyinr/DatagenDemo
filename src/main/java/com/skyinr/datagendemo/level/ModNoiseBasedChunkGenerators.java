package com.skyinr.datagendemo.level;

import com.mojang.serialization.Codec;
import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraftforge.registries.DeferredRegister;

public class ModNoiseBasedChunkGenerators {
//    public static final DeferredRegister<Codec<? extends ChunkGenerator>> NOISE_BASED_CHUNK_GENERATORS = DeferredRegister.create(Registry.CHUNK_GENERATOR_REGISTRY, DataGenDemo.MODID);
//
//    public static final RegistryObject<Codec<? extends ChunkGenerator>> NOISE_BASED_CHUNK_GENERATOR_DEMO = NOISE_BASED_CHUNK_GENERATORS.register("noise_based_chunk_generator_demo", () -> Codec.unit(noiseBasedChunkGenerator));


    public static NoiseBasedChunkGenerator getNoiseBasedChunkGenerator() {
        return new NoiseBasedChunkGenerator(
                BuiltinRegistries.STRUCTURE_SETS,
                BuiltinRegistries.NOISE,
                new FixedBiomeSource(ModBiomes.BIOME_DEMO.getHolder().get()),
                59L,
                new Holder.Direct<>(ModNoiseGeneratorSettings.NOISE_GENERATOR_SETTINGS_DEMO.get()));
    }
}
