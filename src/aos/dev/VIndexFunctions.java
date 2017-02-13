package aos.dev;

import aos.ConverterBytes.NumberUtil;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by xandr on 20.05.16.
 */
public class VIndexFunctions {

    public String switch_case_function;
    public byte[] nred;
    public byte[] red;

    public VIndexFunctions(byte[] nred, byte[] red,String switch_case_function)
    {
        this.nred = nred;
        this.red = red;
        this.switch_case_function = switch_case_function;
    }

    public byte[] constructReseach()
    {
//        switch (this.switch_case_function) {
//            case "NDVI": return GetReseachNDVI(this.nred, this.red);
//            case "RVI" :  return GetReseachRVI(this.nred, this.red);
//            case "IPVI": return GetReseachIPVI(this.nred, this.red);
//            case "DVI" :  return GetReseachDVI(this.nred, this.red);
//            case "TVI" :  return GetReseachTVI(this.nred, this.red);
//        }
        return GetReseachNDVI(this.nred, this.red);
    }

    public byte[] GetReseachNDVI(byte[] dataB4, byte[] dataB3)
    {

        ByteBuffer result_byte_array = ByteBuffer.allocate(dataB3.length*4);
        result_byte_array.position(0);




        for (int indF=0;indF<dataB3.length;indF++)
        {
            Integer valueArrayB3 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB3[indF]}, true);
                    ByteBuffer.wrap(new byte[]{0,0,0,dataB3[indF]}).getInt();
            Integer valueArrayB4 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB4[indF]}, true);
                    ByteBuffer.wrap(new byte[]{0,0,0,dataB4[indF]}).getInt();

            Float float_resultNDVI
                    = (float) ((float)valueArrayB3 - (float)valueArrayB4) /
                      (float) ((float)valueArrayB3 + (float)valueArrayB4) ;

            if (valueArrayB3>0 && valueArrayB4.equals(valueArrayB3) )
            {
                System.out.println(valueArrayB4);
            }
            result_byte_array.order(ByteOrder.LITTLE_ENDIAN).putFloat(float_resultNDVI);
        }
        return result_byte_array.array();
    }
    public byte[] GetReseachRVI(byte[] dataB4, byte[] dataB3)
    {

        ByteBuffer result_byte_array = ByteBuffer.allocate(dataB3.length*4);
        result_byte_array.position(0);
        for (int indF=0;indF<dataB3.length;indF++)
        {
            Integer valueArrayB3 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB3[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB3[indF]}).getInt();
            Integer valueArrayB4 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB4[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB4[indF]}).getInt();

            Float float_resultNDVI
                    = (float) ((float)valueArrayB3 / (float)valueArrayB4);


            result_byte_array.order(ByteOrder.LITTLE_ENDIAN).putFloat(float_resultNDVI);
        }
        return result_byte_array.array();
    }
    public byte[] GetReseachIPVI(byte[] dataB4, byte[] dataB3)
    {

        ByteBuffer result_byte_array = ByteBuffer.allocate(dataB3.length*4);
        result_byte_array.position(0);
        for (int indF=0;indF<dataB3.length;indF++)
        {
            Integer valueArrayB3 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB3[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB3[indF]}).getInt();
            Integer valueArrayB4 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB4[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB4[indF]}).getInt();

            Float float_resultNDVI
                    = (float) valueArrayB3/
                    (float) ((float)valueArrayB3 + (float)valueArrayB4) ;

            result_byte_array.order(ByteOrder.LITTLE_ENDIAN).putFloat(float_resultNDVI);
        }
        return result_byte_array.array();
    }
    public byte[] GetReseachDVI(byte[] dataB4, byte[] dataB3)
    {

        ByteBuffer result_byte_array = ByteBuffer.allocate(dataB3.length*4);
        result_byte_array.position(0);
        for (int indF=0;indF<dataB3.length;indF++)
        {
            Integer valueArrayB3 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB3[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB3[indF]}).getInt();
            Integer valueArrayB4 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB4[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB4[indF]}).getInt();

            Float float_resultNDVI
                    = (float) valueArrayB3 - (float)valueArrayB4 ;


            result_byte_array.order(ByteOrder.LITTLE_ENDIAN).putFloat(float_resultNDVI);
        }
        return result_byte_array.array();
    }

    public byte[] GetReseachTVI(byte[] dataB4, byte[] dataB3)
    {

        ByteBuffer result_byte_array = ByteBuffer.allocate(dataB3.length*4);
        result_byte_array.position(0);
        for (int indF=0;indF<dataB3.length;indF++)
        {
            Integer valueArrayB3 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB3[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB3[indF]}).getInt();
            Integer valueArrayB4 = NumberUtil.getIntFromBytes(new byte[]{0,0,0,dataB4[indF]}, false);
            ByteBuffer.wrap(new byte[]{0,0,0,dataB4[indF]}).getInt();

            Float float_resultNDVI
                    = (float) ((float)valueArrayB3 - (float)valueArrayB4) /
                    (float) ((float)valueArrayB3 + (float)valueArrayB4) ;
            float_resultNDVI = (float) Math.sqrt(float_resultNDVI);
            result_byte_array.order(ByteOrder.LITTLE_ENDIAN).putFloat(float_resultNDVI);
        }
        return result_byte_array.array();
    }
}
