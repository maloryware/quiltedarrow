package io.github.maloryware.quilted_arrow.mixin;

import com.mojang.authlib.GameProfile;
import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

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


	/**
	 * @author Maloryware
	 * @reason we ain't needin this shit
	 */
	@Overwrite
	public @Nullable BlockPos getSpawnPointPosition() {
		QuiltedArrow.LOGGER.info("Spawnpoint position set to {}", this.getLastDeathPos().toString());
		startRespawnPhase(this);
		return this.getBlockPos();
	}

	/**
	 * @author Maloryware
	 * @reason who car dawg
	 */
	@Overwrite
	public float getSpawnAngle() {
		QuiltedArrow.LOGGER.info("Spawn angle set to {}", this.getYaw());
		return this.getYaw();

	}

	/**
	 * @author Maloryware
	 * @reason wah wah we need javadocs baby head lookin ahh
	 */
	@Overwrite
	public RegistryKey<World> getSpawnPointDimension() {
		QuiltedArrow.LOGGER.info("Spawnpoint dimension set to {}", this.getWorld().getRegistryKey().toString());
		return getWorld().getRegistryKey();
	}

	/**
	 * @author Maloryware
	 * @reason take a wild fucking guess at what this does
	 */
	@Overwrite
	public boolean isSpawnPointSet() {
		return true;
	}
}






