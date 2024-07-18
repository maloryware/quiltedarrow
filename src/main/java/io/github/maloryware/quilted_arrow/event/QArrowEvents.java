package io.github.maloryware.quilted_arrow.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import io.github.maloryware.quilted_arrow.component.RespawnPhaseComponent;

public class QArrowEvents {


	public static void changeToSpectator(ServerPlayerEntity oldplayer, ServerPlayerEntity player, boolean alive){
		if(!alive){
			player.changeGameMode(GameMode.SPECTATOR);
			(player);
		}
	}

	public static void getRespawnData(){

	}


	public static void register() {

		ServerPlayerEvents.AFTER_RESPAWN.register(QArrowEvents::changeToSpectator);


	}
}

