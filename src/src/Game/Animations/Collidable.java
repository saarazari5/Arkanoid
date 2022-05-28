package src.Game.Animations;

import src.Geometry.Ball;
import src.Geometry.Point;
import src.Geometry.Rectangle;
import src.Geometry.Velocity;

/**
 */
public interface Collidable {
    Collidable VOID = new Collidable() {
        @Override
        public Rectangle getCollisionRectangle() {
            return null;
        }

        @Override
        public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
            return currentVelocity;
        }
    };
    /**
     * @return Return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();
    /**
     * @param collisionPoint point
     * @param currentVelocity velocity
     * @param hitter the hitter ball
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
