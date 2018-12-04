package me.vitoremanoel.vikingsinvader.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.vitoremanoel.vikingsinvader.VikingsInvader;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "VikingsInvader";
		config.width = 1280;
		config.addIcon("icon.png", Files.FileType.Internal);
		config.height = 720;
		new LwjglApplication(new VikingsInvader(), config);
	}
}
