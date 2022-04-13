package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collections;

public class ModStructureSets {
    public static final DeferredRegister<StructureSet> STRUCTURE_SETS = DeferredRegister.createOptional(Registry.STRUCTURE_SET_REGISTRY, DataGenDemo.MODID);

//    public static final RegistryObject<StructureSet> STRUCTURE_SET_DEMO = STRUCTURE_SETS.register("structure_set_demo", () -> new StructureSet(
//            new ArrayList(Collections.singleton(StructureFeature.OCEAN_MONUMENT.configured(OreConfiguration.NONE, ModBiomes.BIOMES.createTagKey(ModBiomes.BIOME_DEMO.getId())))),
//            new RandomSpreadStructurePlacement(
//                    8,
//                    8,
//                    RandomSpreadType.TRIANGULAR,
//                    23,
//                    new Vec3i(8, 1, 8)
//            )
//    ));
}
