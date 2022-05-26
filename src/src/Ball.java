package src;

import biuoop.DrawSurface;
import java.awt.Color;
import java.lang.ref.WeakReference;

/**
 * class Ball.
 */
public class Ball implements Sprite {
    private Point center;
    private final  int radius;
    private final  Color color;
    private Velocity velocity;
    private WeakReference<GameEnvironment> gameEnvironment;

    /**
     * @param center .
     * @param radius .
     * @param color .
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        if (radius <= 0) {
            this.radius = 5;
        } else {
            this.radius = radius;
        }
        this.color = color;
    }

    /**
     * @param x .
     * @param y .
     * @param radius .
     * @param color .
     */
    public Ball(int x, int y, int radius, Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * @param v velocity setter.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param gameEnvironment setter.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = new WeakReference<>(gameEnvironment);
    }
    /**
     * @param dx of the velocity.
     * @param dy of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return velocity
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * @return x
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return radius
     */
    public int getSize() {
        return radius;
    }

    /**
     * @return color of ball
     */
    public Color getColor() {
        return color;
    }
    /**
     * move the ball according to velocity.
     */
    public void moveOneStep() {
        GameEnvironment gameEnvironment = this.gameEnvironment.get();
        if (this.velocity == null || gameEnvironment == null) {
            return;
        }
        Line line = velocity.convertToLine(center);
        CollisionInfo info = gameEnvironment.getClosestCollision(line);
        if (!info.collisionPoint().equals(Point.DEFAULT)) {
            setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.velocity));
        }
        center = this.velocity.applyToPoint(center);
    }
    /**
     * @param surface of the game.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), radius);
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param game to add the sprite
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * @param game to remove the sprite.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

}
