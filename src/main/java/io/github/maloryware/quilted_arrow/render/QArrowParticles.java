package io.github.maloryware.quilted_arrow.render;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import net.minecraft.util.registry.Registry;


public class QArrowParticles {

	public static final DefaultParticleType SKULL_PARTICLE = FabricParticleTypes.simple();

	public static void register(ModContainer mod){
			Registry.register(Registry.PARTICLE_TYPE, new Identifier(mod.metadata().id(), "skull_particle"),SKULL_PARTICLE);

		}
	//TODO: create factory, replace the EndRodParticle factory with the custom one
	public static void registerClient(){
		ParticleFactoryRegistry.getInstance().register(SKULL_PARTICLE, EndRodParticle.Factory::new);


	}
}
