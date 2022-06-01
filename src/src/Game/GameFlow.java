package src.Game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import src.Game.Animations.AnimationRunner;
import src.Game.Animations.KeyPressStoppableAnimation;
import src.Game.Screens.EndWinScreen;
import src.Game.Screens.LevelInformation;
import src.Game.Screens.Levels.EndLoseScreen;

import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {
    private final AnimationRunner runner;
    private final KeyboardSensor ks;
    private final GUI gui;
    private final Counter gameScore = new Counter(0);

    /**
     * constructor method.
     * @param runner the animation runner.
     * @param gui the gui animator.
     */
    public GameFlow(AnimationRunner runner, GUI gui) {
        this.runner = runner;
        this.gui = gui;
        this.ks = gui.getKeyboardSensor();
    }

    /**
     * @param levels levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    new SpriteCollection(),
                    new GameEnvironment(),
                    runner, gui, gameScore);
            level.initialize();
            while (level.numberOfBalls() > 0 && level.numberOfBlocks() > 0) {
                level.run();
            }
            if (level.numberOfBalls() == 0) {
                this.runner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                        new EndLoseScreen(ks, gameScore)));
                gui.close();
                return;
            }
        }
        this.runner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                new EndWinScreen(ks, gameScore)));
        gui.close();
    }

}
