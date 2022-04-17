package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> DEMO = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, DataGenDemo.modLoc("noise_generator_settings_demo"));
    public static final DeferredRegister<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS = DeferredRegister.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, DataGenDemo.MODID);

    public static final RegistryObject<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS_DEMO = NOISE_GENERATOR_SETTINGS.register("noise_generator_settings_demo", () -> new NoiseGeneratorSettings(
            NoiseSettings.create(-64, 384, new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-0.078125D, 2, 0), new NoiseSlider(0.4D, 3, 0), 1, 2, TerrainProvider.overworld(true)),
            ModBlocks.BLOCK_DEMO.get().defaultBlockState(),
            Blocks.WATER.defaultBlockState(),
            //使用了AT
            NoiseRouterData.overworldWithNewCaves(MODNoiseSettings.getNoiseSettings(), true),
            SurfaceRuleData.overworld(),
            0,
            false,
            true,
            true,
            false)
    );

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
