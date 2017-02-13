package aos.ConverterBytes;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oshchepkovayu on 13.02.17.
 */
public class ConverByteToObject {

    private List<?> listObject;
    private List<?> listArrayData;



    private byte[] dataImagesByte;
    private short[] dataImagesShort;
    private short[] dataImagesUShort;
    private int[] dataImagesInt;
    private float[] dataImagesFloat;
    private double[] dataImagesDouble;

    public ConverByteToObject(byte[] _byteObjectDataImage)
    {
        setListObject(
                convertValueFromObjectDataImage(_byteObjectDataImage)
                        );

        if (getListObject().get(0)                      instanceof byte[])
            {setDataImagesByte((byte[])     getListObject().get(0));}

        else if (getListObject().get(0)                instanceof short[])
            {setDataImagesShort((short[])   getListObject().get(0));}

        else if (getListObject().get(0)                  instanceof int[])
            {setDataImagesInt((int[])       getListObject().get(0));}

        else if ( getListObject().get(0)               instanceof float[])
            {setDataImagesFloat((float[])   getListObject().get(0));}

        else if (getListObject().get(0)               instanceof double[])
            {setDataImagesDouble((double[]) getListObject().get(0));}

    }

    public int getSizeArray()
    {
        if        (getDataImagesByte() !=null)
        {return   getDataImagesByte().length;}

        else  if (getDataImagesShort() !=null)
        {return  getDataImagesShort().length;}

        else    if (getDataImagesInt() !=null)
        {return    getDataImagesInt().length;}

        else if ( getDataImagesFloat() !=null)
        {return  getDataImagesFloat().length;}

        else
        {return getDataImagesDouble().length;}
    }
    private List<?> convertValueFromObjectDataImage(byte[] _byteObjectDataImage)
    {
        List<?> clone = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(_byteObjectDataImage);
            ois = new ObjectInputStream(bis);
            clone = (List<?>) ois.readObject();
            ois.close();
            bis.close();
            return clone;
        }
        catch (Exception e) {
            System.err.print("Unable to deep copy object");
            return  null;
        }
    }


    public List<?> getDataImage()
    {
        if                    (getDataImagesByte() !=null)
        {return Arrays.asList(getDataImagesByte())      ;}

        else  if             (getDataImagesShort() !=null)
        {return  Arrays.asList(getDataImagesShort())    ;}

        else    if             (getDataImagesInt() !=null)
        {return  Arrays.asList(getDataImagesInt())      ;}

        else if              (getDataImagesFloat() !=null)
        {return Arrays.asList(getDataImagesFloat())     ;}

        else
        {return Arrays.asList(getDataImagesDouble())    ;}
    }
    public float getDataPixelImage(int ind)
    {
        if                    (getDataImagesByte() !=null)
        {return getDataImagesByte()[ind]                ;}

        else  if             (getDataImagesShort() !=null)
        {return  getDataImagesShort()[ind]    ;}

        else    if             (getDataImagesInt() !=null)
        {return  getDataImagesInt()[ind]      ;}

        else
//            if              (getDataImagesFloat() !=null)
        {return getDataImagesFloat()[ind]     ;}

//        else if            (getDataImagesDouble() !=null)
//        {return getDataImagesDouble()[ind]    ;}
//        return null
    }





    public List<?> getListObject() {
        return listObject;
    }

    public void setListObject(List<?> listObject) {
        this.listObject = listObject;
    }

    public byte[] getDataImagesByte() {
        return dataImagesByte;
    }

    public void setDataImagesByte(byte[] dataImagesByte) {
        this.dataImagesByte = dataImagesByte;
    }

    public short[] getDataImagesShort() {
        return dataImagesShort;
    }

    public void setDataImagesShort(short[] dataImagesShort) {
        this.dataImagesShort = dataImagesShort;
    }

    public int[] getDataImagesInt() {
        return dataImagesInt;
    }

    public void setDataImagesInt(int[] dataImagesInt) {
        this.dataImagesInt = dataImagesInt;
    }

    public float[] getDataImagesFloat() {
        return dataImagesFloat;
    }

    public void setDataImagesFloat(float[] dataImagesFloat) {
        this.dataImagesFloat = dataImagesFloat;
    }

    public double[] getDataImagesDouble() {
        return dataImagesDouble;
    }

    public void setDataImagesDouble(double[] dataImagesDouble) {
        this.dataImagesDouble = dataImagesDouble;
    }


}
