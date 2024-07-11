package io.github.maloryware.quilted_arrow.component;


import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;

public class RespawnComponent implements BoolComponent, AutoSyncedComponent {

	private boolean isRespawning = false;
	@Override
	public void set(boolean bool) {
		isRespawning = bool;
	}

	@Override
	public boolean get() {
		return this.isRespawning;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		this.isRespawning = tag.getBoolean("isRespawning");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("isRespawning", this.isRespawning);
	}
}
