package io.github.maloryware.quilted_arrow.mixin;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
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
	public void doRespawnProcedure(CallbackInfo ci){

		PlayerEntity player = (PlayerEntity)(Object)this;



		if(getRespawnPhase(player)){ //something something check player for ccapi component
			player.lookAt(player.getCommandSource().getEntityAnchor(), Vec3d.ofCenter(PlayerWaystoneManager.getNearestWaystone(player).getPos()));

			player.setVelocity(Vec3d.of((PlayerWaystoneManager.getNearestWaystone(player).getPos())).subtract(player.getPos()));
			player.velocityDirty = true;
			QuiltedArrow.LOGGER.info("Moving toward location... Velocity: {}", player.getVelocity());
		}
		/*
		if(player.getBlockPos() == PlayerWaystoneManager.getNearestWaystone(player).getPos()
		|| player.getBlockPos().getX() <= PlayerWaystoneManager.getNearestWaystone(player).getPos().getX() - 1
		|| player.getBlockPos().getY() <= PlayerWaystoneManager.getNearestWaystone(player).getPos().getY() - 1
		|| player.getBlockPos().getZ() <= PlayerWaystoneManager.getNearestWaystone(player).getPos().getZ() - 1){

		 */
		if(Vec3d.ofCenter(PlayerWaystoneManager.getNearestWaystone(player).getPos()).isInRange(player.getPos(), 3)
			&& getRespawnPhase(player)
			&& player instanceof ServerPlayerEntity serverPlayerEntity){
			QuiltedArrow.LOGGER.info("Arrived at location.");
			endRespawnPhase(player);
			serverPlayerEntity.interactionManager.changeGameMode(GameMode.SURVIVAL);
			QuiltedArrow.LOGGER.info("Setting gamemode to survival: {} ", serverPlayerEntity.changeGameMode(GameMode.SURVIVAL));



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
