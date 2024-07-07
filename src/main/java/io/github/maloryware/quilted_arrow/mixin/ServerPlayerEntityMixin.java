package io.github.maloryware.quilted_arrow.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

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
		super(null, null, null, null);
		throw new IllegalStateException("what the fuck dude you're not supposed to call this wth......");
	}

	@Overwrite
	public @Nullable BlockPos getSpawnPointPosition() {
		return this.getBlockPos();
	}

	@Overwrite
	public float getSpawnAngle() {
		return this.lookDirection;
	}

	@Overwrite
	public RegistryKey<World> getSpawnPointDimension() {
		return getWorld().getRegistryKey();
	}

}






