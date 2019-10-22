package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTopOnly;
    private int top;
    private int bottom;
    private int size;
    private int numOpenSites=0;

    public Percolation(int N){
        if (N<=0){
            throw new java.lang.IndexOutOfBoundsException();
        }
        grid=new boolean[N][N];
        uf = new WeightedQuickUnionUF(N*N+2);
        ufTopOnly = new WeightedQuickUnionUF(N * N + 1);
        size=N;
        top=0;
        bottom=N*N+1;
    }// create N-by-N grid, with all sites initially blocked

    private void validate(int r, int c) {
        if (r < 0 || c < 0 || r >= size || c >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private int xyTo1D(int r, int c) {
        return r * size + c + 1;
    }

    public void open(int row, int col){
        validate(row,col);
        if (!isOpen(row,col)){
            grid[row][col]=true;
            numOpenSites+=1;
        }
        if (row==0){
            uf.union(xyTo1D(row,col),top);
            ufTopOnly.union(xyTo1D(row, col), top);
        }
        if (row==size-1){
            uf.union(xyTo1D(row,col),bottom);
        }
        if (row>0&&isOpen(row-1,col)){
            uf.union(xyTo1D(row-1,col),xyTo1D(row,col));
            ufTopOnly.union(xyTo1D(row-1,col),xyTo1D(row,col));
        }
        if (row<size-1&&isOpen(row+1,col)){
            uf.union(xyTo1D(row+1,col),xyTo1D(row,col));
            ufTopOnly.union(xyTo1D(row+1,col),xyTo1D(row,col));
        }
        if (col>0&&isOpen(row,col-1)){
            uf.union(xyTo1D(row,col-1),xyTo1D(row, col));
            ufTopOnly.union(xyTo1D(row,col-1),xyTo1D(row, col));
        }
        if (col<size-1&&isOpen(row,col+1)){
            uf.union(xyTo1D(row,col+1),xyTo1D(row, col));
            ufTopOnly.union(xyTo1D(row,col+1),xyTo1D(row, col));
        }

    }// open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col){
        validate(row,col);
        return grid[row][col];
    }// is the site (row, col) open?

    public boolean isFull(int row, int col){
        validate(row,col);
        return ufTopOnly.connected(top,xyTo1D(row,col));
    }// is the site (row, col) full?

    public int numberOfOpenSites(){
        return numOpenSites;
    }// number of open sites

    public boolean percolates(){
        return uf.connected(top,bottom);
    }// does the system percolate?

    public static void main(String[] args){

    }// use for unit testing (not required, but keep this here for the autograder)
}
