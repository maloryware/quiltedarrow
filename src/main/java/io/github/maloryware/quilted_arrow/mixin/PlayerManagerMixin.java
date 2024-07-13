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
		startRespawnPhase(player); // something something write to the nbt tag
		player.interactionManager.changeGameMode(GameMode.SPECTATOR);
		QuiltedArrow.LOGGER.info("Changing gamemode to spectator: {}", player.changeGameMode(GameMode.SPECTATOR));
	}



}
