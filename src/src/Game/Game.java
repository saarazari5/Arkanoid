package src.Game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import src.Game.Animations.*;
import src.Game.Screens.PauseScreen;
import src.Geometry.Ball;
import src.Geometry.Block;
import src.Geometry.Paddle;
import src.Geometry.Point;
import src.Observers.BallRemover;
import src.Observers.BlockRemover;
import src.Observers.ScoreTrackingListener;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static src.Game.GameToolsProvider.DEF_HEIGHT;
import static src.Game.GameToolsProvider.DEF_WIDTH;


/**
 * Game.
 */
public class Game implements Animation {
    private final AnimationRunner runner;
    private boolean running = true;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private  final Counter blockCounter = new Counter();
    private  final Counter ballsCounter = new Counter();
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;

    public static final int DEF_WALLS_SIZE = 10;

    /**
     * @param sprites collection.
     * @param environment of the game.
     */
    public Game(SpriteCollection sprites, GameEnvironment environment) {
        this.sprites = sprites;
        this.environment = environment;
        this.blockRemover = new BlockRemover(this, blockCounter);
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.runner = new AnimationRunner(getGUI(), 60, new Sleeper());
    }

    /**
     * init.
     */
    public Game() {
       this(new SpriteCollection(), new GameEnvironment());
    }

    /**
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        if (Objects.nonNull(environment) && Objects.nonNull(c)) {
            environment.addCollidable(c);
        }
    }

    /**
     * @param c var args of collidable
     */
    public void addCollides(Collidable... c) {
        Arrays.stream(c).filter(Objects::nonNull)
                .forEach(this::addCollidable);
    }

    /**
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        if (Objects.nonNull(sprites) && Objects.nonNull(s)) {
            sprites.addSprite(s);
        }
    }

    /**
     * @param s sprites
     */
    public void addSprites(Sprite... s) {
        Arrays.stream(s).filter(Objects::nonNull)
                .forEach(this::addSprite);
    }

    /**
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        environment.getCollides().remove(c);
    }

    /**
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        Paddle paddle = new Paddle(new Point(350, 500), 15, 100, Color.BLACK, getKeyboard());
        paddle.addToGame(this);
        Counter points = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(points);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this, scoreTrackingListener.getCurrentScore());
        scoreIndicator.addToGame();
        initBlocks();
        initWalls();
        initBalls();
    }
    /**
     * Initializing the balls.
     */
    public void initBalls() {
        for (int i = 0; i < 3; i++) {
            ballsCounter.increase(1);
            Ball ball = new Ball(new Point(100, 500), 5, Color.white);
            ball.setVelocity(new Random().nextInt(8), -5);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
    }
    /**
     * block init.
     */
    private void initBlocks() {
        Block block;
        for (int i = 0; i < 6; i++) {
            Random rand = new Random();
            int numOfBlocks = 12;
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            for (int j = 0; j < numOfBlocks - i; j++) {
                block = new Block(new Point(DEF_WIDTH - (80 + j * 50),
                        30 + (20 * (i + 1)) + 20 * 5), 20, 50, randomColor);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                blockCounter.increase(1);
                block.addToGame(this);
            }
        }
    }

    /**
     * @return gui.
     */
    private GUI getGUI() {
        return GameToolsProvider.getInstance().getGui();
    }
    /**
     * @return keyboard.
     */
    private KeyboardSensor getKeyboard() {
       return GameToolsProvider.getInstance().getKeyboard();
    }
    /**
     * Initializing the walls.
     */
    public void initWalls() {
        Block leftWall = new Block(new Point(0, 0), DEF_HEIGHT, DEF_WALLS_SIZE);
        Block rightWall = new Block(new Point(DEF_WIDTH - DEF_WALLS_SIZE, 0), DEF_HEIGHT, DEF_WALLS_SIZE);
        Block bottomWall = new Block(
                new Point(0, DEF_HEIGHT - DEF_WALLS_SIZE + 20), DEF_WALLS_SIZE, DEF_WIDTH);
        Block topWall = new Block(new Point(0, 0), DEF_WALLS_SIZE, DEF_WIDTH);
        bottomWall.addHitListener(ballRemover);
        addCollides(leftWall, rightWall, bottomWall, topWall);
        addSprites(leftWall, rightWall, bottomWall, topWall);
    }
    /**
     * run the game.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(3.0, 3, sprites));
        this.runner.run(new WeakReference<>(this));
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (getKeyboard().isPressed("p")) {
            this.runner.run(new PauseScreen(getKeyboard()));
        }

        if (blockCounter.getValue() == 0) {
            scoreTrackingListener.addScore(100);
            this.running = false;
            getGUI().close();
        }
        if (ballsCounter.getValue() == 0) {
            this.running = false;
            getGUI().close();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
