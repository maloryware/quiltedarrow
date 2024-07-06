package io.github.maloryware.quilted_arrow.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;

public class RegisterHelpers {

	// this allows for quick n' easy adding of modded items to vanilla item tabs


	protected static void addToIngredients(FabricItemGroupEntries entries){

		entries.addItem(QArrowItems.QUILTED_ARROW);
		entries.addItem(QArrowItems.WOVEN_LEATHER_PADDING);
		entries.addItem(QArrowItems.WOVEN_LEATHER_PATCH);
		entries.addItem(QArrowItems.WOVEN_LEATHER_STRIP);
		entries.addItem(QArrowItems.SKELETON_RIBCAGE);
		entries.addItem(QArrowItems.SKELETON_SPINE);
		entries.addItem(QArrowItems.SKELETON_BELT);

	}
	/*
	protected static void addToRedstone(FabricItemGroupEntries entries){
		entries.addItem(QArrowItems.QUILTED_ARROW);
	}
	*/
	protected static void addToCombat(FabricItemGroupEntries entries){
		entries.addItem(QArrowItems.SKELETON_HELMET);
		entries.addItem(QArrowItems.SKELETON_CHESTPLATE);
		entries.addItem(QArrowItems.SKELETON_LEGGINGS);
		entries.addItem(QArrowItems.SKELETON_BOOTS);
	}

}
