package io.github.maloryware.quilted_arrow;

import io.github.maloryware.quilted_arrow.block.QArrowBlocks;
import io.github.maloryware.quilted_arrow.effect.QArrowEffects;
import io.github.maloryware.quilted_arrow.event.QArrowEvents;
import io.github.maloryware.quilted_arrow.item.QArrowItemTabs;
import io.github.maloryware.quilted_arrow.item.QArrowItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class QuiltedArrow implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("Quilted Arrow Core Components");

	public static final String ID = "quilted_arrow";

	@Override
    public void onInitialize(ModContainer mod) {

		GeckoLib.initialize();
		// ORDER IDIOT, THE FUCKING ORDER IN WHICH YOU REGISTER THOSE FUCKING ENTRYPOINTS NMATTERS GRAAHAZ<HHA0HAHAHHAHAHBAHB
		QArrowEffects.register();
		QArrowItems.register();
		QArrowItemTabs.register();
		QArrowBlocks.register();
		QArrowEvents.register();

    }
}
