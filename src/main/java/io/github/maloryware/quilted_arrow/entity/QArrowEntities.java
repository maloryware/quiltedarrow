package io.github.maloryware.quilted_arrow.entity;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import io.github.maloryware.quilted_arrow.entity.custom.RespawnCamera;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class QArrowEntities {

	public static final EntityType<RespawnCamera> RESPAWNCAM = Registry.register(
		Registries.ENTITY_TYPE,
		new Identifier(QuiltedArrow.ID, "respawn_camera"),
		QuiltEntityTypeBuilder.create(SpawnGroup.MISC, RespawnCamera::new)
			.setDimensions(EntityDimensions.fixed(0.3f, 0.3f)).build());

}
