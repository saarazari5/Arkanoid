package src.Game.Screens;

import biuoop.DrawSurface;
import src.Game.Animations.Sprite;
import java.awt.Color;

/**
 * ScreenLevelOne class.
 * the animation background of level one in the game.
 */
public class LevelOneSprite implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        /* drawing the lines */
        d.setColor(Color.BLUE);
        d.drawLine(400, 75, 400, 375);
        d.drawLine(250, 225, 550, 225);

        /* three circles */
        d.drawCircle(400, 225, 60);
        d.drawCircle(400, 225, 90);
        d.drawCircle(400, 225, 120);
    }

    @Override
    public void timePassed() {
    }
}