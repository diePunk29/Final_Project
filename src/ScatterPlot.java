/**
 * CSE360
 * Final Project
 * This class is the scatter plot that shows
 * the attendance info for the class.
 */

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlot extends JFrame {

    final String TITLE = "Scatter Plot for Attendance";
    final String XAXISLABEL = "% of Attendance";
    final String YAXISLABEl = "Number of Students";
    
    /**
     * This is the constructor for the scatter plot that initializes what it should look like.
     * @param title The title of the scatter plot.
     */
    public ScatterPlot(String title, ArrayList<AttendanceInfo> atInfo)
    {
        super(title);

        //Create Data Table
        XYDataset data = createDataset(atInfo);

        //Create Chart
        JFreeChart scatterPlot = ChartFactory.createScatterPlot(TITLE,XAXISLABEL,YAXISLABEl,data);

        //Set Background Color
        XYPlot plot = (XYPlot)scatterPlot.getPlot() ;
        plot.setBackgroundPaint(new Color(255,228,196));

        //Create Panel
        ChartPanel panel = new ChartPanel(scatterPlot);
        setContentPane(panel);
    }


    /*
    So in here, we need it to pull data from the table. I think the best way is through
    a loop of sorts, using studentEntries and AttendanceInfo?
     */
    /**
     * This pulls the data from the table in order to get it into the plot.
     * @return The dataset to be added to the plot.
     */
    private XYDataset createDataset(ArrayList<AttendanceInfo> info)
    {
        XYSeriesCollection data = new XYSeriesCollection();
        //Data
        XYSeries points = new XYSeries("Attendance");


        /* Test Data
        xAxisData.add(1, 72.9);
        xAxisData.add(2, 81.6);
        xAxisData.add(3, 88.9);
        xAxisData.add(4, 96);
        xAxisData.add(5, 102.1);
        xAxisData.add(6, 108.5);
        xAxisData.add(7, 113.9);
        xAxisData.add(8, 119.3);
        xAxisData.add(9, 123.8);
        xAxisData.add(10, 124.4);
         */

        //Add Real Data
        //*************





        //*************

        data.addSeries(points);

        return data;
    }

}
