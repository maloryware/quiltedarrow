package io.github.maloryware.quilted_arrow.mixin;


import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerWaystoneManager.class)
public abstract class WaystoneActivationMixin {

	@Inject(
		method = "activateWaystone",
		at = @At(value = "HEAD"))

	private static void activateWaystoneEffect(PlayerEntity player, IWaystone waystone, CallbackInfo ci){
		// player.getServer().getCommandManager().executePrefixedCommand(player.getCommandSource(), "photon fx");
	}



}
