package io.github.maloryware.quilted_arrow.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.maloryware.quilted_arrow.QuiltedArrow;
import io.github.maloryware.quilted_arrow.component.RespawnPhaseComponent.RespawnPhase;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ComponentRegistryHelper implements EntityComponentInitializer {
	public static final ComponentKey<RespawnPhaseComponent> RESPAWN =
		ComponentRegistry.getOrCreate(new Identifier(QuiltedArrow.ID, "respawncomponent"), RespawnPhaseComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(RESPAWN, player -> new RespawnPhaseComponent(), RespawnCopyStrategy.ALWAYS_COPY);

	}

	public static RespawnPhase getRespawnPhase(Entity provider){
        return null;
    }

	public static void endRespawnPhase(Entity provider){

	}
	public static void startRespawnPhase(Entity provider){
		RESPAWN.get(provider).generateRespawnPhaseObject(provider);
	}




}



