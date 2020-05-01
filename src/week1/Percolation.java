package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF grid;
    private final int top, bottom, n;
    private int noOfOpenSites = 0;
    private final boolean[] openGrid;

    // Helper Method
    private void checkBounds(int ridx, int cidx) {
        if (ridx < 0 || ridx >= n || cidx < 0 || cidx >= n)
            throw new IllegalArgumentException("Index out of bounds!");
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Grid Size <= 0");
        this.n = n;
        int nSquare = n * n;
        this.grid = new WeightedQuickUnionUF(nSquare + 2);
        this.openGrid = new boolean[nSquare];
        this.top = nSquare;
        this.bottom = nSquare + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int ridx = row - 1;
        int cidx = col - 1;
        checkBounds(ridx, cidx);

        if (!isOpen(row, col)) {
            noOfOpenSites++;
            openGrid[n * ridx + cidx] = true;
            if (ridx == 0) grid.union(top, (ridx * n + cidx));
            else if (isOpen(row - 1, col)) grid.union((ridx * n + cidx), ((ridx - 1) * n + cidx)); // N
            if (!(cidx + 1 == n) && isOpen(row, col + 1)) grid.union((ridx * n + cidx), (ridx * n + (cidx + 1))); // E
            if (cidx != 0 && isOpen(row, col - 1)) grid.union((ridx * n + cidx), (ridx * n + (cidx - 1))); // W
            if (ridx + 1 == n) grid.union(bottom, (ridx * n + cidx));
            else if (isOpen(row + 1, col)) grid.union((ridx * n + cidx), ((ridx + 1) * n + cidx)); // S
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int ridx = row - 1;
        int cidx = col - 1;
        checkBounds(ridx, cidx);
        return openGrid[n * ridx + cidx];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int ridx = row - 1;
        int cidx = col - 1;
        checkBounds(ridx, cidx);
        return grid.find(top) == grid.find(ridx * n + cidx);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return noOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(top) == grid.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}