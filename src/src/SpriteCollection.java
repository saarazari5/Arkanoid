package src;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * SpriteCollection.
 */
public class SpriteCollection {
   private final ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * @return sprites
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    /**
     * @param s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     *  call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> sprites = new ArrayList<>(this.sprites);
        sprites.forEach(Sprite::timePassed);
    }

    /**
     * @param d  call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        sprites.forEach(sprite -> sprite.drawOn(d));
    }
}
