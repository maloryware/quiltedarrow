package io.github.maloryware.quilted_arrow.mixin;

import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

	@Inject(
		method = "respawnPlayer",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;onPlayerRespawned(Lnet/minecraft/server/network/ServerPlayerEntity;)V")
	)

	private void spawnSpectatorEntity(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir){
		ServerWorld world = player.getServerWorld();
		AllayEntity respawnCamera = new AllayEntity(EntityType.ALLAY, world);
		ArmorStandEntity startEntity = new ArmorStandEntity(EntityType.ARMOR_STAND, world);
		Vec3d start = player.getPos();


		startEntity.setPosition(start);
		startEntity.setInvisible(true);

		respawnCamera.setPosition(start);
		respawnCamera.setInvisible(true);
		respawnCamera.setInvulnerable(true);
		respawnCamera.setAiDisabled(true);


		respawnCamera.setTarget(startEntity);

		respawnCamera.getPathfindingFavor(PlayerWaystoneManager.getNearestWaystone(player).getPos());

	}
}
