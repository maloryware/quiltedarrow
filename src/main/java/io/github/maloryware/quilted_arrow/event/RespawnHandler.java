package io.github.maloryware.quilted_arrow.event;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.quiltmc.qsl.entity.event.api.ServerPlayerEntityCopyCallback;

public class RespawnHandler implements ServerPlayerEntityCopyCallback {

	@Override
	public void onPlayerCopy(ServerPlayerEntity copy, ServerPlayerEntity original, boolean wasDeath) {
		if(wasDeath) {
			final boolean changed = copy.changeGameMode(GameMode.SPECTATOR);
			QuiltedArrow.LOGGER.info("Setting gamemode to spectator: {} ", changed);
		}
	}
}
