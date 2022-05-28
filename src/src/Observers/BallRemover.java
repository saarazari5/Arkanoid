package src.Observers;

import src.Game.Counter;
import src.Game.Game;
import src.Geometry.Ball;
import src.Geometry.Block;

import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * Ball remover observer.
 */
public class BallRemover implements HitListener {

    private final WeakReference<Game> game;
    private final WeakReference<Counter> remainingBalls;

    /**
     * @param game the game manager.
     * @param remainingBalls the balls left on the field.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = new WeakReference<>(game);
        this.remainingBalls = new WeakReference<>(remainingBalls);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Game game = this.game.get();
        if (game == null) {
            return;
        }
        hitter.removeFromGame(game);
        Objects.requireNonNull(remainingBalls.get()).decrease(1);
    }
}
