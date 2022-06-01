package src;


import biuoop.GUI;
import src.Game.Animations.AnimationRunner;
import src.Game.GameFlow;
import src.Game.Screens.LevelInformation;
import src.Game.Screens.Levels.LevelFour;
import src.Game.Screens.Levels.LevelOne;
import src.Game.Screens.Levels.LevelThree;
import src.Game.Screens.Levels.LevelTwo;


import java.util.ArrayList;
import java.util.List;

/**
 * main method.
 */
public class Ass5Game {
    /**
     * @param args no args.
     */
    public static void main(String[] args) {
        LevelInformation level1 = new LevelOne();
        LevelInformation level2 = new LevelTwo();
        LevelInformation level3 = new LevelThree();
        LevelInformation level4 = new LevelFour();
        List<LevelInformation> defaultRun = new ArrayList<>();
        defaultRun.add(level1);
        defaultRun.add(level2);
        defaultRun.add(level3);
        defaultRun.add(level4);

        GUI gui = new GUI("GAME", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui);
        gameFlow.runLevels(defaultRun);
        gui.close();
    }
}
