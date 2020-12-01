/**
 * Cristian Mosqueda, Eric Fahy, Albert Schaffer, Will Lord, and Tyler Vaillancourt
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
    private ArrayList<String> keysUsed;

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
        keysUsed = new ArrayList<>();
        XYSeries datePoints;

        // reloading old scatter plot points before adding new series
        if(oldData != null && !oldData.isEmpty()) {
            for(int indx = 0; indx < oldData.size(); indx++) {
                data.addSeries(oldData.get(indx));
            }
        }
        datePoints = new XYSeries(info.get(0).getDate());
        for(int i = 0; i < info.size(); i++) {
            double time = Integer.parseInt(info.get(i).getTimeElapsed().replaceAll(" ", ""));
            if (time >= 75.0) percentage = 100.0;
            else percentage = (time / 75.0) * 100.0;
            datePoints.add(percentage, i + 1);
        }

        if (!oldData.contains(datePoints)) {
            data.addSeries(datePoints);
            oldData.add(datePoints);
        }
        return data;
    }
}