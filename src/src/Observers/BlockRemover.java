package src.Observers;

import src.Game.Counter;
import src.Game.Game;
import src.Geometry.Ball;
import src.Geometry.Block;

import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private final WeakReference<Game> game;
    private final WeakReference<Counter> remainingBlocks;

    /**
     * @param game where blocks are in.
     * @param remainingBlocks the starting remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = new WeakReference<>(game);
        this.remainingBlocks = new WeakReference<>(remainingBlocks);
    }

    /**
     * @return the remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks.get();
    }

    /**
     * Blocks that are hit should be removed.
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the hit block.
     * @param hitter the ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Game game = this.game.get();
        if (game == null) {
            return;
        }
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        Objects.requireNonNull(remainingBlocks.get()).decrease(1);
    }
}
