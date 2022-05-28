package src.Game.Animations;

import biuoop.DrawSurface;
import src.Game.SpriteCollection;

import java.awt.*;
import java.lang.ref.WeakReference;

/**
 * The CountdownAnimation will display the given gameScreen,
 *  for numOfSeconds seconds, and on top of them it will show
 *  a countdown from countFrom back to 1, where each number will
 *  appear on the screen for (numOfSeconds / countFrom) seconds, before
 *  it is replaced with the next one.
  */

public class CountdownAnimation implements Animation {
    private final WeakReference<SpriteCollection> gameScreen;
    private final double numOfSeconds;
    private int countFrom;
    private double currentTime;

    /**
     * @param numOfSeconds .
     * @param countFrom .
     * @param gameScreen .
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = new WeakReference<>(gameScreen);
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * @param countFrom new value.
     */
    public void setCountFrom(int countFrom) {
        this.countFrom = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        SpriteCollection gameScreen = this.gameScreen.get();
        if (gameScreen == null) {
            return;
        }
        gameScreen.drawAllOn(d);
        d.setColor(Color.RED.darker());
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, timerShow(), 50);
    }

    /**
     * timerShow method.
     * calculating the time to show new number.
     * @return the number to show on the screen as a string.
     */
    public String timerShow() {
        double timer = System.currentTimeMillis();
        if ((timer - currentTime) > (numOfSeconds / (this.countFrom + 1) * 1000)) {
            this.currentTime = System.currentTimeMillis();
            this.countFrom--;
        }

        if (this.countFrom != 0) {
            return "" + this.countFrom + "";
        }
        return "";
    }

    @Override
    public boolean shouldStop() {
        return countFrom == 0;
    }
}