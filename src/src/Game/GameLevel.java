package src.Game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import src.Game.Animations.Animation;
import src.Game.Animations.AnimationRunner;
import src.Game.Animations.Collidable;
import src.Game.Animations.CountdownAnimation;
import src.Game.Animations.KeyPressStoppableAnimation;
import src.Game.Animations.Sprite;
import src.Game.Screens.LevelInformation;
import src.Game.Screens.PauseScreen;
import src.Geometry.Ball;
import src.Geometry.Block;
import src.Geometry.Paddle;
import src.Geometry.Point;
import src.Geometry.RemoverBlock;
import src.Observers.BallRemover;
import src.Observers.BlockRemover;
import src.Observers.ScoreTrackingListener;
import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Objects;
import static src.Game.GameToolsProvider.DEF_HEIGHT;
import static src.Game.GameToolsProvider.DEF_WIDTH;


/**
 * Game.
 */
public class GameLevel implements Animation {
    private final WeakReference<AnimationRunner> runner;
    private boolean running = true;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private  final Counter blockCounter = new Counter();
    private  final Counter ballsCounter = new Counter();
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private final LevelInformation levelInformation;
    private final WeakReference<KeyboardSensor> keyboardSensor;
    private final WeakReference<GUI> gui;
    private final WeakReference<Counter> gameScore;

    public static final int DEF_WALLS_SIZE = 10;

    /**
     * @param sprites collection.
     * @param environment of the game.
     * @param runner animation runner.
     * @param levelInformation level info.
     * @param gui gui.
     * @param gameScore gameScore.
     */
    public GameLevel(LevelInformation levelInformation,
                     SpriteCollection sprites,
                     GameEnvironment environment,
                     AnimationRunner runner,
                     GUI gui,
                     Counter gameScore) {
        this.sprites = sprites;
        this.environment = environment;
        this.blockRemover = new BlockRemover(this, blockCounter);
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.runner = new WeakReference<>(runner);
        this.levelInformation = levelInformation;
        this.keyboardSensor = new WeakReference<>(gui.getKeyboardSensor());
        this.gui = new WeakReference<>(gui);
        this.gameScore = new WeakReference<>(gameScore);
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
     * @return level name.
     */
    public String getLevelName() {
        return levelInformation.levelName();
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
        addSprite(levelInformation.getBackground());
        addSprite(levelInformation.getScreenAnimation());
        Paddle paddle = new Paddle(new Point(350, 500),
                15,
                levelInformation.paddleWidth(),
                Color.yellow,
                getKeyboard());
        paddle.setPaddleSpeed(levelInformation.paddleSpeed());
        paddle.addToGame(this);
        this.scoreTrackingListener = new ScoreTrackingListener(gameScore.get());
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
        levelInformation.initialBallVelocities().forEach(velocity -> {
            ballsCounter.increase(1);
            Ball ball = new Ball(new Point(400, 400), 5, Color.white);
            ball.setVelocity(velocity);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        });
    }
    /**
     * block init.
     */
    private void initBlocks() {
        levelInformation.blocks().forEach(block -> {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            blockCounter.increase(1);
            block.addToGame(this);
        });
    }

    /**
     * @return gui.
     */
    private GUI getGUI() {
        return this.gui.get();
    }
    /**
     * @return keyboard.
     */
    private KeyboardSensor getKeyboard() {
       return this.keyboardSensor.get();
    }
    /**
     * Initializing the walls.
     */
    public void initWalls() {
        Block leftWall = new Block(new Point(0, 20), DEF_HEIGHT, DEF_WALLS_SIZE);
        Block rightWall = new Block(new Point(DEF_WIDTH - DEF_WALLS_SIZE, 20), DEF_HEIGHT, DEF_WALLS_SIZE);
        RemoverBlock bottomWall = new RemoverBlock(
                new Point(0, DEF_HEIGHT - DEF_WALLS_SIZE + 20), DEF_WALLS_SIZE, DEF_WIDTH);
        Block topWall = new Block(new Point(0, 20), DEF_WALLS_SIZE, DEF_WIDTH);
        bottomWall.addHitListener(ballRemover);
        addCollides(leftWall, rightWall, bottomWall, topWall);
        addSprites(leftWall, rightWall, bottomWall, topWall);
    }
    /**
     * run the game.
     */
    public void run() {
        AnimationRunner runner = this.runner.get();
        if (runner == null) {
            return;
        }
        this.running = true;
        runner.run(new CountdownAnimation(3.0, 3, sprites));
        runner.run(new WeakReference<>(this));
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        AnimationRunner runner = this.runner.get();
        if (runner != null && (this.getKeyboard().isPressed("p") || this.getKeyboard().isPressed("P"))) {
            runner.run(new KeyPressStoppableAnimation(this.getKeyboard(), KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.getKeyboard())));
        }
        if (blockCounter.getValue() == 0) {
            scoreTrackingListener.addScore(100);
            this.running = false;
        }
        if (ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * @return number of balls
     */
    public int numberOfBalls() {
        return ballsCounter.getValue();
    }

    /**
     * @return number of balls
     */
    public int numberOfBlocks() {
        return blockCounter.getValue();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
