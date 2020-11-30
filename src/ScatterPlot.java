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
        ArrayList<String> keysUsed = new ArrayList<>();
        //Data
        for(int i = 0; i < info.size(); i++) {
            if(!keysUsed.contains(info.get(i).getDate())) {
                keysUsed.add(info.get(i).getDate());
                for(int j = 0; j < info.size(); j++) {
                    XYSeries datePoints = new XYSeries(info.get(i).getDate());
                    if(info.get(j).getDate().equals(info.get(i).getDate())) {
                        //info.
                        //int percentage = info.get(j).getTimeElapsed(;
                        //datePoints.add(j+1);
                    }
                }
            }

        }
        //XYSeries points = new XYSeries("Attendance");


        return data;
    }

}
