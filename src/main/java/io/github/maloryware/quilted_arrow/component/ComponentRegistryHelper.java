package io.github.maloryware.quilted_arrow.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ComponentRegistryHelper implements EntityComponentInitializer {
	public static final ComponentKey<BoolComponent> RESPAWN =
		ComponentRegistry.getOrCreate(new Identifier(QuiltedArrow.ID, "respawncomponent"), BoolComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(RESPAWN, player -> new RespawnComponent(), RespawnCopyStrategy.ALWAYS_COPY);

	}

	public static void manageRespawning(Entity provider){
		boolean isRespawing = RESPAWN.get(provider).get();
	}
}
