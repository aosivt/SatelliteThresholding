package aos.dev;

import aos.Main;
import org.apache.directory.api.util.ByteBuffer;
import org.apache.hadoop.hbase.util.ByteBufferArray;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.file.tfile.ByteArray;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.nio.DoubleBuffer;
import java.util.Arrays;

/**
 * Created by alex on 13.06.16.
 */
public class MapForJavaParallers {

    public static final Function2< byte[], byte[],byte[]> REDUCER_ITEM_INDEX_VI =
            (Function2<byte[], byte[], byte[]>) (first_array_band, second_array_band) ->
        new VIndexParallelsFunction(first_array_band,second_array_band,Main.setReseachVI).constructReseach();

}
