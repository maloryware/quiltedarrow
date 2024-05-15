package io.github.maloryware.quilted_arrow.item;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BoneMealItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;



public class QArrowBlocks {
	public static final Block WOVEN_LEATHER_BLOCK = new Block(QuiltBlockSettings.of(Material.AIR));


	public static void register(ModContainer mod) {
		Registry.register(Registry.BLOCK,new Identifier(mod.metadata().id(), "woven_leather_block"), WOVEN_LEATHER_BLOCK);
	}
}
