package io.github.maloryware.quilted_arrow.event;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.*;

public class QArrowEvents {


	public static void changeToSpectator(ServerPlayerEntity oldplayer, ServerPlayerEntity player, boolean alive) {
		if (!alive) {
			player.changeGameMode(GameMode.SPECTATOR);
			startRespawnPhase(player);
			RESPAWN.get(player).getRespawnPhase().ifPresent(respawnPhase -> {
				if (respawnPhase.nearestWaystone() == null) {
					QuiltedArrow.LOGGER.info("No waystone found.");
					endRespawnPhase(player);
					player.changeGameMode(GameMode.SURVIVAL);
				}
			});
		}
	}

	public static boolean ceaseFlightUnderwater(LivingEntity entity) {
		if (entity instanceof PlayerEntity playerEntity) {
			if (playerEntity.isSubmergedInWater()) {
				playerEntity.stopFallFlying();
				return false;
			}
			else {
				return true;
			}

		}
		return false;
	}

	@SuppressWarnings("deprecation") // shut UP I KNOW IT'S DEPRECATED I'LL SWITCH TO THE QUILT VERSION WHEN I FUCKING DIE
	public static void register() {

		ServerPlayerEvents.AFTER_RESPAWN.register(QArrowEvents::changeToSpectator);
		EntityElytraEvents.ALLOW.register(QArrowEvents::ceaseFlightUnderwater);


	}

}

