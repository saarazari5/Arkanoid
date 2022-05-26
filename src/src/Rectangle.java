package src;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Rectangle.
 */
public class Rectangle {
    enum RectLineDirection {
        TOP,
        LEFT,
        BOTTOM,
        RIGHT;

        public int lineIndex() {
            if (this == RectLineDirection.TOP) {
                return 0;
            } else if (this == RectLineDirection.LEFT) {
                return 1;
            } else if (this == RectLineDirection.BOTTOM) {
                return 2;
            } else if (this == RectLineDirection.RIGHT) {
                return 3;
            }
            throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    private  Point upperLeft;
    private  double height;
    private  double width;
    private  Line[] rectLines;


    /**
     * @param upperLeft
     * @param height
     * @param width
     */
    public Rectangle(Point upperLeft, double height, double width) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        setRectLines();
    }
    /**
     * @param upperLeft
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
        setRectLines();
    }

    /**
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return line of rect
     */
    public Line[] getRectLines() {
        return rectLines;
    }

    /**
     * @param rectLines
     */
    public void setRectLines(Line[] rectLines) {
        this.rectLines = rectLines;
    }

    /**
     * @param line
     * @return intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        return Arrays.stream(rectLines)
                .map(rectLine -> rectLine.intersectionWith(line))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    private void setRectLines() {
        rectLines = new Line[4];
        rectLines[0] = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        rectLines[1] = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        rectLines[2] = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, getUpperLeft().getY() + height));
        rectLines[3] = new Line(new Point(upperLeft.getX() + width, getUpperLeft().getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY()));
    }

    /**
     * @param direction direction of the rectangle.
     * @return the line in the direction.
     */
    public Line getLine(RectLineDirection direction) {
        if (direction == RectLineDirection.LEFT) {
            return rectLines[1];
        } else if (direction == RectLineDirection.BOTTOM) {
            return rectLines[2];
        } else if (direction == RectLineDirection.RIGHT) {
            return rectLines[3];
        }
        return rectLines[0];
    }

    /**
     * @param line line
     * @return if line is a rect line
     */
    public boolean isRectLine(Line line) {
        return Arrays.stream(rectLines).anyMatch(rectLine -> rectLine.equals(line));
    }

    /**
     * @param point point
     * @return .
     */
    public boolean isPointRectEdge(Point point) {
        return isUpperLeft(point) || isUpperRight(point) || isBottomLeft(point) || isBottomRight(point);
    }

    /**
     * @param edge edge
     * @return the reclines interescting with an edge
     */
    public List<Line> getRectLinesForEdge(Point edge) {
        return Arrays.stream(rectLines).filter(line -> line.isPointOnSegment(edge)).collect(Collectors.toList());
    }

    /**
     * @param point point
     * @return is point an upper left
     */
    public boolean isUpperLeft(Point point) {
        return point.equals(getUpperLeft());
    }

    /**
     * @param point point
     * @return if point an upper right
     */
    public boolean isUpperRight(Point point) {
        return point.equals(getUpperRight());
    }

    /**
     * @param point point
     * @return if point is bottom left
     */
    public boolean isBottomLeft(Point point) {
        return point.equals(getBottomLeft());
    }

    /**
     * @param point point
     * @return if point is bottom right
     */
    public boolean isBottomRight(Point point) {
        return point.equals(getBottomRight());
    }

    /**
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return upper left
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return upper right
     */
    public Point getUpperRight() {
        return rectLines[0].end();
    }

    /**
     * @return bottom left
     */
    public Point getBottomLeft() {
        return rectLines[1].end();
    }
    /**
     * @return bottom left
     */
    public Point getBottomRight() {
        return rectLines[2].end();
    }


    @Override
    public String toString() {
        return "Rectangle{" + "upperLeft=" + upperLeft + ", height=" + height + ", width=" + width
                + ", rectLines=" + Arrays.toString(rectLines) + '}';
    }
}
