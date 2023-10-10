package com.tugalsan.api.bytes.client;

import java.nio.charset.StandardCharsets;

public class TGS_ByteLengthUtils {

    public static int byteLengthInteger() {
        return 4;
    }

    public static int byteLengthFloat() {
        return 4;
    }

    public static int byteLengthLong() {
        return 8;
    }

    public static int byteLengthDouble() {
        return 8;
    }

    public static int byteLengthStringNative(String value) {
        return value.length();
    }

    public static int byteLengthStringUTF8(String value) {
        return value.getBytes(StandardCharsets.UTF_8).length;
    }

    public static  int byteLengthStringUTF16(String value) {
        return value.getBytes().length;
    }
}
