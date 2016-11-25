package aos.ConverterBytes;

import java.nio.*;
  
public class NumberUtil {
    public static byte[] getBytesFromShort(short value, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.allocate(2).putShort(value).array();
        else
            return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array();
    }
     
    public static short getShortFromBytes(byte[] array, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.wrap(array, 0, 2).getShort();
        else
            return ByteBuffer.wrap(array, 0, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }
     
    public static byte[] getBytesFromInt(int value, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.allocate(4).putInt(value).array();
        else
            return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }
     
    public static int getIntFromBytes(byte[] array, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.wrap(array, 0, 4).getInt();
        else
            return ByteBuffer.wrap(array, 0, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }
     
    public static byte[] getBytesFromLong(long value, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.allocate(8).putLong(value).array();
        else
            return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(value).array();
    }
     
    public static long getLongFromBytes(byte[] array, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.wrap(array, 0, 8).getLong();
        else
            return ByteBuffer.wrap(array, 0, 8).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }
     
    public static byte[] getBytesFromFloat(float value, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.allocate(4).putFloat(value).array();
        else
            return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array();
    }
     
    public static float getFloatFromBytes(byte[] array, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.wrap(array, 0, 4).getFloat();
        else
            return ByteBuffer.wrap(array, 0, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }
     
    public static byte[] getBytesFromDouble(double value, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.allocate(8).putDouble(value).array();
        else
            return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(value).array();
    }
     
    public static double getDoubleFromBytes(byte[] array, boolean bigEndian) {
        if (bigEndian)
            return ByteBuffer.wrap(array, 0, 8).getDouble();
        else
            return ByteBuffer.wrap(array, 0, 8).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }
}