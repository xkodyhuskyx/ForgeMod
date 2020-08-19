package com.kodyhusky.examplemod.items;

import com.kodyhusky.examplemod.ExampleMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(ExampleMod.TAB));
    }
}
