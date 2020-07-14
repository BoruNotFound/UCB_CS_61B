package hw2;
import java.lang.*;
import edu.princeton.cs.introcs.StdRandom; // to generate random numbers
import edu.princeton.cs.introcs.StdStats;  // to compute stats

public class PercolationStats {
    private Percolation[] systems;
    private int[] thresholds;
    private int N;
    private int T;

    /* Performs T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T must be positive integers.");
        }
        this.N = N;
        this.T = T;
        systems = new Percolation[T];
        thresholds = new int[T];

        // do Monte-Carlo experiments on all percolation systems and store the thresholds
        for (int t = 0; t < T; t++) {
            systems[t] = pf.make(N);
            thresholds[t] = doExperiment(systems[t]);
        }

    }

    /* Does a single Monte-Carlo experiment on a percolation system.
     * Returns the threshold.
     */
    public int doExperiment(Percolation p) {
        while (!p.percolates()) {
            // random among all sites is equivalent to random among blocked sites
            int gridIndex = StdRandom.uniform(N * N);
            int row = gridIndex / N;
            int col = gridIndex % N;
            p.open(row, col);
        }
        return p.numberOfOpenSites();
    }

    /* Sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(this.thresholds);
    }

    /* Sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(this.thresholds);
    }

    /* Low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        double m = this.mean();
        double s = this.stddev();
        return m - 1.96 * s / Math.sqrt(T);
    }

    /* High endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        double m = this.mean();
        double s = this.stddev();
        return m + 1.96 * s / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int N = 50;
        int T = 300;
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(N, T, pf);
        double low = ps.confidenceLow();
        double high = ps.confidenceHigh();

        System.out.printf("Completed %d experiments on %d-by-%d percolation system.\n", T, N, N);
        System.out.printf("Mean   : %6.1f \n", ps.mean());
        System.out.printf("StdDev : %6.1f \n", ps.stddev());
        System.out.printf("95%% confidence interval: %6.1f - %6.1f", low, high);
    }
}
