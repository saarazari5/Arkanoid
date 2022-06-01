package src.Observers;

import src.Game.Counter;
import src.Game.GameLevel;
import src.Geometry.Ball;
import src.Geometry.Block;

import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * Ball remover observer.
 */
public class BallRemover implements HitListener {

    private final WeakReference<GameLevel> game;
    private final WeakReference<Counter> remainingBalls;

    /**
     * @param gameLevel the game manager.
     * @param remainingBalls the balls left on the field.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.game = new WeakReference<>(gameLevel);
        this.remainingBalls = new WeakReference<>(remainingBalls);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        GameLevel gameLevel = this.game.get();
        if (gameLevel == null) {
            return;
        }
        hitter.removeFromGame(gameLevel);
        Objects.requireNonNull(remainingBalls.get()).decrease(1);
    }
}
