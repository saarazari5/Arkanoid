package src.Game.Animations;

import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /** draw a frame interface.
     * @param d draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return indicator of when animation should stop.
     */
    boolean shouldStop();
}