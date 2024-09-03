package io.github.maloryware.quilted_arrow.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@SuppressWarnings("DataFlowIssue") // there's always a client you fucking buffoon
public class CustomTitleScreen extends Screen {
	Screen parent;
	public CustomTitleScreen(Text title, Screen parent) {
		super(title);
		this.parent = parent;
	}

	@Override
	public void closeScreen(){
		this.client.setScreen(parent);
	}
}
