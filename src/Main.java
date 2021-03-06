import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import java.util.Random;

public class Main extends BinarySearch{

    static int[] arr;
    static Random rand = new Random();
    static long startTimeNano, endTimeNano, totalTimeNano;
    static int totalOperations;
    static DefaultCategoryDataset execTimeDataSet = new DefaultCategoryDataset( );
    static DefaultCategoryDataset operationsDataSet = new DefaultCategoryDataset( );


    public static void main(String[] args){
        ArraySearchTest();
        PlotGraph("Exectuion Time", "Execution Time vs Dataset Size", "Dataset Size", "Execution Time (ns)", execTimeDataSet);
        PlotGraph("Basic Operations", "Basic Operations vs Dataset Size", "Dataset Size", "Number of Basic Operations", operationsDataSet);
    }

    public static void WarmUp(){
        for (int size = 1000; size <= 10000; size += 1000){
            long[] timeArrNano = new long[20];
            int[] operationArr = new int[20];
            totalTimeNano = 0;
            totalOperations = 0;
            for (int i = 0; i < 20; i++) {
                arr = CreateArray(size);
                int key = rand.nextInt(size);
                startTimeNano = System.nanoTime();
                operationArr[i] = BinarySearchBasicOperations(arr, key);
                endTimeNano = System.nanoTime();
            }
        }
    }

    /**
     *  The bulk of the code is implemented here to run; the array creation, search function, dataset construction,
     *  execution timing, and operation count.
     */
    public static void ArraySearchTest(){
        for (int size = 1000; size <= 20000; size += 1000){
            long[] timeArrNano = new long[20];
            int[] operationArr = new int[20];
            totalTimeNano = 0;
            totalOperations = 0;
            for (int i = 0; i < 20; i++) {
                arr = CreateArray(size);
                int key = rand.nextInt(size);
                startTimeNano = System.nanoTime();
                operationArr[i] = BinarySearchBasicOperations(arr, key);
                endTimeNano = System.nanoTime();
                timeArrNano[i] = endTimeNano - startTimeNano;
                System.out.println("ArrSize: " + size + "\t\tTestNum: " + (i+1) + "\t\tBasicOper: " + operationArr[i] + "\t\tTimeNano: " + timeArrNano[i]);
            }
            SortArray(timeArrNano);
            SortArray(operationArr);
            for (int i = 5; i < timeArrNano.length - 5; i++){
                totalTimeNano += timeArrNano[i];
                totalOperations += operationArr[i];
            }
            totalOperations /= 10;
            totalTimeNano /= 10;
            operationsDataSet.addValue(totalOperations, "Basic Operations (avg)", Integer.toString(size));
            execTimeDataSet.addValue(totalTimeNano, "Execution time (avg)", Integer.toString(size));
            System.out.println("***\tArrSize: " + size + "\t\tAvgBasicOper: " + totalOperations + "\t\tAvgTimeNano: " + totalTimeNano + "\t***");
        }
    }

    /**
     * Creates a new plot class and sends it the required variables to plot the graph.
     *
     * @param appTitle
     * @param title
     * @param xAxisLabel
     * @param yAxisLabel
     * @param dataSet
     */
    public static void PlotGraph(String appTitle, String title, String xAxisLabel, String yAxisLabel, DefaultCategoryDataset dataSet){
        Plot chart = new Plot(appTitle, title, xAxisLabel, yAxisLabel, dataSet);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
