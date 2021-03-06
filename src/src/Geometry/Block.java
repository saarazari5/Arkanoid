package src.Geometry;

import biuoop.DrawSurface;
import src.Game.Animations.Collidable;
import src.Game.GameLevel;
import src.Game.Animations.Sprite;
import src.Observers.HitListener;
import src.Observers.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static src.Game.GameUtils.DEF_HEIGHT;
import static src.Game.GameUtils.DEF_WIDTH;

/**
 * Block.
 */
public class Block extends Rectangle implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners = new ArrayList<>();


    private final Color color;
    /**
     * @param upperLeft of the block
     * @param height of the block
     * @param width of the block
     * @param color of the block
     */
    public Block(Point upperLeft, double height, double width, Color color) {
        super(upperLeft, height, width);
        this.color = color;
    }

    /**
     * @param upperLeft of the block
     * @param height of the block
     * @param width of the block
     */
    public Block(Point upperLeft, double height, double width) {
        this(upperLeft, height, width, Color.BLACK);
    }

    /**
     * @return the hit observers.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * @param hitListeners setter.
     */
    public void setHitListeners(List<HitListener> hitListeners) {
        this.hitListeners = hitListeners;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        ArrayList<RectLineDirection> collisionDirections = new ArrayList<>();
        this.notifyHit(hitter);
        for (int i = 0; i < RectLineDirection.values().length; i++) {
            RectLineDirection direction = Arrays.stream(RectLineDirection.values()).collect(Collectors.toList()).get(i);
            if (getLine(direction).isPointOnSegment(collisionPoint)) {
                collisionDirections.add(direction);
            }
        }
        if (collisionDirections.isEmpty()) {
            return currentVelocity;
        }
        if (collisionDirections.size() == 1) {
            return velocityFromDirection(collisionDirections.get(0), currentVelocity);
        }
        return velocityFromDirection(hitOnEdges(collisionPoint, currentVelocity), currentVelocity);
    }

    private RectLineDirection hitOnEdges(Point collisionPoint, Velocity currentVelocity) {
        if (isUpperLeft(collisionPoint)) {
            if (currentVelocity.getDx() >= 0) {
                return RectLineDirection.LEFT;
            }
            return RectLineDirection.TOP;
        }
        if (isUpperRight(collisionPoint)) {
            if (currentVelocity.getDx() <= 0) {
                return RectLineDirection.RIGHT;
            }
            return RectLineDirection.TOP;
        }
        if (isBottomLeft(collisionPoint)) {
            if (currentVelocity.getDx() >= 0) {
                return RectLineDirection.LEFT;
            }
            return RectLineDirection.BOTTOM;
        }
        if (isBottomRight(collisionPoint)) {
            if (currentVelocity.getDx() <= 0) {
                return RectLineDirection.RIGHT;
            }
            return RectLineDirection.BOTTOM;
        }
        return RectLineDirection.TOP;
    }

    private Velocity velocityFromDirection(RectLineDirection direction, Velocity currentVelocity) {
        if (direction == RectLineDirection.TOP || direction == RectLineDirection.BOTTOM) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (direction == RectLineDirection.LEFT || direction == RectLineDirection.RIGHT) {
            if (isBound(DEF_HEIGHT, DEF_WIDTH) && currentVelocity.getDy() == 0) {
                return new Velocity(-currentVelocity.getDx(),
                        direction == RectLineDirection.LEFT ? -3 : 3);
            }
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        throw new IllegalStateException("Unexpected value: " + direction);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * @param height of the bound
     * @param width of the bound
     * @return if a block is one of the bounds of the game.
     */
    public boolean isBound(int height, int width) {
        return getLine(RectLineDirection.TOP).length() >= width
                || getLine(RectLineDirection.RIGHT).length() >= height;
    }

    /**
     * @param gameLevel to add a block.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * @param gameLevel to remove a block.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * @return color of block
     */
    public Color getColor() {
        return color;
    }

    protected void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
