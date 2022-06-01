package src;


import biuoop.GUI;
import src.Game.Animations.AnimationRunner;
import src.Game.GameFlow;
import src.Game.GameUtils;
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
public class GameRunner {
    /**
     * @param args levels to run.
     */
    public static void main(String[] args) {
            List<LevelInformation> listOfLevels = new ArrayList<>();
            LevelInformation level1 = new LevelOne();
            LevelInformation level2 = new LevelTwo();
            LevelInformation level3 = new LevelThree();
            LevelInformation level4 = new LevelFour();
            List<LevelInformation> defaultRun = new ArrayList<>();
            defaultRun.add(level1);
            defaultRun.add(level2);
            defaultRun.add(level3);
            defaultRun.add(level4);

            for (String s : args) {
                if (s.equals("1")) {
                    listOfLevels.add(level1);
                }
                if (s.equals("2")) {
                    listOfLevels.add(level2);
                }
                if (s.equals("3")) {
                    listOfLevels.add(level3);
                }
                if (s.equals("4")) {
                    listOfLevels.add(level4);
                }
            }
            GUI gui = new GUI("GAME", GameUtils.DEF_WIDTH, GameUtils.DEF_HEIGHT);
            AnimationRunner animationRunner = new AnimationRunner(gui);
            GameFlow gameFlow = new GameFlow(animationRunner, gui);

            if (listOfLevels.isEmpty()) {
                gameFlow.runLevels(defaultRun);
            }
            if (args.length == 0) {
                gameFlow.runLevels(defaultRun);
            }
            gameFlow.runLevels(listOfLevels);
    }
}
