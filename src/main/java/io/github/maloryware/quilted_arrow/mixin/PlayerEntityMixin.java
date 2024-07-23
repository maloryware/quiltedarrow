package io.github.maloryware.quilted_arrow.mixin;

import com.google.common.collect.ImmutableList;
import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
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

	@Unique
	private static final ImmutableList<Vec3i> VALID_SPAWN_OFFSETS = ImmutableList.of(
		new Vec3i(0, 0, -1),
		new Vec3i(-1, 0, 0),
		new Vec3i(0, 0, 1),
		new Vec3i(1, 0, 0),
		new Vec3i(-1, 0, -1),
		new Vec3i(1, 0, -1),
		new Vec3i(-1, 0, 1),
		new Vec3i(1, 0, 1),
		new Vec3i(0, 1, 0)
	);



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
					Vec3d nearestWaystone = new Vec3d(
						respawnPhase.nearestWaystone().x,
						respawnPhase.nearestWaystone().y + 4,
						respawnPhase.nearestWaystone().z);

					Vec3d target = nearestWaystone.subtract(player.getPos()).normalize();


					double currentDistance = player.getPos().distanceTo(nearestWaystone);
					double totalDistance = deathPos.distanceTo(nearestWaystone);
					double thirdOfDistance = totalDistance / 3;


					player.lookAt(player.getCommandSource().getEntityAnchor(), deathPos);


					if (nearestWaystone.isInRange(player.getPos(), 3)) {
							QuiltedArrow.LOGGER.info("Arrived at location.");
							player.travel(player.getPos());
							endRespawnPhase(player);
							final boolean changed = player.changeGameMode(GameMode.SURVIVAL);

							QuiltedArrow.LOGGER.info("Setting gamemode to survival: {} ", changed);
					}


					else {
						if (currentDistance > thirdOfDistance * 2) {

							player.setVelocity(target.multiply((totalDistance / currentDistance) * 0.5));
							// speed up

						} else if (currentDistance < thirdOfDistance) {

							player.setVelocity(target.multiply((currentDistance / totalDistance)));
							// slow down

						} else {

							player.setVelocity(target.multiply(0.5));
							// keep speed

						}

						client.velocityDirty = true;
						client.velocityModified = true;

						QuiltedArrow.LOGGER.info("Moving toward location... Velocity: {}", client.getVelocity());
					}



				});

			}
		}

	@Unique
	private void findValidRespawnPosition() {

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




