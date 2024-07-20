package io.github.maloryware.quilted_arrow.component;


import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.RESPAWN;

public class RespawnPhaseComponent implements Component, AutoSyncedComponent {

	private @Nullable RespawnPhase respawnPhase;


	public Optional<RespawnPhase> getRespawnPhase() {
		return Optional.ofNullable(this.respawnPhase);
	}

	// implementation...
	public record RespawnPhase(Vec3d deathPos, Vec3d nearestWaystone)  {

	}

	public void generateRespawnPhaseObject(Entity provider){
		this.respawnPhase = new RespawnPhase(provider.getPos(), Vec3d.of(PlayerWaystoneManager.getNearestWaystone((PlayerEntity) provider).getPos()));

    }

	public void closePhase(){
		this.respawnPhase = null;
	}

	@Override
	public void writeToNbt(@NotNull NbtCompound tag) {

		if (this.respawnPhase != null) {

			final var respawnPhaseNbt = new NbtCompound();

			respawnPhaseNbt.putDouble("deathPositionX", this.respawnPhase.deathPos().getX());
			respawnPhaseNbt.putDouble("deathPositionY", this.respawnPhase.deathPos().getY());
			respawnPhaseNbt.putDouble("deathPositionZ", this.respawnPhase.deathPos().getZ());

			respawnPhaseNbt.putDouble("waystonePositionX",this.respawnPhase.nearestWaystone().getX());
			respawnPhaseNbt.putDouble("waystonePositionY",this.respawnPhase.nearestWaystone().getY());
			respawnPhaseNbt.putDouble("waystonePositionZ",this.respawnPhase.nearestWaystone().getZ());

			tag.put("quilted_arrow:respawnPhase", respawnPhaseNbt);
		}

		else {
			tag.remove("quilted_arrow:respawnPhase");
		}
	}

	@Override
	public void readFromNbt(NbtCompound tag) {

		if (tag.get("quilted_arrow:respawnPhase") instanceof NbtCompound respawnPhaseNbt) {

			final double deathPositionX = respawnPhaseNbt.getDouble("deathPositionX");
			final double deathPositionY = respawnPhaseNbt.getDouble("deathPositionY");
			final double deathPositionZ = respawnPhaseNbt.getDouble("deathPositionZ");

			final double waystonePositionX = respawnPhaseNbt.getDouble("waystonePositionX");
			final double waystonePositionY = respawnPhaseNbt.getDouble("waystonePositionY");
			final double waystonePositionZ = respawnPhaseNbt.getDouble("waystonePositionZ");

			this.respawnPhase = new RespawnPhase(
				new Vec3d(deathPositionX,deathPositionY,deathPositionZ),
				new Vec3d(waystonePositionX, waystonePositionY, waystonePositionZ)
			);

		}
	}

	public Vec3d getDeathPos(){
		return this.respawnPhase.deathPos;
	}

	public Vec3d getNearestWaystone(){
		return this.respawnPhase.nearestWaystone;
	}





}
