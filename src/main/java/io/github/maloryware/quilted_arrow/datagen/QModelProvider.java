package io.github.maloryware.quilted_arrow.datagen;

import io.github.maloryware.quilted_arrow.item.QArrowItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.BlockStateModelGenerator;
import net.minecraft.data.client.model.Models;

// refer to kaupenjoe's datagen tutorial for 1.19.3

public class QModelProvider extends FabricModelProvider {
	public QModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {


		itemModelGenerator.register(QArrowItems.QUILTED_ARROW, Models.SINGLE_LAYER_ITEM);

		itemModelGenerator.register(QArrowItems.WOVEN_LEATHER_STRIP, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.WOVEN_LEATHER_PATCH, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.WOVEN_LEATHER_PADDING, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.SKELETON_BELT, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.SKELETON_RIBCAGE, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.SKELETON_SPINE, Models.SINGLE_LAYER_ITEM);

		itemModelGenerator.register(QArrowItems.SKELETON_HELMET, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.SKELETON_CHESTPLATE, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.SKELETON_LEGGINGS, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(QArrowItems.SKELETON_BOOTS, Models.SINGLE_LAYER_ITEM);


	}
}
