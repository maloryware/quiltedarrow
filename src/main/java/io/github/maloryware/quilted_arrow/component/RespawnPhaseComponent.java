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

	private double deathposX;
	private double deathposY;
	private double deathposZ;
	private int waystoneposX;
	private int waystoneposY;
	private int waystoneposZ;

	public Optional<RespawnPhase> getRespawnPhase() {
		return Optional.ofNullable(respawnPhase);
	}

	// implementation...
	public record RespawnPhase(Vec3d deathPos, Vec3d nearestWaystone)  {

	}

	public void generateRespawnPhaseObject(Entity provider){
		this.respawnPhase = new RespawnPhase(provider.getPos(), (Vec3d) PlayerWaystoneManager.getNearestWaystone((PlayerEntity) provider));

    }

	public void closePhase(Entity provider){
		this.respawnPhase = null;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {

		this.deathposX = tag.getDouble("deathPositionX");
		this.deathposY = tag.getDouble("deathPositionY");
		this.deathposZ = tag.getDouble("deathPositionZ");
		this.waystoneposX = tag.getInt("waystonePositionX");
		this.waystoneposY = tag.getInt("waystonePositionY");
		this.waystoneposZ = tag.getInt("waystonePositionZ");

	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		if(respawnPhase == null){
			tag.remove("deathPositionX");
			tag.remove("deathPositionY");
			tag.remove("deathPositionZ");

			tag.remove("waystonePositionX");
			tag.remove("waystonePositionY");
			tag.remove("waystonePositionZ");
		}
		else {
			tag.putDouble("deathPositionX", this.respawnPhase.deathPos.x);
			tag.putDouble("deathPositionY", this.respawnPhase.deathPos.y);
			tag.putDouble("deathPositionZ", this.respawnPhase.deathPos.z);

			tag.putDouble("waystonePositionX", this.respawnPhase.nearestWaystone.x);
			tag.putDouble("waystonePositionY", this.respawnPhase.nearestWaystone.y);
			tag.putDouble("waystonePositionZ", this.respawnPhase.nearestWaystone.z);
		}

	}

	/*

	// huh, unsure what to do here


	public Vec3d getDeathPos(PlayerEntity provider){
		return Vec3d.of(BlockPos.create(
			RESPAWN.get(provider),
			RESPAWN.get(provider).deathposY,
			RESPAWN.get(provider).deathposZ
			));
	}

	public Vec3d getNearestWaystone(PlayerEntity provider){
		return Vec3d.of(BlockPos.create(
			RESPAWN.get(provider).waystoneposX,
			RESPAWN.get(provider).waystoneposY,
			RESPAWN.get(provider).waystoneposZ
		));
	}


	 */


}
