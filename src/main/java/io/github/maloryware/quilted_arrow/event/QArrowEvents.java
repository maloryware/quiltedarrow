package io.github.maloryware.quilted_arrow.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

public class QArrowEvents {


	public static void changeToSpectator(ServerPlayerEntity oldplayer, ServerPlayerEntity player, boolean alive){
		player.changeGameMode(GameMode.SPECTATOR);
	}


	public static void register() {

		ServerPlayerEvents.AFTER_RESPAWN.register(QArrowEvents::changeToSpectator);


	}
}

