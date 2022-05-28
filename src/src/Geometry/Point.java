package src.Geometry;

import java.util.Objects;

/**
 * Point class.
 */
public class Point {
    public static final Point DEFAULT = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

    private double x;
    private double y;

    /**
     * @param x coordinate
     * @param y coordinate
     */

    public Point(double x, double y) {
       this.x = (int) x;
       this.y = (int) y;
    }

    /***
     * @param other distance
      * @return aff
     */
    public double distance(Point other) {
        if (other == null || this.equals(other)) {
            return 0;
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2)  + Math.pow(this.y - other.y, 2));
    }

    static boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x)
                && q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    static int orientation(Point p, Point q, Point r) {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) {
            return 0;
        }
        return (val > 0) ? 1 : 2;
    }

    /**
     * @param other asf
     * @return asdasf
     */
    public boolean equals(Point other) {
        return Double.compare(this.x, other.x) == 0 && Double.compare(this.y, other.y) == 0;
    }

    /**
     * @return asdasd
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return sfaf
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param x
     */
    public void setX(double x) {
        this.x = (int) x;
    }

    /**
     * @param y
     */
    public void setY(double y) {
        this.y = (int) y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return equals(point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
