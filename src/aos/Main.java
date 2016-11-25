package aos;

import aos.dev.ByteOutputFormat;
import aos.dev.MapForJava;
import aos.dev.MapForJavaParallers;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.BytesWritable;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static int  limit;
    public static String  setReseachVI;

    public static void main(String[] args) {
        Date StartPro = new Date(System.currentTimeMillis());

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");

        if (args.length < 1) {
            System.err.println("Please provide the input file full path as argument");
            System.exit(0);
        }
        else {

            File OutPutPath = new File("ert");
            if (OutPutPath.exists())
            {
                String[]entries = OutPutPath.list();
                for(String s: entries){
                    File currentFile = new File(OutPutPath.getPath(),s);
                    currentFile.delete();
                }
                OutPutPath.getAbsoluteFile().delete();
                System.err.println("ert was Delete");
            }
//            System.err.println("Start program" + args[0]);
            //new File("ert").delete();

            limit = Integer.parseInt(args[1].toString());
            setReseachVI = args[2].toString();


            SparkConf conf = new SparkConf().setAppName("SatelliteThresholding").setMaster("local");
            JavaSparkContext context = new JavaSparkContext(conf);

            JavaPairRDD<IntWritable, BytesWritable> inputRDD = context.sequenceFile("hsf", IntWritable.class, BytesWritable.class);

            JavaPairRDD<Integer, byte[]> pairs =
                    inputRDD.mapToPair(MapForJava.MAPPER_ITEM_INDEX_VI_INT_BYTESWRITABLE);

            inputRDD = null;

            JavaPairRDD<Integer, byte[]> result = pairs.
                    reduceByKey(MapForJavaParallers.REDUCER_ITEM_INDEX_VI);

//            JavaPairRDD<Integer, byte[]> result = pairs.
//                    reduceByKey(MapForJavaParallers.REDUCER_ITEM_INDEX_VI);

            pairs = null;

            JavaPairRDD<Integer, byte[]> result_by_compare_func = result.sortByKey();
            result = null;

//              JavaPairRDD<IntWritable, BytesWritable> result = pairs.reduceByKey(MapForJava.REDUCER_ITEM_INDEX_VI);
//                    pairs.reduceByKey(MapForJava.REDUCER_ITEM_INDEX_VI);
//                    inputRDD.reduceByKey(MapForJava.REDUCER_ITEM_INDEX_VI);
//              JavaPairRDD<IntWritable, BytesWritable> pairs_sort = pairs.sortByKey();
//              result.saveAsTextFile("ert");

            result_by_compare_func.saveAsNewAPIHadoopFile("ert",
                    IntWritable.class,
                    BytesWritable.class,
                    ByteOutputFormat.class);
        }

        System.out.println("Начало созданя файла последовательности: " + timeFormat.format(StartPro));
        System.out.println("Конец созданя файла последовательности: " + timeFormat.format(new Date(System.currentTimeMillis())));

    }

    }

