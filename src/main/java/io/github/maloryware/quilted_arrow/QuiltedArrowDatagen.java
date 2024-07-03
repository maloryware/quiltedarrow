package io.github.maloryware.quilted_arrow;

import io.github.maloryware.quilted_arrow.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class QuiltedArrowDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator){
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(QLootTableGen::new);
		pack.addProvider(QRecipeGen::new);
		pack.addProvider(QModelProvider::new);
		pack.addProvider(QBlockTagProvider::new);
		pack.addProvider(QItemTagProvider::new);
		pack.addProvider(QWorldGen::new);

	}
}
