package src;

import src.Game.Game;
import src.Game.GameEnvironment;
import src.Game.SpriteCollection;

/**
 * main method.
 */
public class Ass5Game {
    /**
     * @param args no args.
     */
    public static void main(String[] args) {
        Game game = new Game(new SpriteCollection(), new GameEnvironment());
        game.initialize();
        game.run();
    }
}
