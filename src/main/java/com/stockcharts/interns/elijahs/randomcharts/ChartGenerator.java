package com.stockcharts.interns.elijahs.randomcharts;

import java.util.*;

import edu.princeton.cs.introcs.*;


public final class ChartGenerator {
    private static final Random random = new Random();

    private ChartGenerator() {
        // prevent instantiation
    }

    // generates data points by applying a random percent change to the previous data point
    public static double[] generateRandomChartData(int numPoints, double maxPercentChange) {
        if (maxPercentChange < 0)
            maxPercentChange *= -1;

        double[] points = new double[numPoints];
        points[0] = random.nextDouble() * 90 + 10;
        double[] percentChanges = random.doubles(numPoints, -maxPercentChange, maxPercentChange).toArray();
        for (int i = 1; i < numPoints ; i++) {
            points[i] = points[i-1] + points[i-1] * percentChanges[i] / 100;
        }
        return points;
    }

    public static void lineGraph(double[] ys) {
        lineGraph(ys, 1.0, "");
    }

    public static void lineGraph(double[] ys, double yRatio) {
        lineGraph(ys, yRatio, "");
    }

    public static void lineGraph(double[] ys, double yRatio, String filePath) {
        if (ys == null || ys.length < 2)
            throw new IllegalArgumentException("There must be at least two points to plot.");

        StdDraw.setCanvasSize(512, (int)(512 * yRatio));

        double min = min(ys);
        double max = max(ys);

        // normalize ys to fit between 0 and 1
        for (int i = 0; i < ys.length; i++) {
            ys[i] = (ys[i] - min) / (max - min) ;
        }
        
        double dx = 1.0 / (ys.length - 1);
        StdDraw.line(0, ys[0], dx, ys[1]);
        for (int i = 1; i < ys.length - 1; i++) {
            StdDraw.line(dx * i, ys[i], dx * (i+1), ys[i+1]);
        }

        if (filePath != null && !filePath.isBlank())
            StdDraw.save(filePath);
    }

    public static double min(double[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Nums cannot be empty");

        double min = nums[0];
        for (double num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }

    public static double max(double[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Nums cannot be empty");

        double max = nums[0];
        for (double num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }


}
