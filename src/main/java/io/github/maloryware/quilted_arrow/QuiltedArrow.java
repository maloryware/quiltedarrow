package io.github.maloryware.quilted_arrow;

import io.github.maloryware.quilted_arrow.block.QArrowBlocks;
import io.github.maloryware.quilted_arrow.effects.QArrowEffects;
import io.github.maloryware.quilted_arrow.item.QArrowItemTabs;
import io.github.maloryware.quilted_arrow.item.QArrowItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class QuiltedArrow implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("Quilted Arrow Core Components");

	public static String ID = "quilted_arrow";

	@Override
    public void onInitialize(ModContainer mod) {


		GeckoLib.initialize();

        LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());


		QArrowItems.register();
		QArrowItemTabs.register();
		QArrowEffects.register();
		QArrowBlocks.register();

    }
}
