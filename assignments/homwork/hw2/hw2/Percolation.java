package hw2;
import java.lang.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;             // board length
    private boolean[][] sites; // true if site[i][j] is open, false if blocked.
    private int openSites;     // number of open sites
    private int TOP;           // a virtual top site, which is connected to the first row of sites.
    private int BOTTOM;        // a virtual bottom site, which is connected to the last row of sites.
    private WeightedQuickUnionUF uf; // union find
    private int[][] DIRECTIONS = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}}; // neighbors

    /* Creates a N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be a positive integer.");
        }
        sites = new boolean[N][N]; // default values are false
        uf = new WeightedQuickUnionUF(N * N + 2);
        TOP = N * N;
        BOTTOM = N * N + 1;
        openSites = 0;
        this.N = N;

    }

    /* Translates a 2d index (row, col) to 1d index x. */
    public int indexOf(int row, int col) {
        return row * N + col;
    }

    /* Checks if the index is out-of-bound. */
    public void checkIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col > N) {
            throw new IndexOutOfBoundsException("Row / Column index is out of bound.");
        }
    }

    /* Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        checkIndex(row, col);
        if (!sites[row][col]) {
            sites[row][col] = true;
            openSites++;
        }

        // If (row, col) has a open neighbor, connect them.
        for (int[] d : DIRECTIONS) {
            int row2 = row + d[0];
            int col2 = col + d[1];
            if (0 <= row2 && row2 < N && 0 <= col2 && col2 < N) {
                if (isOpen(row2, col2)) {
                    uf.union(indexOf(row, col), indexOf(row2, col2));
                }
            }
        }

        // If this site is on the top/ bottom row, connect it to the water source/ bottom site.
        if (row == 0) {
            uf.union(TOP, indexOf(row, col));
        } else if (row == N - 1) {
            uf.union(BOTTOM, indexOf(row, col));
        }
    }

    /* Returns true if the site (row, col) is open. */
    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        return sites[row][col];
    }

    /* Returns true if the site (row, col) is full. */
    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        // the site is full if it is connected with the water source
        return uf.connected(indexOf(row, col), TOP);
    }

    /* Returns the number of open sites. */
    public int numberOfOpenSites() {
        return openSites;
    }

    /* Returns true if the system percolates. */
    public boolean percolates() {
        // the system percolates if the bottom site is connected with the water source.
        return uf.connected(BOTTOM, TOP);
    }


    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        p.open(1, 1);
        p.open(2, 2);
        p.open(2, 1);
        System.out.println(p.percolates());
        p.open(1, 2);
        System.out.println(p.percolates());
        p.open(0, 1);
        System.out.println(p.percolates());
    }
}
