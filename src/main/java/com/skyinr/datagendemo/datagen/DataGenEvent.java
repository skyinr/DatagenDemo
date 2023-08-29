package com.skyinr.datagendemo.datagen;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import com.skyinr.datagendemo.datagen.loottable.ModLootTableProvider;
import com.skyinr.datagendemo.datagen.tags.ModBlockTagsProvider;
import com.skyinr.datagendemo.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DataGenDemo.MODID)
public class DataGenEvent {
    @SubscribeEvent
    public static void register(GatherDataEvent event) {
        PackOutput packOutput = event.getGenerator().getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        if (event.includeClient()) {
            //block/item models, blockstates, language files...
            event.getGenerator().addProvider(true, new ModBlockStateProvider(packOutput,
                    DataGenDemo.MODID, helper, ModBlocks.BLOCKS));
            event.getGenerator().addProvider(true, new ModItemModelProvider(packOutput,
                    DataGenDemo.MODID, helper, ModItems.ITEMS));
            event.getGenerator().addProvider(true, new ModLanguageProvider(packOutput,
                    DataGenDemo.MODID, "en_us"));
        }
        if (event.includeServer()) {
            //recipes,advancements,tags...
            event.getGenerator().addProvider(true, new ModLootTableProvider(packOutput
            ));
            event.getGenerator().addProvider(true, new ModWorldGenProvider(packOutput, lookupProvider));
            event.getGenerator().addProvider(true, new ModBlockTagsProvider(packOutput,
                    lookupProvider, helper));
            event.getGenerator().addProvider(true, new ModRecipeProvider(packOutput));
        }
    }
}
