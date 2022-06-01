package src.Game.Screens;

import biuoop.DrawSurface;
import src.Game.Animations.Sprite;

import java.awt.Color;

/**
 * ScreenLevelTwo class.
 * the animation background of level two in the game.
 */
public class LevelTwoSprite implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(255, 255, 153));

        /* drawing the lines from point (100,100) */
        for (int i = 1; i < 75; i++) {
            d.drawLine(100, 100, i * 10, 200);
        }

        /* creating the sun by three circles */
        d.setColor(new Color(255, 255, 153));
        d.fillCircle(100, 100, 60);
        d.setColor(new Color(255, 255, 0));
        d.fillCircle(100, 100, 55);
        d.setColor(new Color(255, 204, 0));
        d.fillCircle(100, 100, 50);
    }

    @Override
    public void timePassed() {

    }
}
