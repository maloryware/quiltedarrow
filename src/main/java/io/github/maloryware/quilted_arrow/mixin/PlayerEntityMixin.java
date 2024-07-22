package io.github.maloryware.quilted_arrow.mixin;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.RESPAWN;
import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.endRespawnPhase;

@Debug(
	export = true
)

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {


	public PlayerEntityMixin(EntityType<?> variant, World world) {
		super(variant, world);
	}



	@Inject(
		method = "tick",
		at = @At(
			shift = At.Shift.AFTER,
			value = "INVOKE",
			target = "Lnet/minecraft/entity/player/PlayerEntity;isSpectator()Z"
		)
	)
	public void doRespawnProcedure(CallbackInfo ci) {

		if ((Object) this instanceof ServerPlayerEntity player) {
			RESPAWN.get(player).getRespawnPhase().ifPresent(

				respawnPhase -> {
					PlayerEntity client = (PlayerEntity) (Object) this;

					Vec3d deathPos = respawnPhase.deathPos();
					Vec3d nearestWaystone = respawnPhase.nearestWaystone();
					Vec3d target = player.getPos().subtract(nearestWaystone);


					double currentDistance = player.getPos().distanceTo(nearestWaystone);
					double totalDistance = deathPos.distanceTo(nearestWaystone);
					double thirdOfDistance = totalDistance/3;

					player.lookAt(player.getCommandSource().getEntityAnchor(), nearestWaystone);

					if(currentDistance > thirdOfDistance){

						// player.updateVelocity((float) (totalDistance/currentDistance*0.1), target);
						// speed up
						client.travel(target);
					}

					else if(currentDistance < thirdOfDistance * 2){

						// player.updateVelocity((float) (currentDistance/totalDistance*0.5), target);
						// slow down
						client.travel(target);
					}

					else {

						// player.setVelocity(target.multiply(0.1));
						// keep speed
						client.travel(target);
					}

					client.velocityDirty = true;
					client.velocityModified = true;

					QuiltedArrow.LOGGER.info("Moving toward location... Velocity: {}", client.getVelocity());

					if (nearestWaystone.isInRange(player.getPos(), 3)) {

						QuiltedArrow.LOGGER.info("Arrived at location.");
						endRespawnPhase(player);
						final boolean changed = player.changeGameMode(GameMode.SURVIVAL);

						QuiltedArrow.LOGGER.info("Setting gamemode to survival: {} ", changed);
					}
				});

		}
		}



	@Overwrite()
	public static Optional<Vec3d> findRespawnPosition(ServerWorld world, BlockPos pos,
													  float spawnAngle, boolean isSpawnPointSet,
													  boolean alive){
		return Optional.of(
			new Vec3d(
				(double)pos.getX() + 0.5,
				(double)pos.getY() + 0.1,
				(double)pos.getZ() + 0.5));
	}



}




