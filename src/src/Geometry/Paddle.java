package src.Geometry;

import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.Arrays;

import static src.Game.GameUtils.DEF_WIDTH;

/**
 * Paddle.
 */
public class Paddle extends Block  {
    private boolean isMoving = false;
    private final KeyboardSensor keyboard;

    private static final int NUM_OF_REGIONS = 5;
    private static final int TWO_PI = 360;
    private static final int DEGREES = 30;
    private int paddleSpeed = 10;

    /**
     * @param upperLeft of the paddle
     * @param height of the paddle
     * @param width of the paddle
     * @param color of the paddle
     * @param keyboard observer for movement
     */
    public Paddle(Point upperLeft, double height, double width, Color color, KeyboardSensor keyboard) {
        super(upperLeft, height, width, color);
        this.keyboard = keyboard;
    }

    /**
     * @param upperLeft of the paddle
     * @param height of the paddle
     * @param width of the paddle
     * @param keyboard observer for movement
     */
    public Paddle(Point upperLeft, double height, double width, KeyboardSensor keyboard) {
        super(upperLeft, height, width);
        this.keyboard = keyboard;
    }

    /**
     * moveLeft.
     */
    public void moveLeft() {
        Point oldUpperLeft = getUpperLeft();
        Point newUpperLeft = new Point(oldUpperLeft.getX() < paddleSpeed ? oldUpperLeft.getX() : oldUpperLeft.getX()
                - paddleSpeed,
                oldUpperLeft.getY());
        setUpperLeft(newUpperLeft);
    }

    /**
     * moveRIGHT.
     */
    public void moveRight() {
        Point oldUpperLeft = getUpperLeft();
        if (getWidth() + oldUpperLeft.getX() >= DEF_WIDTH) {
            return;
        }
        setUpperLeft(new Point(oldUpperLeft.getX() + paddleSpeed, oldUpperLeft.getY()));
    }

    /**
     * @param paddleSpeed setter.
     */
    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        for (int i = 0; i < RectLineDirection.values().length; i++) {
            RectLineDirection direction = Arrays.stream(RectLineDirection.values()).toList().get(i);
            if (getLine(direction).isPointOnSegment(collisionPoint)) {
                if (direction == RectLineDirection.TOP) {
                    return hitOnTopOfPaddle(collisionPoint, currentVelocity);
                } else if (direction == RectLineDirection.BOTTOM) {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                } else if (direction == RectLineDirection.LEFT) {
                    return hitOnLeftOfPaddle(currentVelocity);
                } else if (direction == RectLineDirection.RIGHT) {
                    return hitOnRightOfPaddle(currentVelocity);
                }
                throw new IllegalStateException("Unexpected value: " + direction);
            }
        }
        return currentVelocity;
    }

    private Velocity hitOnTopOfPaddle(Point collisionPoint, Velocity currentVelocity) {
        int angle = findCollisionAngle(collisionPoint);
        return Velocity.fromAngleAndSpeed(angle,
                Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));    }

    private Velocity hitOnRightOfPaddle(Velocity currentVelocity) {
        if (this.isMoving) {
            return new Velocity(10, 0);
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy() == 0 ? -5 : -currentVelocity.getDy());
    }

    private Velocity hitOnLeftOfPaddle(Velocity currentVelocity) {
        if (this.isMoving) {
            return new Velocity(-10, 0);
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy() == 0 ? -5 : -currentVelocity.getDy());
    }

    private int findCollisionAngle(Point collisionPoint) {
        double sizePaddle = Math.abs(this.getUpperLeft().getX() - this.getUpperRight().getX());
        double sizeRegion = sizePaddle / NUM_OF_REGIONS;
        if (collisionPoint.getX() >= this.getUpperLeft().getX()
                && collisionPoint.getX() < (sizeRegion + this.getUpperLeft().getX())) {
            return TWO_PI - 2 * DEGREES;
        }
        if (collisionPoint.getX() >= (sizeRegion + this.getUpperLeft().getX())
                && collisionPoint.getX() < (sizeRegion * 2 + this.getUpperLeft().getX())) {
            return TWO_PI - DEGREES;
        }
        if (collisionPoint.getX() >= (sizeRegion * 2 + this.getUpperLeft().getX())
                && collisionPoint.getX() < (sizeRegion * 3 + this.getUpperLeft().getX())) {
            return 0;
        }
        if (collisionPoint.getX() >= (sizeRegion * 3 + this.getUpperLeft().getX())
                && collisionPoint.getX() < (sizeRegion * 4 + this.getUpperLeft().getX())) {
            return DEGREES;
        }
        if (collisionPoint.getX() >= (sizeRegion * 4 + this.getUpperLeft().getX())
                && collisionPoint.getX() <= this.getUpperRight().getX()) {
            return DEGREES * 2;
        }
        return 0;
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.isMoving = true;
            moveLeft();
            return;
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.isMoving = true;
            moveRight();
            return;
        }
        this.isMoving = false;
    }
}