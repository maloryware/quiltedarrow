package io.github.maloryware.quilted_arrow.mixin;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.*;

@Debug(
	export = true
)

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {


	public PlayerEntityMixin(EntityType<?> variant, World world) {
		super(variant, world);
	}

	public double distanceBetween(Vec3d first, Vec3d second){
		float f = (float) (first.getX() - second.getX());
		float g = (float) (first.getY() - second.getY());
		float h = (float) (first.getZ() - second.getZ());
		return MathHelper.sqrt(f * f + g * g + h * h);

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

		PlayerEntity player = (PlayerEntity) (Object) this;
		@Nullable Vec3d deathPos = RESPAWN.get(player).getDeathPos(player);
		@Nullable Vec3d nearestWaystone = RESPAWN.get(player).getNearestWaystone(player);

		try {

			if (getRespawnPhase(player).isPresent()) {


				player.lookAt(player.getCommandSource().getEntityAnchor(), nearestWaystone);

				if(distanceBetween(player.getPos(), nearestWaystone) < (distanceBetween(deathPos, nearestWaystone))/3){
					// speed up
				}
				else if(distanceBetween(player.getPos(), nearestWaystone) > 2 * ((distanceBetween(deathPos, nearestWaystone))/3)){
					// slow down
				}
				else {
					// keep speed
				}

				QuiltedArrow.LOGGER.info("Moving toward location... Velocity: {}", player.getVelocity());

			}


			if (nearestWaystone.isInRange(player.getPos(), 3)
				&& getRespawnPhase(player).isPresent()
				&& player instanceof ServerPlayerEntity serverPlayerEntity) {

				QuiltedArrow.LOGGER.info("Arrived at location.");
				endRespawnPhase(player);
				final boolean changed = serverPlayerEntity.changeGameMode(GameMode.SURVIVAL);

				QuiltedArrow.LOGGER.info("Setting gamemode to survival: {} ", changed);
			}
		}

		catch(NullPointerException | ArrayIndexOutOfBoundsException e){
			if(getRespawnPhase(player).isPresent()) {
				endRespawnPhase(player);
				QuiltedArrow.LOGGER.info("Exception caught: {}", e.toString());
				player.sendSystemMessage(Text.of("No waystone found."));
			}
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
