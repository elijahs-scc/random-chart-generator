package com.stockcharts.interns.elijahs.randomcharts;

import java.util.*;

import edu.princeton.cs.introcs.*;


public class RandomChartsApp {



    public static void main(String[] args) {
        double[] data = ChartGenerator.generateRandomChartData(200, 1);
        ChartGenerator.lineGraph(data, 0.5, "chart.png");
    }



}
