package io.github.maloryware.quilted_arrow.mixin;

import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Debug(
	export = true
)

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {


	@Shadow
	public abstract int getEnchantmentTableSeed();

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
		if(){
			this.setVelocity(Vec3d.of(PlayerWaystoneManager.getNearestWaystone((PlayerEntity)(Object)this).getPos()));
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
