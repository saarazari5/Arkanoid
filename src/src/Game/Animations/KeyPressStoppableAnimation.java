package src.Game.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.lang.ref.WeakReference;

/**
 * KeyPressStoppableAnimation class.
 * arrange all the animations that are has the key press for stopping the animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final WeakReference<KeyboardSensor> keyboard;
    private final Animation animation;
    private final String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor method.
     * @param keyboard of the keyboard.
     * @param key the string.
     * @param animation the animation that is being run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.keyboard = new WeakReference<>(keyboard);
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        KeyboardSensor keyboard = this.keyboard.get();
        if (keyboard == null) {
            return;
        }
        if (keyboard.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}