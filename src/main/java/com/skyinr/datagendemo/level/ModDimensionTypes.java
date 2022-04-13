package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.OptionalLong;

public class ModDimensionTypes {
    public static final DeferredRegister<DimensionType> DEFERREDS = DeferredRegister.createOptional(Registry.DIMENSION_TYPE_REGISTRY, DataGenDemo.MODID);

    public static final RegistryObject<DimensionType> DIMENSION_TYPE_DEMO = DEFERREDS.register("dimension_type_demo", () -> DimensionType.create(
            OptionalLong.empty(),
            true,
            false,
            false,
            true,
            1.0D,
            false,
            true,
            false,
            true,
            true,
            -64,
            256,
            256,
            BlockTags.INFINIBURN_OVERWORLD,
            DimensionType.OVERWORLD_EFFECTS,
            0.0F
    ));
}
