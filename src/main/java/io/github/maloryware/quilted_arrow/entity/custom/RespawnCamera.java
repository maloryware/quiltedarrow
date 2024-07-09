package io.github.maloryware.quilted_arrow.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class RespawnCamera extends PathAwareEntity {
	public RespawnCamera(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

}
