package src;

/**
 * Collision info object.
 */
 class CollisionInfo {

     private Point collisionPoint;
     private Collidable collisionObject;

    /**
     * @param collisionPoint point
     * @param collisionObject object
     */
     CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return getCollisionPoint();
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return getCollisionObject();
    }

    private Point getCollisionPoint() {
        return collisionPoint;
    }
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }
    private Collidable getCollisionObject() {
        return collisionObject;
    }
    @Override
    public String toString() {
        return "CollisionInfo{" + "collisionPoint=" + collisionPoint + ", collisionObject=" + collisionObject + '}';
    }
}