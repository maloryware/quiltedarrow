package io.github.maloryware.quilted_arrow.datagen;

// refer to kaupenjoe's datagen tutorial for 1.19.3

import io.github.maloryware.quilted_arrow.item.QArrowItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class QRecipeGen extends FabricRecipeProvider {
	public QRecipeGen(FabricDataOutput output) {
		super(output);
	}

	// i'm not using the recipe book so this is just so it won't bitch about the lack of a criterion
	public static String holdString(){
		return hasItem(QArrowItems.QUILTED_ARROW);
	}

	public static CriterionConditions holdConditions(){
		return conditionsFromItem(QArrowItems.QUILTED_ARROW);
	}

	@Override
	public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {






		ShapedRecipeJsonFactory.create(RecipeCategory.MISC, QArrowItems.SKELETON_BELT, 1)
			.pattern("# #")
			.pattern("# #")
			.pattern("BBB")
			.ingredient('#', QArrowItems.WOVEN_LEATHER_STRIP)
			.ingredient('B', Items.BONE)
			.criterion(holdString(), holdConditions())
			.offerTo(exporter,new Identifier(getRecipeName(QArrowItems.SKELETON_BELT)));

		ShapedRecipeJsonFactory.create(RecipeCategory.MISC, QArrowItems.SKELETON_CHESTPLATE, 1)
			.pattern("#N#")
			.pattern("QSQ")
			.pattern("LCL")
			.ingredient('#', QArrowItems.WOVEN_LEATHER_PADDING)
			.ingredient('N', Items.NETHER_STAR)
			.ingredient('Q', QArrowItems.SKELETON_RIBCAGE)
			.ingredient('S', QArrowItems.SKELETON_SPINE)
			.ingredient('L', QArrowItems.WOVEN_LEATHER_PATCH)
			.ingredient('C', QArrowItems.SKELETON_BELT)
			.criterion(holdString(), holdConditions())
			.offerTo(exporter,new Identifier(getRecipeName(QArrowItems.SKELETON_CHESTPLATE)));

		ShapedRecipeJsonFactory.create(RecipeCategory.MISC, QArrowItems.SKELETON_HELMET, 1)
			.pattern("#N#")
			.pattern("#S#")
			.pattern(" L ")
			.ingredient('#', QArrowItems.WOVEN_LEATHER_STRIP)
			.ingredient('N', Items.NETHER_STAR)
			.ingredient('S', Items.SKELETON_SKULL)
			.ingredient('L', QArrowItems.WOVEN_LEATHER_PATCH)
			.criterion(holdString(), holdConditions())
			.offerTo(exporter,new Identifier(getRecipeName(QArrowItems.SKELETON_HELMET)));

		ShapedRecipeJsonFactory.create(RecipeCategory.MISC, QArrowItems.WOVEN_LEATHER_PADDING, 1)
			.pattern("# #")
			.pattern("QQQ")
			.pattern(" Q ")
			.ingredient('#', QArrowItems.WOVEN_LEATHER_STRIP)
			.ingredient('Q', QArrowItems.WOVEN_LEATHER_PATCH)
			.criterion(holdString(), holdConditions())
			.offerTo(exporter,new Identifier(getRecipeName(QArrowItems.WOVEN_LEATHER_PADDING)));

		ShapedRecipeJsonFactory.create(RecipeCategory.MISC, QArrowItems.SKELETON_RIBCAGE, 1)
			.pattern("###")
			.pattern("Q#Q")
			.pattern("###")
			.ingredient('#', Items.BONE)
			.ingredient('Q', QArrowItems.WOVEN_LEATHER_PATCH)
			.criterion(holdString(), holdConditions())
			.offerTo(exporter,new Identifier(getRecipeName(QArrowItems.SKELETON_RIBCAGE)));

		ShapedRecipeJsonFactory.create(RecipeCategory.MISC, QArrowItems.SKELETON_SPINE, 1)
			.pattern(" # ")
			.pattern("Q#Q")
			.pattern(" # ")
			.ingredient('#', Items.BONE)
			.ingredient('Q', QArrowItems.WOVEN_LEATHER_STRIP)
			.criterion(holdString(), holdConditions())
			.offerTo(exporter,new Identifier(getRecipeName(QArrowItems.SKELETON_SPINE)));
	}
}
