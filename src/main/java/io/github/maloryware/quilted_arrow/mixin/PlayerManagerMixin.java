package io.github.maloryware.quilted_arrow.mixin;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.startRespawnPhase;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

	@Inject(
		method = "respawnPlayer",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;onPlayerRespawned(Lnet/minecraft/server/network/ServerPlayerEntity;)V")
	)

	private void spectateRespawn(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir) {
		startRespawnPhase(player); // write to the nbt tag
		final boolean changed = player.changeGameMode(GameMode.SURVIVAL);
		QuiltedArrow.LOGGER.info("Setting gamemode to survival: {} ", changed);
	}



}
