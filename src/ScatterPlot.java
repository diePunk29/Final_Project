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
    public ScatterPlot(String title, ArrayList<AttendanceInfo> atInfo, ArrayList<XYSeries> oldData)
    {
        super(title);
        //Create Data Table
        XYDataset data = createDataset(atInfo, oldData);

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
     * @param info is the student attendance info being used to populate the scatter chart
     * @param oldData
     * @return The dataset to be added to the plot.
     */
    private XYDataset createDataset(ArrayList<AttendanceInfo> info, ArrayList<XYSeries> oldData)
    {
        double percentage;
        XYSeriesCollection data = new XYSeriesCollection();
        ArrayList<String> keysUsed = new ArrayList<>();
        XYSeries datePoints;

        // reloading old scatter plot points before adding new series
        if(oldData != null && !oldData.isEmpty()) {
            for(int indx = 0; indx < oldData.size(); indx++) {
                data.addSeries(oldData.get(indx));
            }
        }
        for(int i = 0; i < info.size(); i++) {
            if(!keysUsed.contains(info.get(i).getDate())) {
                keysUsed.add(info.get(i).getDate());
                datePoints = new XYSeries(info.get(i).getDate());
                for(int j = 0; j < info.size(); j++) {
                    if (info.get(j).getDate().equals(info.get(i).getDate())) {
                        double time = Integer.parseInt(info.get(j).getTimeElapsed().replaceAll(" ", "").toString());
                        if (time >= 75.0) percentage = 100.0;
                        else percentage = (time / 75.0) * 100.0;
                        datePoints.add(percentage, j + 1);
                    }
                }
                data.addSeries(datePoints);
                oldData.add(datePoints);
            }
        }
        return data;
    }



}
