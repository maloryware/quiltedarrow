package io.github.maloryware.quilted_arrow.screen.custom;

import io.github.maloryware.quilted_arrow.screen.CustomTitleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

public class QButtons {

	public static ButtonWidget createQTitleScreenButton(int x, int y, int width, int height, Text msg, Screen parent) {
		MinecraftClient client = parent.getClient();
		return ButtonWidget.builder(Text.of("&lQA"), (btn) -> { // "Go to new title screen"
			client.getToastManager().add(SystemToast.create(
				client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("Moved to new title screen."), Text.of("To return, press [ESCAPE]."))
			);
			client.setScreen(new CustomTitleScreen(Text.of("Huh whuh"), parent));
		}).positionAndSize(x, y, width, height).build();
	}

	public static ButtonWidget createQTitleScreenButton(Screen parent, int anchor) {
		MinecraftClient client = parent.getClient();
		return ButtonWidget.builder(Text.of("Go to new title screen"), (btn) -> {
			client.getToastManager().add(SystemToast.create(
				client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("Moved to new title screen."), Text.of("To return, press [ESCAPE]."))
			);
			client.setScreen(new CustomTitleScreen(Text.of("Huh whuh"), parent));
		})
			.tooltip(Tooltip.create(Text.of("Sends you to the new menu.")))
			.positionAndSize(parent.width/2 + 100, anchor, 20, 20).build();
	}


}
