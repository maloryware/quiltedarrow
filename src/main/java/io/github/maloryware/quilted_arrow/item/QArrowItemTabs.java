package io.github.maloryware.quilted_arrow.item;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


// despite this throwing a warning, it's the only way i really managed to get it to work;
// using a method reference (QArrowItems.WOVEN_LEATHER_STRIP::getDefaultStack()) caused a crash
// and so did attempting to just directly pass a Supplier<ItemStack> instance/object

// so yeah. from now on, whenever using QuiltItemGroup#createWithIcon, use "() -> Class.ItemName.getDefaultStack
// when passing the Supplier<ItemStack> parameter

// the @SuppressWarnings decorator below simply tells the IDE to shut the fuck up


public class QArrowItemTabs {

	public static final ItemGroup QARROWCORE = Registry.register(Registries.ITEM_GROUP,
		new Identifier(QuiltedArrow.ID, "qarrowcore"),
		FabricItemGroup.builder().name(Text.translatable("itemGroup.qarrowcore"))
			.icon(() -> new ItemStack(QArrowItems.QUILTED_ARROW)).entries((displayContext, entries) -> {

				entries.addItem(QArrowItems.SKELETON_RIBCAGE);
				entries.addItem(QArrowItems.SKELETON_SPINE);
				entries.addItem(QArrowItems.SKELETON_BELT);
				entries.addItem(QArrowItems.WOVEN_LEATHER_STRIP);
				entries.addItem(QArrowItems.WOVEN_LEATHER_PATCH);
				entries.addItem(QArrowItems.WOVEN_LEATHER_PADDING);
				entries.addItem(QArrowItems.SKELETON_HELMET);
				entries.addItem(QArrowItems.SKELETON_CHESTPLATE);
				entries.addItem(QArrowItems.SKELETON_LEGGINGS);
				entries.addItem(QArrowItems.SKELETON_BOOTS);
				entries.addItem(QArrowItems.QUILTED_ARROW);

			}).build());




	public static void register(){
		QuiltedArrow.LOGGER.info("Registering Quilted Arrow item groups...");
	}



}

