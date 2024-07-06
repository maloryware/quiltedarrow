package io.github.maloryware.quilted_arrow.mixin;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/*
 * This mixin is based off of
 * AwwShoot's Random Respawns Quilt
 * which can be found here:
 * https://modrinth.com/mod/randomrespawn
 *
 */

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
	@Inject(
		method = "findRespawnPosition",
		at = @At("RETURN")
	)

	private static void findRespawnPosition(ServerWorld world, BlockPos pos, float spawnAngle, boolean isSpawnPointSet, boolean alive, CallbackInfoReturnable<Optional<Vec3d>> cir) {

		// Change the player's respawn position to their current one.
		cir.setReturnValue(Optional.of());
	}
}


