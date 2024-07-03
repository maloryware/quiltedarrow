package io.github.maloryware.quilted_arrow.datagen;

// refer to kaupenjoe's datagen tutorial for 1.19.3

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class QWorldGen extends FabricDynamicRegistryProvider {
	public QWorldGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider registries, Entries entries) {
		// worldgen bs
	}

	@Override
	public String getName() {
		return QuiltedArrow.ID;
	}
}
