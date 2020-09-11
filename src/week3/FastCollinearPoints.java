package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        nullAndEqualityCheck(points);

        Point[] pointsCopySO = Arrays.copyOf(points, points.length);
        Point[] pointsCopyNO = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        Arrays.sort(pointsCopyNO);
        for (Point origin : pointsCopyNO) {
            Arrays.sort(pointsCopySO);
            Arrays.sort(pointsCopySO, origin.slopeOrder());
            int count = 1;
            Point lineBeginning = null;
            for (int j = 0; j < pointsCopySO.length - 1; ++j) {
                if (pointsCopySO[j].slopeTo(origin) == pointsCopySO[j + 1].slopeTo(origin)) {
                    count++;
                    if (count == 2) {
                        lineBeginning = pointsCopySO[j];
                        count++;
                    } else if (count >= 4 && j + 1 == pointsCopySO.length - 1) {
                        if (lineBeginning.compareTo(origin) > 0) {
                            segmentsList.add(new LineSegment(origin, pointsCopySO[j + 1]));
                        }
                        count = 1;
                    }
                } else if (count >= 4) {
                    if (lineBeginning.compareTo(origin) > 0) {
                        segmentsList.add(new LineSegment(origin, pointsCopySO[j]));
                    }
                    count = 1;
                } else {
                    count = 1;
                }
            }
        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }

    public int numberOfSegments() {
        return this.segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, numberOfSegments());
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
