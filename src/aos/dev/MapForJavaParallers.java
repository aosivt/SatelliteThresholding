package aos.dev;

import aos.Main;
import org.apache.spark.api.java.function.Function2;

/**
 * Created by alex on 13.06.16.
 */
public class MapForJavaParallers {

    public static final Function2< byte[], byte[],byte[]> REDUCER_ITEM_INDEX_VI =
            (Function2<byte[], byte[], byte[]>) (first_array_band, second_array_band) ->
        new VIndexParallelsFunction(first_array_band,second_array_band,Main.setReseachVI).constructReseach();

}
