package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int trails;
    private final double[] result;
    private final static double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("N or trails <= 0");
        this.trails = trials;
        result = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            result[i] = p.numberOfOpenSites() / (n * n * 1.0);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trails));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trails));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trails);
        System.out.printf("mean=%f\n", ps.mean());
        System.out.printf("stddev=%f\n", ps.stddev());
        System.out.printf("95 confidence interval=[%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
    }
}