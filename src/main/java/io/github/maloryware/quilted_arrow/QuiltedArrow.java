package io.github.maloryware.quilted_arrow;

import io.github.maloryware.quilted_arrow.effects.QArrowEffects;
import io.github.maloryware.quilted_arrow.event.AttackEntityHandler;
import io.github.maloryware.quilted_arrow.item.QArrowBlocks;
import io.github.maloryware.quilted_arrow.item.QArrowItems;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class QuiltedArrow implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Quilted Arrow Essentials");
	public static final String ID = "quilted_arrow";

	@Override
	public void onInitialize(ModContainer mod) {

		LOGGER.info("{} initialized.", mod.metadata().name());
		// remember to initialize shit in the right order you fucking dingus

		QArrowEffects.registerEffects();

		GeckoLib.initialize();
		QArrowItems.register(mod);
		QArrowBlocks.register(mod);
		AttackEntityCallback.EVENT.register(new AttackEntityHandler());

	}
}
