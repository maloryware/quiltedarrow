package io.github.maloryware.quilted_arrow.mixin;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.startRespawnPhase;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {



	@Inject(
		method = "respawnPlayer",
		at = @At(value = "INVOKE", target =  "Lnet/minecraft/server/world/ServerWorld;onPlayerRespawned(Lnet/minecraft/server/network/ServerPlayerEntity;)V")
	)

	private void onRespawn(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir) {
		startRespawnPhase(player); // write to the nbt tag

	}



}
