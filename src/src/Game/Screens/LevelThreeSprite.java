package src.Game.Screens;

import biuoop.DrawSurface;
import src.Game.Animations.Sprite;

import java.awt.Color;

/**
 * ScreenLevelThree class.
 * the animation background of level three in the game.
 */
public class LevelThreeSprite implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        /* drawing the building */
        d.setColor(new Color(153, 153, 153));
        d.fillRectangle(117, 250, 15, 150);
        d.setColor(new Color(102, 102, 102));
        d.fillRectangle(100, 375, 50, 75);
        d.setColor(new Color(51, 51, 51));
        d.fillRectangle(75, 425, 100, 200);
        d.setColor(new Color(204, 204, 204));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(85 + (j * 18), 435 + (i * 35), 10, 20);
            }
        }
        d.setColor(new Color(255, 102, 0));
        d.fillCircle(123, 250, 15);
        d.setColor(new Color(153, 0, 0));
        d.fillCircle(123, 250, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(123, 250, 7);
    }

    @Override
    public void timePassed() {
    }
}
