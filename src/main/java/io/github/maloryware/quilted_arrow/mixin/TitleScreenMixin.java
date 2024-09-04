package io.github.maloryware.quilted_arrow.mixin;

import io.github.maloryware.quilted_arrow.screen.custom.QButtons;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text title) {
		super(title);
	}

	/*
	@Inject(method = "init", at = @At("TAIL"))
    public void onInit(CallbackInfo ci) {
        QuiltedArrow.LOGGER.info("hi chat :3c ummm this is ur logger speaking; say hello to quilted arrow chat :333");
    }

	 */

	@Inject(method = "initWidgetsNormal", at = @At("RETURN"))
	private void addTitleScreenWidgets(int y, int spacingY, CallbackInfo ci){
		this.addDrawableChild(QButtons.createQTitleScreenButton(this, y));
	}

}
