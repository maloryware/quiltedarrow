package io.github.maloryware.quilted_arrow.component;


import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.RESPAWN;

public class RespawnPhaseComponent implements Component, AutoSyncedComponent {

	private @Nullable RespawnPhase respawnPhase;
	private long deathX, deathY, deathZ, waystoneX, waystoneY, waystoneZ;
	private long[] deathpos, waystonepos;
	public Optional<RespawnPhase> getRespawnPhase() {
		return Optional.ofNullable(respawnPhase);
	}

	// implementation...
	public record RespawnPhase(Vec3d deathPos, Vec3d nearestWaystone)  {

	}

	public void generateRespawnPhaseObject(Entity provider){
		this.respawnPhase = new RespawnPhase(provider.getPos(), (Vec3d) PlayerWaystoneManager.getNearestWaystone((PlayerEntity) provider));

		deathX = (long) provider.getPos().getX();
		deathY = (long) provider.getPos().getY();
		deathZ = (long) provider.getPos().getZ();
		waystoneX = PlayerWaystoneManager.getNearestWaystone((PlayerEntity) provider).getPos().getX();
		waystoneY = PlayerWaystoneManager.getNearestWaystone((PlayerEntity) provider).getPos().getY();
		waystoneZ = PlayerWaystoneManager.getNearestWaystone((PlayerEntity) provider).getPos().getZ();

		deathpos = new long[]{deathX, deathY, deathZ};
		waystonepos = new long[]{waystoneX, waystoneY, waystoneZ};

    }

	public void closePhase(Entity provider){
		this.respawnPhase = null;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		this.deathpos = tag.getLongArray("deathPosition");
		this.waystonepos = tag.getLongArray("waystonePosition");
	}

	public Vec3d getDeathPos(PlayerEntity provider){
		deathpos = RESPAWN.get(provider).deathpos;
		if (deathpos == null) {
			return null;
		}
		else {
			return Vec3d.of(
				BlockPos.create(
					deathpos[0], deathpos[1], deathpos[2]
				)
			);
		}
	}
	public Vec3d getNearestWaystone(PlayerEntity provider){
		waystonepos = RESPAWN.get(provider).waystonepos;
		if (waystonepos == null) {
			return null;
		}
		else {
			return Vec3d.of(
				BlockPos.create(
					waystonepos[0], waystonepos[1], waystonepos[2]
				)
			);
		}
	}


	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putLongArray("deathPosition", deathpos);
		tag.putLongArray("nearestWaystone", waystonepos);
	}



}
