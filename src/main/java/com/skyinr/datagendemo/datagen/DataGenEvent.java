package com.skyinr.datagendemo.datagen;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import com.skyinr.datagendemo.item.ModItems;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DataGenDemo.MODID)
public class DataGenEvent {
    @SubscribeEvent
    public static void register(GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeClient()) {
            //block/item models, blockstates, language files...
            event.getGenerator().addProvider(new ModBlockStateProvider(event.getGenerator(),
                    DataGenDemo.MODID, helper, ModBlocks.BLOCKS));
            event.getGenerator().addProvider(new ModItemModelProvider(event.getGenerator(),
                    DataGenDemo.MODID, helper, ModItems.ITEMS));
        }
        if (event.includeServer()) {
            //recipes,advancements,tags...
        }
    }
}
