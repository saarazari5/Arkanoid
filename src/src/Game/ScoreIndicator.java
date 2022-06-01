package src.Game;

import biuoop.DrawSurface;
import src.Game.Animations.Sprite;
import java.awt.Color;
import java.lang.ref.WeakReference;

/**
 * display score.
 */
public class ScoreIndicator implements Sprite {
    private final WeakReference<GameLevel> game;
    private final WeakReference<Counter> counter;

    /**
     * Constructor.
     * @param gameLevel - this game
     * @param counter - this counter
     */
    public ScoreIndicator(GameLevel gameLevel, Counter counter) {
        this.game = new WeakReference<>(gameLevel);
        this.counter = new WeakReference<>(counter);
    }

    /**
     * draw the Score.
     * @param d - surface
     */
    public void drawOn(DrawSurface d) {
        Counter counter = this.counter.get();
        if (counter == null) {
            return;
        }
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2, 15, "Score: ".concat(Integer.toString(counter.getValue())), 15);
        GameLevel game = this.game.get();
        if (game != null) {
            d.drawText(d.getWidth() / 8, 15, "Level Name: ".concat(game.getLevelName()), 15);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

    }

    /**
     * add the ball to the game.
     */
    public void addToGame() {
        GameLevel gameLevel = this.game.get();
        if (gameLevel == null) {
            return;
        }
        gameLevel.addSprite(this);
    }

}