package src.Game.Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.lang.ref.WeakReference;

/**
 * Animation runner class.
 */
public class AnimationRunner {

    private final WeakReference<GUI> gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * @param gui .
     * @param framesPerSecond .
     * @param sleeper .
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = new WeakReference<>(gui);
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * run an animation in a certain timeframe.
     * @param animation object.
     */
    public void run(Animation animation) {
         performAnimation(animation);
    }

    /**
     * run an animation in a certain timeframe.
     * @param weakAnimation object.
     */
    public void run(WeakReference<Animation> weakAnimation) {
        Animation animation = weakAnimation.get();
        if (animation == null) {
            return;
        }
        performAnimation(animation);
    }

    private void performAnimation(Animation animation) {
        GUI gui = this.gui.get();
        if (gui == null) {
            return;
        }
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
