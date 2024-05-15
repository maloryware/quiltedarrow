package io.github.maloryware.quilted_arrow.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;


public class QArrowItems {

	public static final Item WOVEN_LEATHER_STRIP = new Item(new QuiltItemSettings().group(ItemGroup.MISC));
	public static final Item WOVEN_LEATHER_PATCH = new Item(new QuiltItemSettings().group(ItemGroup.MISC));


	// skeleton armor set
	public static final Item SKELETON_HELMET = new QArrowArmorItem(QArrowArmorMaterial.BONE, EquipmentSlot.HEAD,
		new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCOMBAT));

	public static final Item SKELETON_CHESTPLATE = new QArrowArmorItem(QArrowArmorMaterial.BONE, EquipmentSlot.CHEST,
		new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCOMBAT));

	public static final Item SKELETON_LEGGINGS = new QArrowArmorItem(QArrowArmorMaterial.BONE, EquipmentSlot.LEGS,
		new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCOMBAT));

	public static final Item SKELETON_BOOTS = new QArrowArmorItem(QArrowArmorMaterial.BONE, EquipmentSlot.FEET,
		new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCOMBAT));





	public static void register(ModContainer mod) {
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "woven_leather_strip"), WOVEN_LEATHER_STRIP);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "woven_leather_patch"), WOVEN_LEATHER_PATCH);

		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_helmet"), SKELETON_HELMET);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_chestplate"), SKELETON_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_leggings"), SKELETON_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_boots"), SKELETON_BOOTS);

	}
}
