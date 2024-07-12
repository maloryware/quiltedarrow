package io.github.maloryware.quilted_arrow.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
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
		ServerWorld world = player.getServerWorld();
		ArmorStandEntity startEntity = new ArmorStandEntity(EntityType.ARMOR_STAND, world);
		Vec3d start = player.getPos();
		player.changeGameMode(GameMode.SPECTATOR);
		player.noClip = true;
		startRespawnPhase(player); // something something write to the nbt tag
	}

}
