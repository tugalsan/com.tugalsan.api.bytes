package com.tugalsan.api.bytes.client;

import com.tugalsan.api.unsafe.client.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
//import java.nio.ByteBuffer;//GWT does not like u; check on 2.10 version again! or use https://github.com/Vertispan/gwt-nio
import java.util.stream.IntStream;

public class TGS_ByteArrayUtils {

    private static String hex2Text(byte byteDigit) {
        var hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((byteDigit >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((byteDigit & 0xF), 16);
        return new String(hexDigits);
    }

    public static String hex2Text(byte[] byteDigits) {
        var sb = new StringBuffer();
        IntStream.range(0, byteDigits.length).forEachOrdered(i -> sb.append(hex2Text(byteDigits[i])));
        return sb.toString();
    }

    private static byte hex2Byte(CharSequence hexDigit) {
        return TGS_UnSafe.call(() -> {
            var byteDigit0 = Character.digit(hexDigit.charAt(0), 16);
            var byteDigit1 = Character.digit(hexDigit.charAt(1), 16);
            return (byte) ((byteDigit0 << 4) + byteDigit1);
        }, exception -> {
            return TGS_UnSafe.thrwReturns(TGS_ByteArrayUtils.class.getSimpleName(), "hex2Byte(CharSequence hexDigit)", "hexDigit:" + hexDigit + ", e:" + exception.getMessage());
        });
    }

    public static byte[] hex2ByteArray(CharSequence hexDigits) {
        if (hexDigits.length() % 2 == 1) {
            TGS_UnSafe.thrw(TGS_ByteArrayUtils.class.getSimpleName(), "hex2ByteArray(CharSequence hexDigits)", "Invalid hexadecimal text supplied. -> hexDigits.length() % 2 == 1");
        }
        var bytes = new byte[hexDigits.length() / 2];
        for (int i = 0; i < hexDigits.length(); i += 2) {
            bytes[i / 2] = hex2Byte(hexDigits.subSequence(i, i + 2));
        }
        return bytes;
    }

    public static int toInteger(byte[] byteArrray4) {
//        return ByteBuffer.wrap(byteArrray4).getInt();//GWT does not like u; check on 2.10 version again! or use https://github.com/Vertispan/gwt-nio
        return TGS_UnSafe.call(() -> new BigInteger(byteArrray4).intValue());
    }

    public static byte[] toByteArray(int integer) {
//        return ByteBuffer.allocate(4).putInt(integer).array();//GWT does not like u; check on 2.10 version again! or use https://github.com/Vertispan/gwt-nio
        return BigInteger.valueOf(integer).toByteArray();
    }

    public static void transfer(byte[] source, OutputStream dest0) {
        TGS_UnSafe.run(() -> {
            try ( var dest = dest0) {
                dest.write(source);
            }
        });
    }

    public static byte[] toByteArray(InputStream is0) {
        return TGS_UnSafe.call(() -> {
            try ( var is = is0;  var baos = new ByteArrayOutputStream();) {
                int reads = is.read();
                while (reads != -1) {
                    baos.write(reads);
                    reads = is.read();
                }
                return baos.toByteArray();
//            return is.readAllBytes();//GWT does not like it
            }
        });
    }

    public static byte[] toByteArray(CharSequence sourceText) {
        return toByteArray(sourceText, StandardCharsets.UTF_8);
    }

    public static byte[] toByteArray(CharSequence sourceText, Charset charset) {
        return sourceText.toString().getBytes(charset);
    }

    public static String toString(byte[] source) {
        return toString(source, StandardCharsets.UTF_8);
    }

    public static String toString(byte[] source, Charset charset) {
        return new String(source, charset);
    }
}
