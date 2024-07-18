package io.github.maloryware.quilted_arrow.component;


import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RespawnPhaseComponent implements Component, AutoSyncedComponent {

	private @Nullable RespawnPhase respawnPhase;
	public Optional<RespawnPhase> getRespawnPhase() {
		return Optional.ofNullable(respawnPhase);
	}

	// implementation...
	public record RespawnPhase(Vec3d deathPos, Vec3d nearestWaystone)  {}

	public void generateRespawnPhaseObject(Entity provider){
		this.respawnPhase = new RespawnPhase(provider.getPos(), (Vec3d) PlayerWaystoneManager.getNearestWaystone((PlayerEntity) provider));

	}

	@Override
	public void readFromNbt(NbtCompound tag) {

	}

	@Override
	public void writeToNbt(NbtCompound tag) {

	}



}
