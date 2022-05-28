package src.Geometry;

import java.awt.Color;

/**
 * a speciel block designated to remove balls from game.
 */
public class RemoverBlock extends  Block {
    /**
     * @param upperLeft of the block
     * @param height of the block
     * @param width of the block
     * @param color of the block
     */
    public RemoverBlock(Point upperLeft, double height, double width, Color color) {
        super(upperLeft, height, width, color);
    }

    /**
     * @param upperLeft of the block
     * @param height of the block
     * @param width of the block
     */
    public RemoverBlock(Point upperLeft, double height, double width) {
        super(upperLeft, height, width);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        return new Velocity(0, 0);
    }
}
