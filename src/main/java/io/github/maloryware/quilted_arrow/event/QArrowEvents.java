package io.github.maloryware.quilted_arrow.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.startRespawnPhase;

public class QArrowEvents {


	public static void changeToSpectator(ServerPlayerEntity oldplayer, ServerPlayerEntity player, boolean alive){
		if(!alive){
			player.changeGameMode(GameMode.SPECTATOR);
			startRespawnPhase(player);
		}
	}

	public static void register() {

		ServerPlayerEvents.AFTER_RESPAWN.register(QArrowEvents::changeToSpectator);


	}
}

