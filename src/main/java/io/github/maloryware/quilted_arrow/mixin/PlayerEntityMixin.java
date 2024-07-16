package io.github.maloryware.quilted_arrow.mixin;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
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

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.endRespawnPhase;
import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.getRespawnPhase;

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
			value = "INVOKE",
			target = "Lnet/minecraft/entity/player/PlayerEntity;updateWaterSubmersionState()Z"
		)
	)
	public void doRespawnProcedure(CallbackInfo ci) {

		PlayerEntity player = (PlayerEntity) (Object) this;
		Vec3d deathpos = player.getPos();

		try {

				@Nullable Vec3d nearestWaystoneVec = Vec3d.ofCenter(PlayerWaystoneManager.getNearestWaystone(player).getPos());
				@Nullable BlockPos nearestWaystone = PlayerWaystoneManager.getNearestWaystone(player).getPos();
			if (getRespawnPhase(player)
				&& player instanceof ServerPlayerEntity serverPlayerEntity) { // read from the nbt tag

				player.lookAt(player.getCommandSource().getEntityAnchor(), deathpos);
				/* serverPlayerEntity.addVelocity(nearestWaystoneVec.subtract(serverPlayerEntity.getPos()));*/

				player.move(MovementType.PLAYER, nearestWaystoneVec.subtract(serverPlayerEntity.getPos()));
				QuiltedArrow.LOGGER.info("Moving toward location... Velocity: {}", player.getVelocity());

			}


			if (nearestWaystoneVec.isInRange(player.getPos(), 3)
				&& getRespawnPhase(player)
				&& player instanceof ServerPlayerEntity serverPlayerEntity) {

				QuiltedArrow.LOGGER.info("Arrived at location.");
				endRespawnPhase(player);
				final boolean changed = serverPlayerEntity.changeGameMode(GameMode.SURVIVAL);

				QuiltedArrow.LOGGER.info("Setting gamemode to survival: {} ", changed);
			}
		}
		catch(NullPointerException e){
			endRespawnPhase(player);
			QuiltedArrow.LOGGER.info("Exception caught: {}", e.toString());
			player.sendSystemMessage(Text.of("No waystone found."));
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
