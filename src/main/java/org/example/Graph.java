package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Graph extends JFrame{

    private final int DEFAULT_PADDING = 15;
    public Graph(Map<String, Double> map){
        init(map);
    }

    private void init(Map<String, Double> map){
        CategoryDataset dataset = createDataset(map);

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING));
        chartPanel.setBackground(Color.white);
        chart.getCategoryPlot().getRangeAxis().setLowerBound(25);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(Map<String, Double> schools) {
        var dataset = new DefaultCategoryDataset();
        schools.forEach((key, value) -> dataset.setValue(value, "avg(age)", key));
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Средний возраст команд",
                "",
                "Средний возраст",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }
}
