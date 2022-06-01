package src.Game.Screens.Levels;

import src.Game.Animations.Sprite;
import src.Game.GameUtils;
import src.Game.Screens.LevelInformation;
import src.Game.Screens.LevelOneSprite;
import src.Geometry.Block;
import src.Geometry.Point;
import src.Geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level one information.
 */
public class LevelOne implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelo = new ArrayList<>();
        Velocity v = new Velocity(0, -3);
        listVelo.add(v);
        return listVelo;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(10, 30), GameUtils.DEF_HEIGHT, GameUtils.DEF_WIDTH, Color.BLACK);
    }

    @Override
    public Sprite getScreenAnimation() {
        return new LevelOneSprite();
    }

    @Override
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<>();
        Block b = new Block(new Point(375, 200), 50, 50, Color.red);
        listBlocks.add(b);
        return listBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
