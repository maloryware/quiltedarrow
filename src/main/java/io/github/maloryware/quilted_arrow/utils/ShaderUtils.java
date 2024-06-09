package io.github.maloryware.quilted_arrow.utils;


import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// Right now this is gonna go unused. I got a bit ahead of myself.
// I'll loop around to this once I have nothing of bigger importance to do.
// Priority tasks are getting the storyline done so we can start on working on
// the necessary stuff for it.
@SuppressWarnings("all")

@Mixin(GameRenderer.class)
public interface ShaderUtils {
	@Invoker("loadShader")
	static void invokeLoadShader(Identifier id) {
		throw new AssertionError();
	}
}
