package aos.dev;

/**
 * Created by oshchepkovayu on 15.04.16.
 */
import aos.Main;

import org.apache.hadoop.io.*;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;
import java.nio.ByteBuffer;

import java.util.Arrays;


public class MapForJava {

    public static final PairFunction<Tuple2<IntWritable, BytesWritable>, Integer, byte[]> MAPPER_ITEM_INDEX_VI_INT_BYTESWRITABLE
            = new PairFunction<Tuple2<IntWritable, BytesWritable>, Integer, byte[]>() {
        @Override
        public Tuple2<Integer, byte[]> call(Tuple2<IntWritable, BytesWritable> byteWritableBytesWritableTuple2) throws Exception {
            int keyRow = byteWritableBytesWritableTuple2._1.get();
            byte[] arrowRow = byteWritableBytesWritableTuple2._2.copyBytes();

//            for (int indArray = 0; indArray<arrowRow.length;indArray++)
//            {
//                Integer valueArray = null;
//                valueArray = ByteBuffer.wrap(new byte[]{0,0,0,arrowRow[indArray]}).getInt();
//                if (valueArray < Main.limit){arrowRow[indArray] = 0;}
//
//            }

            Tuple2<Integer,byte[]> outputTuple =
                    new Tuple2<>(keyRow, arrowRow);
            return outputTuple;
        }
    };

    public static final Function2< byte[], byte[],byte[]> REDUCER_ITEM_INDEX_VI =
            new Function2<byte[], byte[],byte[] >() {
                @Override
                public byte[] call(byte[] first_array_band, byte[] second_array_band) throws Exception
                {



//                    if (first_array_band[3]==(byte)3) {
//
//                        return new VIndexFunctions(
//                                Arrays.copyOfRange(first_array_band, 4, first_array_band.length),
//                                Arrays.copyOfRange(second_array_band, 4, second_array_band.length),
//                                Main.setReseachVI).constructReseach();
//
//                        }
//                    else
//                        return new VIndexFunctions(
//                                    Arrays.copyOfRange(second_array_band, 4, second_array_band.length),
//                                    Arrays.copyOfRange(first_array_band, 4, first_array_band.length),
//                                    Main.setReseachVI).constructReseach();
//                        }
                    if (!Arrays.copyOfRange(first_array_band, 4, first_array_band.length)
                            .equals(Arrays.copyOfRange(second_array_band, 4, second_array_band.length)))
                    {
                        System.out.println("не совпал");
                    }

                    return new VIndexFunctions(
                            Arrays.copyOfRange(first_array_band, 4, first_array_band.length),
                            Arrays.copyOfRange(second_array_band, 4, second_array_band.length),
                            Main.setReseachVI).constructReseach();
            }
};
}
