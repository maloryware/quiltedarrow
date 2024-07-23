package io.github.maloryware.quilted_arrow.event;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.*;

public class QArrowEvents {


	public static void changeToSpectator(ServerPlayerEntity oldplayer, ServerPlayerEntity player, boolean alive){
		if(!alive){
			player.changeGameMode(GameMode.SPECTATOR);
			startRespawnPhase(player);
			RESPAWN.get(player).getRespawnPhase().ifPresent( respawnPhase -> {
				if (respawnPhase.nearestWaystone() == null) {
					QuiltedArrow.LOGGER.info("No waystone found.");
					endRespawnPhase(player);
					player.changeGameMode(GameMode.SURVIVAL);
				}
			});
		}
	}

	public static void register() {

		ServerPlayerEvents.AFTER_RESPAWN.register(QArrowEvents::changeToSpectator);


	}
}

