package src.Geometry;

import java.util.List;


/**
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * @param start point
     * @param end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param rect the rectangle to check intersection with.
     * @return rect
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        final Point[] closestPoint = {Point.DEFAULT};
        List<Point> intersectionsPoints = rect.intersectionPoints(this);
        intersectionsPoints.forEach(point -> {
            double currentPointDistance = point.distance(start);
            if (currentPointDistance <= closestPoint[0].distance(start)) {
                closestPoint[0] = point;
            }
        });
        return closestPoint[0];
    }

    /**
     * @param x1 point
     * @param y1 point
     * @param x2 point
     * @param y2 point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return Return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return Returns the middle point of the line
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * @return Returns the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return  Returns the end point of the line
     */
    public Point end() {
        return this.end;
    }


    /**
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        int o1 = Point.orientation(this.start, this.end, other.start);
        int o2 = Point.orientation(this.start, this.end, other.end);
        int o3 = Point.orientation(other.start, other.end, this.start);
        int o4 = Point.orientation(other.start, other.end, this.end);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        if (o1 == 0 && Point.onSegment(this.start, other.start, this.end)) {
            return true;
        }

        if (o2 == 0 && Point.onSegment(this.start, other.end, this.end)) {
            return true;
        }

        if (o3 == 0 && Point.onSegment(other.start, this.start, other.end)) {
            return true;
        }
        return o4 == 0 && Point.onSegment(other.start, this.end, other.end);
    }

    /**
     * @param other line
     * @return intersection with this line.
     */
    public Point intersectionWith(Line other) {
        if (other.end.equals(this.end)) {
            return end;
        }
        if (other.start.equals(this.start)) {
            return start;
        }
        if (!isIntersecting(other)) {
            return null;
        }
        double a1 = end.getY() - start.getY();
        double b1 = start.getX() - end.getX();
        double c1 = a1 * (start.getX()) + b1 * (start.getY());

        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
         return  null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            return new Point(x, y);
        }
    }

    boolean isPointOnSegment(Point point) {
        if (point.equals(start) || point.equals(end)) {
            return true;
        }
        return start.distance(point) + end.distance(point) == length();
    }

    /**
     * @param other line
     * @return equal flag.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.end.equals(other.start) && this.start.equals(other.end));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return this.equals(line);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.start.hashCode() + this.end.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Line{" + "start=" + start + ", end=" + end + '}';
    }
}
