package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] fractions;
    private int numTrials;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N<=0||T<=0){
            throw new IllegalArgumentException();
        }


        for (int i=0;i<T;i++){
            int numOpened=0;
            Percolation p=pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);

                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    numOpened+=1;
                }
            }
            fractions[i] = (double) numOpened / (N * N);
        }
    }// perform T independent experiments on an N-by-N grid

    public double mean(){
        return StdStats.mean(fractions);
    }// sample mean of percolation threshold

    public double stddev(){
        return StdStats.stddev(fractions);
    }// sample standard deviation of percolation threshold

    public double confidenceLow(){
        double mu = mean();
        double sigma = stddev();
        return mu - 1.96 * sigma / Math.sqrt(numTrials);
    }// low endpoint of 95% confidence interval

    public double confidenceHigh(){
        double mu = mean();
        double sigma = stddev();
        return mu + 1.96 * sigma / Math.sqrt(numTrials);
    }// high endpoint of 95% confidence interval
}
