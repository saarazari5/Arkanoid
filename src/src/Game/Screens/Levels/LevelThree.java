package src.Game.Screens.Levels;

import src.Game.Animations.Sprite;
import src.Game.Screens.LevelInformation;
import src.Game.Screens.LevelThreeSprite;
import src.Geometry.Block;
import src.Geometry.Point;
import src.Geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelThree class.
 * all the methods in the level classes.
 */
public class LevelThree implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> listVelocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(330 + i * 60, 4);
            listVelocities.add(velocity);
        }

        return listVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Welcome to New-York";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(10, 30), 590, 780,
                new Color(102, 255, 102).darker().darker());
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        int elementsInRow;
        int yValue;
        int xValue;

        for (int i = 0; i < 5; i++) {
            elementsInRow = 10 - i;
            yValue = (i * 20) + 150;
            xValue = 290 + (i * 50);
            for (int j = 0; j < elementsInRow; j++) {

                if (j != 0) {
                    listOfBlocks.add(new Block(new Point(xValue + 50, yValue),
                            20, 50, createColor(i)));
                    xValue = xValue + 50;
                }
            }
        }
        return listOfBlocks;
    }

    /**
     * choseColor method.
     * @param loop the index in the loop.
     * @return color to the block.
     */
    private Color createColor(int loop) {
        if (loop == 0) {
            return Color.GRAY;
        }
        if (loop == 1) {
            return Color.red;
        }
        if (loop == 2) {
            return Color.YELLOW;
        }
        if (loop == 3) {
            return Color.BLUE;
        }
        if (loop == 4) {
            return Color.WHITE;
        }
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 35;
    }

    @Override
    public Sprite getScreenAnimation() {
        return new LevelThreeSprite();
    }
}
