package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private int size;
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {

        nullAndEqualityCheck(points);

        this.lineSegments = new LineSegment[2];
        this.size = 0;

        Arrays.sort(points);

        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k]) &&
                                points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])) {

                            // add item to array
                            addLineSegment(new LineSegment(points[i], points[m]));

                            points[i].drawTo(points[m]);
                            StdDraw.show();
                        }
                    }
                }
            }
        }
    }

    private void nullAndEqualityCheck(Point[] points) {
        if (points == null)
            cornerCase();

        for (int i = 0; i < points.length; i++) {
            Point point1 = points[i];
            for (int i1 = 0; i1 < points.length; i1++) {
                if (i != i1) {
                    Point point2 = points[i1];
                    if (point1 == null || point2 == null || point1.compareTo(point2) == 0)
                        cornerCase();
                }
            }
        }
    }

    private void cornerCase() {
        throw new IllegalArgumentException();
    }

    private void addLineSegment(LineSegment lineSegment) {
        if (size == lineSegments.length) {
            int capacity = 2 * lineSegments.length;
            LineSegment[] temp = new LineSegment[capacity];
            System.arraycopy(this.lineSegments, 0, temp, 0, this.size);
            this.lineSegments = temp;
        }

        this.lineSegments[this.size++] = lineSegment;
    }

    public int numberOfSegments() {
        return this.size;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.lineSegments, this.size);
    }

    // Unit Tests
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        System.out.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
