package src;

import java.util.LinkedList;
import java.util.List;


/**
 * GameEnv.
 */
public class GameEnvironment {
    private final List<Collidable> collides = new LinkedList<>();

    /**
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        collides.add(c);
    }

    /**
     * @param trajectory line.
     * @return collisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInfo = null;
        for (Collidable collidable : this.collides) {
            Rectangle block = collidable.getCollisionRectangle();
            Point closestCollision = trajectory.closestIntersectionToStartOfLine(block);
            if (closestCollision != null) {
                if (collisionInfo == null) {
                    collisionInfo = new CollisionInfo(closestCollision, collidable);
                } else {
                    if (trajectory.start().distance(closestCollision)
                            < trajectory.start().distance(collisionInfo.collisionPoint())) {
                        collisionInfo = new CollisionInfo(closestCollision, collidable);
                    }
                }
            }
        }
        return collisionInfo;
    }
    /**
     * @return collides
     */
    public List<Collidable> getCollides() {
        return collides;
    }
}