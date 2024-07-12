package io.github.maloryware.quilted_arrow.component;


import net.minecraft.nbt.NbtCompound;

public class RespawnComponent implements BoolComponent {

	private boolean isRespawning = false;
	@Override
	public void setBoolKey(boolean bool) {
		isRespawning = bool;
	}

	@Override
	public boolean getBoolKey() {
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
