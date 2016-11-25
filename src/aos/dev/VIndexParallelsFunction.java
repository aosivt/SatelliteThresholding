package aos.dev;

import aos.ConverterBytes.NumberUtil;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by alex on 13.06.16.
 */
public class VIndexParallelsFunction {

    public String switch_case_function;
    public byte[] nred;
    public byte[] red;

    public byte[] result;

    public ByteBuffer result_byte_array;
    public float[] result_float;

    public VIndexParallelsFunction(byte[] first, byte[] second,String switch_case_function)
    {
        if (first[3]==(byte)3)
        {
            this.nred = Arrays.copyOfRange(first, 4, first.length);
            this.red = Arrays.copyOfRange(second, 4, second.length) ;
        }
        else if (first[3]==(byte)4)
        {
            this.nred = Arrays.copyOfRange(second, 4, second.length);
            this.red = Arrays.copyOfRange(first, 4, first.length);
        }

        this.switch_case_function = switch_case_function;
        this.result = new byte[nred.length*4];

    }


    public byte[] constructReseach()
    {
        switch (this.switch_case_function) {
            case "NDVI": return GetReseachNDVI();
            case "RVI" :  return GetReseachRVI();
            case "IPVI": return GetReseachIPVI();
            case "DVI" :  return GetReseachDVI();
            case "TVI" :  return GetReseachTVI();
            default: return GetReseachNDVI();
        }
    }

    public byte[] GetReseachNDVI()
    {
        try {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");

            IntStream.range(0, this.red.length)
            .parallel().
            forEach(
            (i)->
            {
//                System.err.println("Current: " + Thread.currentThread());
//                System.err.println("Active: " + Thread.activeCount());
                float test =
                ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true) -
                (float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.red[i]}, true))
                /
                ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true) +
                (float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.red[i]}, true));


                this.FillingResultArray(test,i);
            }
    );
}
        catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
        return this.result;
}

    public byte[] GetReseachRVI()
    {
        try {
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
            IntStream.range(0, this.red.length)
                    .parallel().
                    forEach(
                            (i)->
                            {
//                System.err.println("Current: " + Thread.currentThread());
//                System.err.println("Active: " + Thread.activeCount());

                            float test =
                            ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true))
                            /
                            ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.red[i]}, true));
                            this.FillingResultArray(test,i);
                            }
                            );
                    }
            catch (Exception e) {
                                System.err.println(e.getMessage());
                                }
        return this.result;
    }

    public byte[] GetReseachIPVI()
    {
        try {
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
            IntStream.range(0, this.red.length)
                    .parallel().
                    forEach(
                            (i)->
                            {
//                System.err.println("Current: " + Thread.currentThread());
//                System.err.println("Active: " + Thread.activeCount());
                                float test =
                                        ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true))
                                        /
                                        ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true)+
                                         (float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.red[i]}, true));
                                this.FillingResultArray(test,i);
                            }
                    );
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return this.result;
    }
    public byte[] GetReseachDVI()
    {
        try {
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
            IntStream.range(0, this.red.length)
                    .parallel().
                    forEach(
                            (i)->
                            {
//                System.err.println("Current: " + Thread.currentThread());
//                System.err.println("Active: " + Thread.activeCount());
                                float test =
                                            ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true) -
                                            (float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.red[i]}, true));
                                this.FillingResultArray(test,i);
                            }
                    );
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return this.result;
    }

    public byte[] GetReseachTVI()
    {
        try {
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
            IntStream.range(0, this.red.length)
                    .parallel().
                    forEach(
                            (i)->
                            {
//                System.err.println("Current: " + Thread.currentThread());
//                System.err.println("Active: " + Thread.activeCount());
                                float test =(float) Math.sqrt(
                                        ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true) -
                                        (float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.red[i]}, true))
                                        /
                                        ((float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.nred[i]}, true) +
                                        (float) NumberUtil.getIntFromBytes(new byte[]{0, 0, 0, this.red[i]}, true)));
                                this.FillingResultArray(test,i);
                            }
                    );
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return this.result;
    }

    private void FillingResultArray(float input, int index_array)
    {
        System.arraycopy(NumberUtil.getBytesFromFloat(input,false),0, this.result,index_array*4,4);
    }
}




//        return this.result;
//
//
//        for (int indF=0;indF<dataB3.length;indF++)
//        {
//            Integer valueArrayB3 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB3[indF]}, false);
//            ByteBuffer.wrap(new byte[]{0,0,0,dataB3[indF]}).getInt();
//            Integer valueArrayB4 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB4[indF]}, false);
//            ByteBuffer.wrap(new byte[]{0,0,0,dataB4[indF]}).getInt();
//
//            Float float_resultNDVI
//                    = (float) ((float)valueArrayB3 - (float)valueArrayB4) /
//                    (float) ((float)valueArrayB3 + (float)valueArrayB4) ;
//
//            result_byte_array.order(ByteOrder.LITTLE_ENDIAN).putFloat(float_resultNDVI);
//        }
//        byte[] clone = result_byte_array.array().clone();
//        Arrays.sort(clone);
//        if (clone[clone.length-1]>100)
//        {
//            System.out.println(clone[clone.length-1]);
//        }
//        result_byte_array.array().clone();