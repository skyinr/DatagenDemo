package com.skyinr.datagendemo.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.level.ModBiomes;
import com.skyinr.datagendemo.level.ModLevels;
import net.minecraft.core.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.info.WorldgenRegistryDumpReport;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class ModWorldgenProvider implements DataProvider {
    private final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    private final Logger logger = LogManager.getLogger();

    public static final RegistryAccess registryAccess = RegistryAccess.builtinCopy();
    protected final DynamicOps<JsonElement> ops;
    private final boolean dump;

    private final DataGenerator generator;
    private final String modid;

    public static final MappedRegistry<LevelStem> levelStems = (MappedRegistry<LevelStem>) DimensionType.defaultDimensions(registryAccess, 0);
    public static final MappedRegistry<DimensionType> dimensionTypes = (MappedRegistry<DimensionType>) registryAccess.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
    public static final MappedRegistry<Biome> biomes = (MappedRegistry<Biome>) registryAccess.ownedRegistryOrThrow(Registry.BIOME_REGISTRY);
    public static final MappedRegistry<ConfiguredWorldCarver<?>> configuredCarvers = (MappedRegistry<ConfiguredWorldCarver<?>>) registryAccess.ownedRegistryOrThrow(Registry.CONFIGURED_CARVER_REGISTRY);
    public static final MappedRegistry<ConfiguredFeature<?, ?>> configuredFeatures = (MappedRegistry<ConfiguredFeature<?, ?>>) registryAccess.ownedRegistryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY);
    public static final MappedRegistry<ConfiguredStructureFeature<?, ?>> configuredStructureFeatures = (MappedRegistry<ConfiguredStructureFeature<?, ?>>) registryAccess.ownedRegistryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY);
    public static final MappedRegistry<DensityFunction> densityFunctions = (MappedRegistry<DensityFunction>) registryAccess.ownedRegistryOrThrow(Registry.DENSITY_FUNCTION_REGISTRY);
    public static final MappedRegistry<NormalNoise.NoiseParameters> noiseParameters = (MappedRegistry<NormalNoise.NoiseParameters>) registryAccess.ownedRegistryOrThrow(Registry.NOISE_REGISTRY);
    public static final MappedRegistry<NoiseGeneratorSettings> noiseGeneratorSettings = (MappedRegistry<NoiseGeneratorSettings>) registryAccess.ownedRegistryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
    public static final MappedRegistry<PlacedFeature> placedFeatures = (MappedRegistry<PlacedFeature>) registryAccess.ownedRegistryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
    public static final MappedRegistry<StructureProcessorList> processorLists = (MappedRegistry<StructureProcessorList>) registryAccess.ownedRegistryOrThrow(Registry.PROCESSOR_LIST_REGISTRY);
    public static final MappedRegistry<StructureSet> structureSets = (MappedRegistry<StructureSet>) registryAccess.ownedRegistryOrThrow(Registry.STRUCTURE_SET_REGISTRY);
    public static final MappedRegistry<StructureTemplatePool> templatePools = (MappedRegistry<StructureTemplatePool>) registryAccess.ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);

    protected final Map<ResourceKey<Registry<?>>, Map<ResourceLocation, Object>> objects = new HashMap<>();

    public ModWorldgenProvider(DataGenerator generator, String modid, boolean dump) {
        this.generator = generator;
        this.modid = modid;
        this.dump = dump;

        this.ops = RegistryOps.create(JsonOps.INSTANCE, registryAccess);
    }

    protected void addAll() {
        addBiome(ModBiomes.DEMO_BIOME.getId(), ModBiomes.DEMO_BIOME.get());

        addDimension(DataGenDemo.modLoc("data_gen_level_stem_demo"), ModLevels.DATA_GEN_LEVEL_STEM_DEMO);

        addDimensionType(DataGenDemo.modLoc("data_gen_dimension_type"), ModLevels.DIMENSION_TYPE_DEMO);

        addNoiseGeneratorSettings(DataGenDemo.modLoc("data_gen_level_demo_settings"), ModLevels.NOISE_GENERATOR_SETTINGS_DEMO);

    }

    protected void addDimension(ResourceLocation name, LevelStem stem) {
        add(name, stem, levelStems);
    }

    protected void addDimensionType(ResourceLocation name, DimensionType type) {
        add(name, type, dimensionTypes);
    }

    protected void addBiome(ResourceLocation name, Biome biome) {
        add(name, biome, biomes);
    }

    protected void addConfiguredCarver(ResourceLocation name, ConfiguredWorldCarver<?> carver) {
        add(name, carver, configuredCarvers);
    }

    protected void addConfiguredFeature(ResourceLocation name, ConfiguredFeature<?, ?> feature) {
        add(name, feature, configuredFeatures);
    }

    protected void addConfiguredStructureFeature(ResourceLocation name, ConfiguredStructureFeature<?, ?> feature) {
        add(name, feature, configuredStructureFeatures);
    }

    protected void addDensityFunction(ResourceLocation name, DensityFunction function) {
        add(name, function, densityFunctions);
    }

    protected void addNoiseParameters(ResourceLocation name, NormalNoise.NoiseParameters parameters) {
        add(name, parameters, noiseParameters);
    }

    protected void addNoiseGeneratorSettings(ResourceLocation name, NoiseGeneratorSettings settings) {
        add(name, settings, noiseGeneratorSettings);
    }

    protected void addPlacedFeature(ResourceLocation name, PlacedFeature feature) {
        add(name, feature, placedFeatures);
    }

    protected void addProcessorList(ResourceLocation name, StructureProcessorList list) {
        add(name, list, processorLists);
    }

    protected void addStructureSet(ResourceLocation name, StructureSet set) {
        add(name, set, structureSets);
    }

    protected void addTemplatePool(ResourceLocation name, StructureTemplatePool pool) {
        add(name, pool, templatePools);
    }

    protected <T> ResourceLocation add(ResourceLocation name, T value, WritableRegistry<T> registry) {
        ResourceKey key = registry.key();
        Holder<T> holder = register(name, value, registry, true);
        objects.computeIfAbsent(key, __ -> new HashMap<>()).put(name, holder.value());
        return name;
    }

    private <T> Holder<T> register(ResourceLocation name, T value, WritableRegistry<T> registry, boolean override) {
        if (registry.containsKey(name)) {
            if (override) {
                int id = registry.getId(registry.get(name));
                return registry.registerOrOverride(OptionalInt.of(id), ResourceKey.create(registry.key(), name), value, Lifecycle.stable());
            } else {
                return registry.getHolderOrThrow(ResourceKey.create(registry.key(), name));
            }
        } else {
            return registry.register(ResourceKey.create(registry.key(), name), value, Lifecycle.stable());
        }
    }

    @Override
    public void run(HashCache cache) {
        if (dump) {
            new WorldgenRegistryDumpReport(generator).run(cache);
        }

        Path path = generator.getOutputFolder();
        addAll();

        save(path, cache, levelStems, LevelStem.CODEC);
        save(path, cache, dimensionTypes, DimensionType.DIRECT_CODEC);
        save(path, cache, biomes, Biome.DIRECT_CODEC);
        save(path, cache, configuredCarvers, ConfiguredWorldCarver.DIRECT_CODEC);
        save(path, cache, configuredFeatures, ConfiguredFeature.DIRECT_CODEC);
        save(path, cache, configuredStructureFeatures, ConfiguredStructureFeature.DIRECT_CODEC);
        save(path, cache, densityFunctions, DensityFunction.DIRECT_CODEC);
        save(path, cache, noiseParameters, NormalNoise.NoiseParameters.DIRECT_CODEC);
        save(path, cache, noiseGeneratorSettings, NoiseGeneratorSettings.DIRECT_CODEC);
        save(path, cache, placedFeatures, PlacedFeature.DIRECT_CODEC);
        save(path, cache, processorLists, StructureProcessorType.DIRECT_CODEC);
        save(path, cache, structureSets, StructureSet.DIRECT_CODEC);
        save(path, cache, templatePools, StructureTemplatePool.DIRECT_CODEC);
    }

    private <T> void save(Path path, HashCache cache, Registry<T> register, Codec<T> codec) {
        ResourceKey<? extends Registry<T>> key = register.key();
        Path target = path.resolve("data").resolve(modid).resolve(key.location().getPath());
        Map<ResourceLocation, T> values = (Map<ResourceLocation, T>) objects.get(key);
        if (values != null && !values.isEmpty()) {
            values.forEach((name, value) -> {
                JsonElement json = codec.encodeStart(ops, value)
                        .getOrThrow(false, s -> {
                            throw new RuntimeException("Couldn't serialize element " + name + ": " + s);
                        });
                Path output = target.resolve(name.getPath() + ".json");
                save(cache, json, output);
            });
        }
    }

    private void save(HashCache cache, JsonElement element, Path path) {
        try {
            DataProvider.save(gson, cache, element, path);
        } catch (IOException e) {
            logger.error("Couldn't save element {}", path, e);
        }
    }

    @Override
    public String getName() {
        return "Worldgen:" + DataGenDemo.MODID;
    }
}
