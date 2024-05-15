package io.github.maloryware.quilted_arrow;

import io.github.maloryware.quilted_arrow.item.QArrowBlocks;
import io.github.maloryware.quilted_arrow.item.QArrowItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuiltedArrow implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Quilted Arrow Essentials");

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());

		QArrowItems.register(mod);
		QArrowBlocks.register(mod);
    }
}
