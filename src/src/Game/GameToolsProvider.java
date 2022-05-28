package src.Game;

import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * a game singleton tools provider like keyboard and such.
 */
public final class GameToolsProvider {
    public static final int DEF_WIDTH = 800;
    public static final int DEF_HEIGHT = 600;
    private static GameToolsProvider shared = null;

    private final GUI gui;

    private GameToolsProvider() {
        this.gui = new GUI("Ass3Game", DEF_WIDTH, DEF_HEIGHT);
    }

    /**
     * @return singleton instance
     */
    public static GameToolsProvider getInstance() {
        if (shared == null) {
            shared = new GameToolsProvider();
        }
        return shared;
    }

    /**
     * @return game keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return gui.getKeyboardSensor();
    }

    /**
     * @return game gui
     */
    public GUI getGui() {
        return gui;
    }
}
