package src.Game.Screens;

import biuoop.DrawSurface;
import src.Game.Animations.Sprite;
import java.awt.Color;

/**
 * ScreenLevelFour class.
 * the animation background of level four in the game.
 */
public class LevelFourSprite implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GRAY.brighter());
        for (int i = 0; i < 20; i++) {
            d.drawLine(130 + (i * 5), 380, 60 + (i * 5), 600);
        }
        for (int i = 0; i < 12; i++) {
            d.drawLine(580 + (i * 8), 500, 550 + (i * 8), 600);
        }
        d.fillCircle(575, 520, 32);
        d.fillCircle(210, 400, 35);
        d.fillCircle(175, 430, 28);
        d.fillCircle(560, 490, 30);
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(110, 390, 30);
        d.fillCircle(125, 420, 32);
        d.fillCircle(160, 385, 35);
        d.fillCircle(660, 500, 35);
        d.fillCircle(625, 530, 28);
        d.fillCircle(610, 485, 35);
    }

    @Override
    public void timePassed() {

    }
}
