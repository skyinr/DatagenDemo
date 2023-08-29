package com.skyinr.datagendemo.datagen;

import com.skyinr.datagendemo.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLanguageProvider extends LanguageProvider {
    protected String modID;

    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
        this.modID = modid;
    }

    @Override
    protected void addTranslations() {
        add(ModItems.ModTabs.TAB_KEY, "DataGen Demo");
        try {
            ForgeRegistries.BLOCKS.getValues().stream()
                    .filter(block -> modID.equals(ForgeRegistries.BLOCKS.getKey(block).getNamespace()))
                    .forEach(block -> addBlock(() -> block, ForgeRegistries.BLOCKS.getKey(block).getPath()));
            ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> modID.equals(ForgeRegistries.ITEMS.getKey(item).getNamespace()) && !(item instanceof BlockItem))
                    .forEach(item -> addItem(() -> item, ForgeRegistries.ITEMS.getKey(item).getPath()));
            ForgeRegistries.ENCHANTMENTS.getValues().stream()
                    .filter(enchantment -> modID.equals(ForgeRegistries.ENCHANTMENTS.getKey(enchantment).getNamespace()))
                    .forEach(enchantment -> addEnchantment(() -> enchantment, enchantment.getDescriptionId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
