package com.zzqa.ds7000.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 通信格式转换
 * <p/>
 * Java和一些windows编程语言如c、c++、delphi所写的网络程序进行通讯时，需要进行相应的转换 高、低字节之间的转换
 * windows的字节序为低字节开头 linux,unix的字节序为高字节开头 java则无论平台变化，都是高字节开头
 */

public class FormatTransfer {
    /**
     * 将int转为低字节在前，高字节在后的byte数组
     *
     * @param n int
     * @return byte[]
     */
    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 将int转为高字节在前，低字节在后的byte数组
     *
     * @param n int
     * @return byte[]
     */
    public static byte[] toHH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 将short转为低字节在前，高字节在后的byte数组
     *
     * @param n short
     * @return byte[]
     */
    public static byte[] toLH(short n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * 将short转为高字节在前，低字节在后的byte数组
     *
     * @param n short
     * @return byte[]
     */
    public static byte[] toHH(short n) {
        byte[] b = new byte[2];
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * 将将int转为高字节在前，低字节在后的byte数组
     *
     * public static byte[] toHH(int number) { int temp = number; byte[] b = new
     * byte[4]; for (int i = b.length - 1; i > -1; i--) { b = new Integer(temp &
     * 0xff).byteValue(); temp = temp >> 8; } return b; }
     *
     * public static byte[] IntToByteArray(int i) { byte[] abyte0 = new byte[4];
     * abyte0[3] = (byte) (0xff & i); abyte0[2] = (byte) ((0xff00 & i) >> 8);
     * abyte0[1] = (byte) ((0xff0000 & i) >> 16); abyte0[0] = (byte)
     * ((0xff000000 & i) >> 24); return abyte0; }
     *
     *
     */

    /**
     * 将float转为低字节在前，高字节在后的byte数组
     */
    public static byte[] toLH(float f) {
        return toLH(Float.floatToRawIntBits(f));
    }

    /**
     * 将float转为高字节在前，低字节在后的byte数组
     */
    public static byte[] toHH(float f) {
        return toHH(Float.floatToRawIntBits(f));
    }

    /**
     * 将String转为byte数组
     */
    public static byte[] stringToBytes(String s, int length) {
        while (s.getBytes().length < length) {
            s += "\0";
        }
        byte b[] = s.getBytes();
        if (b.length > length) {
            byte c[] = new byte[length];
            for (int i = 0; i < length; i++) {
                c[i] = b[i];
            }
            return c;
        } else {
            return b;
        }
    }

    /**
     * 将字节数组转换为String
     *
     * @param b byte[]
     * @return String
     */
    public static String bytesToString(byte[] b) {
        StringBuffer result = new StringBuffer("");
        int length = b.length;
        for (int i = 0; i < length; i++) {
            result.append((char) (b[i] & 0xff));
        }
        return result.toString();
    }

    /**
     * 将字符串转换为byte数组
     *
     * @param s String
     * @return byte[]
     */
    public static byte[] stringToBytes(String s) {
        return s.getBytes();
    }

    /**
     * 将高字节数组转换为int
     *
     * @param b byte[]
     * @return int
     */
    public static int hBytesToInt(byte[] b) {
        int s = 0;
        for (int i = 0; i < 3; i++) {
            if (b[i] >= 0) {
                s = s + b[i];
            } else {
                s = s + 256 + b[i];
            }
            s = s * 256;
        }
        if (b[3] >= 0) {
            s = s + b[3];
        } else {
            s = s + 256 + b[3];
        }
        return s;
    }

    /**
     * 将低字节数组转换为int
     *
     * @param b byte[]
     * @return int
     */
    public static int lBytesToInt(byte[] b) {
        int s = 0;
        for (int i = 0; i < 3; i++) {
            if (b[3 - i] >= 0) {
                s = s + b[3 - i];
            } else {
                s = s + 256 + b[3 - i];
            }
            s = s * 256;
        }
        if (b[0] >= 0) {
            s = s + b[0];
        } else {
            s = s + 256 + b[0];
        }
        return s;
    }

    /**
     * 高字节数组到short的转换
     *
     * @param b byte[]
     * @return short
     */
    public static short hBytesToShort(byte[] b) {
        int s = 0;
        if (b[0] >= 0) {
            s = s + b[0];
        } else {
            s = s + 256 + b[0];
        }
        s = s * 256;
        if (b[1] >= 0) {
            s = s + b[1];
        } else {
            s = s + 256 + b[1];
        }
        short result = (short) s;
        return result;
    }

    /**
     * 低字节数组到short的转换
     *
     * @param b byte[]
     * @return short
     */
    public static short lBytesToShort(byte[] b) {
        int s = 0;
        if (b[1] >= 0) {
            s = s + b[1];
        } else {
            s = s + 256 + b[1];
        }
        s = s * 256;
        if (b[0] >= 0) {
            s = s + b[0];
        } else {
            s = s + 256 + b[0];
        }
        short result = (short) s;
        return result;
    }

    /**
     * 高字节数组转换为float
     *
     * @param b byte[]
     * @return float
     */
    public static float hBytesToFloat(byte[] b) {
        int i = 0;
        if(b[0]==127&&b[1]<0){
            b[0]=126;
        }
        if(b[0]==-1&&b[1]<0){
            b[0]=-2;
        }
        i = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8 | (b[3] & 0xff);
        return Float.intBitsToFloat(i);
    }

    /**
     * 低字节数组转换为float
     *
     * @param b byte[]
     * @return float
     */
    public static float lBytesToFloat(byte[] b) {
        int i = 0;
        i = ((((b[3] & 0xff) << 8 | (b[2] & 0xff)) << 8) | (b[1] & 0xff)) << 8 | (b[0] & 0xff);
        return Float.intBitsToFloat(i);
    }

    /**
     * 将byte数组中的元素倒序排列
     */
    public static byte[] bytesReverseOrder(byte[] b) {
        int length = b.length;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[length - i - 1] = b[i];
        }
        return result;
    }

    /**
     * 打印byte数组
     */
    public static void printBytes(byte[] bb) {
        int length = bb.length;
        for (int i = 0; i < length; i++) {
            System.out.print(bb + " ");
        }
        System.out.println("");
    }

    public static void logBytes(byte[] bb) {
        int length = bb.length;
        String out = "";
        for (int i = 0; i < length; i++) {
            out = out + bb + " ";
        }

    }

    /**
     * 将int类型的值转换为字节序颠倒过来对应的int值
     *
     * @param i int
     * @return int
     */
    public static int reverseInt(int i) {
        int result = FormatTransfer.hBytesToInt(FormatTransfer.toLH(i));
        return result;
    }

    /**
     * 将short类型的值转换为字节序颠倒过来对应的short值
     *
     * @param s short
     * @return short
     */
    public static short reverseShort(short s) {
        short result = FormatTransfer.hBytesToShort(FormatTransfer.toLH(s));
        return result;
    }

    /**
     * 将float类型的值转换为字节序颠倒过来对应的float值
     *
     * @param f float
     * @return float
     */
    public static float reverseFloat(float f) {
        float result = FormatTransfer.hBytesToFloat(FormatTransfer.toLH(f));
        return result;
    }

    public static int getUtcTime() {
        int i = 0;

        i = (int) (new Date().getTime() / 1000);
        return i;

        /*
          // 1、取得本地时间：
          Calendar cal = Calendar.getInstance();

          // 2、取得时间偏移量：
          int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

          // 3、取得夏令时差：
          int dstOffset = cal.get(Calendar.DST_OFFSET);

          // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
          cal.add(java.com.zzqa.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

          // 之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
          i = (int) (cal.getTimeInMillis() / 1000);
          return i;
          */
    }

    public static Date getUtcDate(Date value) {
        Date date = null;

        Calendar cal = Calendar.getInstance();
        // 取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

        // 取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);

        // 从时间里扣除这些差量，即可以取得UTC时间：
        date = new Date(value.getTime() - (zoneOffset + dstOffset));

        return date;
    }

    public static Date getUtcDate(long value) {
        Date date = null;

        Calendar cal = Calendar.getInstance();
        // 取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

        // 取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);

        // 从时间里扣除这些差量，即可以取得UTC时间：
        date = new Date(value - (zoneOffset + dstOffset));

        return date;
    }

    public static long reverseLong(long l) {
        long result = FormatTransfer.hBytesToLong(FormatTransfer.toLH(l));
        return result;
    }

    public static byte[] toLH(long n) {
        byte[] b = new byte[8];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8  & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        b[4] = (byte) (n >> 32 & 0xff);
        b[5] = (byte) (n >> 40 & 0xff);
        b[6] = (byte) (n >> 48 & 0xff);
        b[7] = (byte) (n >> 56 & 0xff);
        return b;
    }

    public static long hBytesToLong(byte[] b) {
        int s = 0;
        for (int i = 0; i < 7; i++) {
            if (b[i] >= 0) {
                s = s + b[i];
            } else {
                s = s + 256 + b[i];
            }
            s = s * 256;
        }
        if (b[7] >= 0) {
            s = s + b[7];
        } else {
            s = s + 256 + b[7];
        }
        return s;
    }
}