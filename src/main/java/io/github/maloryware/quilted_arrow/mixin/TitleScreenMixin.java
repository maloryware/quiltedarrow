package io.github.maloryware.quilted_arrow.mixin;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	@Inject(method = "init", at = @At("TAIL"))
	public void onInit(CallbackInfo ci) {
		QuiltedArrow.LOGGER.info("Quilted Arrow Essentials mixin initialized - hope and pray nothing breaks.");
	}
}
