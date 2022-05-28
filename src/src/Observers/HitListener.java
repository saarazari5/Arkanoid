package src.Observers;

import src.Geometry.Ball;
import src.Geometry.Block;

/**
 * Hit observer.
 */
public interface HitListener {
    /**
     * @param beingHit This method is called whenever the beingHit object is hit.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}