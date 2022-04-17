package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.registries.DeferredRegister;

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
