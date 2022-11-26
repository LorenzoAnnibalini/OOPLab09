package it.unibo.oop.workers02;

import java.lang.Thread;

public class MultiThreadedSumMatrix implements SumMatrix {

    private int nThread;

    public MultiThreadedSumMatrix(final int numThread) {
        this.nThread=numThread;
    }

    private static class Calcolo extends Thread {
        private int elemPerThread;
        private double[][] matrice;
        private double res;

        
    }

    @Override
    public double sum(double[][] matrix) {
        return 0;
    }
    
}
