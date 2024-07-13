package io.github.maloryware.quilted_arrow.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.getRespawnPhase;
import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.startRespawnPhase;

/*
 * This mixin is based off of
 * AwwShoot's Random Respawns Quilt
 * which can be found here:
 * https://modrinth.com/mod/randomrespawn
 *
 */

//TODO: change all of this into a @WrapOperation dependent on a custom setting

@Mixin(net.minecraft.server.network.ServerPlayerEntity.class)
@Debug(export = true)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {



	private ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
		throw new IllegalStateException("what the fuck dude you're not supposed to call this wth......");
	}

	@Overwrite
	public @Nullable BlockPos getSpawnPointPosition() {
		QuiltedArrow.LOGGER.info("Spawnpoint position set to {}", this.getLastDeathPos().toString());
		startRespawnPhase(this);
		return this.getBlockPos();
	}

	@Overwrite
	public float getSpawnAngle() {
		QuiltedArrow.LOGGER.info("Spawn angle set to {}", this.getYaw());
		return this.getYaw();

	}

	@Overwrite
	public RegistryKey<World> getSpawnPointDimension() {
		QuiltedArrow.LOGGER.info("Spawnpoint dimension set to {}", this.getWorld().getRegistryKey().toString());
		return getWorld().getRegistryKey();
	}

	@Overwrite
	public boolean isSpawnPointSet() {
		return true;
	}
}






