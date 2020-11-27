//import java.awt.Color;
//import javax.swing.JFrame;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//public class ScatterPlot extends JFrame {
//
//    final String TITLE = "Scatter Plot of Attendance";
//    final String XAXISLABEL = "% of Attendance";
//    final String YAXISLABEl = "Number of Students";
//
//    public ScatterPlot(String title)
//    {
//
//        //Create Data Table
//        XYDataset data = createDataset();
//
//        //Create Chart
//        JFreeChart scatterPlot = ChartFactory.createScatterPlot(TITLE,XAXISLABEL,YAXISLABEl,data);
//
//        //Set Background Color
//        XYPlot plot = (XYPlot)scatterPlot.getPlot() ;
//        plot.setBackgroundPaint(new Color(255,228,196));
//
//        //Create Panel
//        ChartPanel panel = new ChartPanel(scatterPlot);
//        setContentPane(panel);
//    }
//
//    private XYDataset createDataset()
//    {
//        XYSeriesCollection data = new XYSeriesCollection();
//
//        //X-axis data
//        XYSeries xAxisData = new XYSeries("What should this be?");
//
//
//        /* Test Data
//        xAxisData.add(1, 72.9);
//        xAxisData.add(2, 81.6);
//        xAxisData.add(3, 88.9);
//        xAxisData.add(4, 96);
//        xAxisData.add(5, 102.1);
//        xAxisData.add(6, 108.5);
//        xAxisData.add(7, 113.9);
//        xAxisData.add(8, 119.3);
//        xAxisData.add(9, 123.8);
//        xAxisData.add(10, 124.4);
//         */
//
//        //Add Real Data
//        //*************
//
//
//
//
//
//        //*************
//
//        data.addSeries(xAxisData);
//
//        //Y-axis data
//        XYSeries yAxisData = new XYSeries("What should this be?");
//
//        //Test data
//        /*
//        yAxisData.add(1, 72.5);
//        yAxisData.add(2, 80.1);
//        yAxisData.add(3, 87.2);
//        yAxisData.add(4, 94.5);
//        yAxisData.add(5, 101.4);
//        yAxisData.add(6, 107.4);
//        yAxisData.add(7, 112.8);
//        yAxisData.add(8, 118.2);
//        yAxisData.add(9, 122.9);
//        yAxisData.add(10, 123.4);
//        */
//
//        //Add Real Data
//        //***************
//
//
//
//
//        //***************
//
//        data.addSeries(yAxisData);
//
//        return data;
//    }
//}
