package src.Game.Screens;

import src.Game.Animations.Sprite;
import src.Geometry.Block;
import src.Geometry.Velocity;

import java.util.List;

/**
 * level information.
 */
public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     *      Note that initialBallVelocities().size() == numberOfBalls().
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * @return  the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return  a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     *   before the level is considered to be "cleared".
     *   This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();

    /**
     * @return the screen extra animation.
     */
    Sprite getScreenAnimation();
}