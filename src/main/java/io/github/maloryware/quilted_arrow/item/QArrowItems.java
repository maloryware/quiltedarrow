package io.github.maloryware.quilted_arrow.item;

import io.github.maloryware.quilted_arrow.geckolib.SkeletonSet.QArrowSkeletonArmorSetPiece;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;


public class QArrowItems {

	public static final Item WOVEN_LEATHER_STRIP = new Item(new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCORE));
	public static final Item WOVEN_LEATHER_PATCH = new Item(new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCORE));
	public static final Item QUILTED_ARROW = new Item(new QuiltItemSettings().rarity(Rarity.RARE));

	// untextured

	public static final Item WOVEN_LEATHER_PADDING = new Item(new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCORE));
	public static final Item SKELETON_RIBCAGE = new Item(new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCORE));
	public static final Item SKELETON_SPINE = new Item(new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCORE));
	public static final Item SKELETON_BELT = new Item(new QuiltItemSettings().group(QArrowItemTab.QUILTEDARROWCORE));

	// skeleton armor set
	public static final Item SKELETON_HELMET = new QArrowSkeletonArmorSetPiece(
		QArrowArmorMaterials.BONE,
		EquipmentSlot.HEAD,
		new QuiltItemSettings()
			.group(QArrowItemTab.QUILTEDARROWCOMBAT)
			.group(QArrowItemTab.QUILTEDARROWCORE));

	public static final Item SKELETON_CHESTPLATE = new QArrowSkeletonArmorSetPiece(
		QArrowArmorMaterials.BONE,
		EquipmentSlot.CHEST,
		new QuiltItemSettings()
			.group(QArrowItemTab.QUILTEDARROWCOMBAT)
			.group(QArrowItemTab.QUILTEDARROWCORE));


	public static final Item SKELETON_LEGGINGS = new QArrowSkeletonArmorSetPiece(
		QArrowArmorMaterials.BONE,
		EquipmentSlot.LEGS,
		new QuiltItemSettings()
			.group(QArrowItemTab.QUILTEDARROWCOMBAT)
			.group(QArrowItemTab.QUILTEDARROWCORE));


	public static final Item SKELETON_BOOTS = new QArrowSkeletonArmorSetPiece(
		QArrowArmorMaterials.BONE,
		EquipmentSlot.FEET,
		new QuiltItemSettings()
			.group(QArrowItemTab.QUILTEDARROWCOMBAT).group(QArrowItemTab.QUILTEDARROWCORE));


	public static void register(ModContainer mod) {
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "woven_leather_strip"), WOVEN_LEATHER_STRIP);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "woven_leather_patch"), WOVEN_LEATHER_PATCH);

		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "woven_leather_padding"), WOVEN_LEATHER_PADDING);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "ribcage"), SKELETON_RIBCAGE);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "spine"), SKELETON_SPINE);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "bone_belt"), SKELETON_BELT);

		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_helmet"), SKELETON_HELMET);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_chestplate"), SKELETON_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_leggings"), SKELETON_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "skeleton_boots"), SKELETON_BOOTS);


		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "quilted_arrow"), QUILTED_ARROW);

	}
}
