package src;

import biuoop.DrawSurface;

import java.awt.Color;
import java.lang.ref.WeakReference;

/**
 * display score.
 */
public class ScoreIndicator implements Sprite {
    private final WeakReference<Game> game;
    private final WeakReference<Counter> counter;

    /**
     * Constructor.
     * @param game - this game
     * @param counter - this counter
     */
    public ScoreIndicator(Game game, Counter counter) {
        this.game = new WeakReference<>(game);
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
        d.drawText(d.getWidth() / 2, 40, Integer.toString(counter.getValue()), 15);
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
        Game game = this.game.get();
        if (game == null) {
            return;
        }
       game.addSprite(this);
    }

}