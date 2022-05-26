package src;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * constructor method.
     * it creates the velocity object.
     * @param dx the value of the change in x
     * @param dy the value of the change in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed method.
     * calculating the speed by getting the angle and speed of the vector of the ball.
     * @param angle angle of vector
     * @param speed value of the speed
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        while (angle >= 360) {
            angle = angle - 360;
        }
        while (angle < 0) {
            angle = angle + 360;
        }

        double dy = speed * (-1) * Math.cos(Math.toRadians(angle));
        double dx = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @param point
     * @return asf
     */
    public Line convertToLine(Point point) {
        Point endPoint = new Point(point.getX() + dx, point.getY() +  dy);
        return new Line(point, endPoint);
    }
    /**
     * @return w
     */
    public double getDx() {
        return dx;
    }

    /**
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * @return sf
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * @param p
     * @return afa
     */
    public Point applyToPoint(Point p) {
        p.setX(p.getX() + dx);
        p.setY(p.getY() + dy);

        return p;
    }
}

