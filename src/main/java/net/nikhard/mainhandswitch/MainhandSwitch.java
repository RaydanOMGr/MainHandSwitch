package net.nikhard.mainhandswitch;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Arm;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainhandSwitch implements ClientModInitializer {
	public static final String MOD_ID = "mainhand-switch";
	public static final Logger LOGGER = LoggerFactory.getLogger("Mainhand Switch");

	public static final KeyBinding BINDING = KeyBindingHelper.registerKeyBinding(
			new KeyBinding("Switch main hand", GLFW.GLFW_KEY_CAPS_LOCK, KeyBinding.INVENTORY_CATEGORY)
	);

	public boolean clicked; // Anti-trrrrrrrr variable

	@Override
	public void onInitialize() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (!BINDING.isPressed()) {
				clicked = false;
			}
			if (BINDING.isPressed()){
				if (client.player.getMainArm() == Arm.RIGHT && !clicked) {
					client.player.setMainArm(Arm.LEFT);
				} else if (client.player.getMainArm() == Arm.LEFT && !clicked) {
					client.player.setMainArm(Arm.RIGHT);
				}
				clicked = true;
			}
		});
	}
}
