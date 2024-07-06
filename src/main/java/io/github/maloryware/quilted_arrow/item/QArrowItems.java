package io.github.maloryware.quilted_arrow.item;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
/*
import io.github.maloryware.quilted_arrow.block.QArrowBlocks;
import io.github.maloryware.quilted_arrow.geckolib.SkeletonSet.QArrowSkeletonArmorSetPiece;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
*/
import io.github.maloryware.quilted_arrow.item.custom.ArmorItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;


public class QArrowItems {

	public static final Item QUILTED_ARROW = registerItem("quilted_arrow",new Item(new QuiltItemSettings().rarity(Rarity.EPIC)));

	public static final Item WOVEN_LEATHER_STRIP = registerItem("woven_leather_strip", new Item(new QuiltItemSettings()));
	public static final Item WOVEN_LEATHER_PATCH = registerItem("woven_leather_patch", new Item(new QuiltItemSettings()));

	public static final Item SKELETON_RIBCAGE = registerItem("skeleton_ribcage", new Item(new QuiltItemSettings()));
	public static final Item SKELETON_SPINE = registerItem("skeleton_spine", new Item(new QuiltItemSettings()));
	public static final Item SKELETON_BELT = registerItem("skeleton_belt", new Item(new QuiltItemSettings()));

	// untextured

	public static final Item WOVEN_LEATHER_PADDING = registerItem("woven_leather_padding",new Item(new QuiltItemSettings()));

	// skeleton armor set


	public static final Item SKELETON_HELMET = registerItem(
		"skeleton_helmet", new ArmorItem(
			ArmorMaterials.BONE,
			net.minecraft.item.ArmorItem.ArmorSlot.HELMET,
			new QuiltItemSettings()));

	public static final Item SKELETON_CHESTPLATE = registerItem(
		"skeleton_chestplate", new ArmorItem(
			ArmorMaterials.BONE,
			net.minecraft.item.ArmorItem.ArmorSlot.CHESTPLATE,
			new QuiltItemSettings()));


	public static final Item SKELETON_LEGGINGS = registerItem(
		"skeleton_leggings", new ArmorItem(
			ArmorMaterials.BONE,
			net.minecraft.item.ArmorItem.ArmorSlot.LEGGINGS,
			new QuiltItemSettings()));


	public static final Item SKELETON_BOOTS = registerItem(
		"skeleton_boots", new ArmorItem(
			ArmorMaterials.BONE,
			net.minecraft.item.ArmorItem.ArmorSlot.BOOTS,
			new QuiltItemSettings()));






	private static Item registerItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(QuiltedArrow.ID, name), item);
	}

	public static void register() {
		QuiltedArrow.LOGGER.info("Registering Quilted Arrow items...");


		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(RegisterHelpers::addToIngredients);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(RegisterHelpers::addToCombat);
		// ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE_BLOCKS).register(QArrowItemRegisterHelpers::addToRedstone);

	}
}
