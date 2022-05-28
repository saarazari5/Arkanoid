package src.Game.Screens;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import src.Game.Animations.Animation;

import java.lang.ref.WeakReference;

/**
 * pause screen.
 */
public class PauseScreen implements Animation {
    private final WeakReference<KeyboardSensor> keyboard;
    private boolean stop;

    /**
     * @param k keyboard sensor to notify when p was pressed.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = new WeakReference<>(k);
        this.stop = false;
    }

    /**
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        KeyboardSensor keyboardSensor = keyboard.get();
        if (keyboardSensor == null) {
            return;
        }
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * @return if screen should be removed
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
