package io.github.maloryware.quilted_arrow.block;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;




@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
public class QArrowBlocks {



	private static Block RegisterBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(QuiltedArrow.ID, name), block);
	}

	public static void register() {

	}
}


