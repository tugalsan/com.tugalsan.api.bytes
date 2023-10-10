package com.tugalsan.api.bytes.client;

import java.nio.charset.StandardCharsets;

public class TGS_ByteLengthUtils {

    public int byteLengthInteger() {
        return 4;
    }

    public int byteLengthFloat() {
        return 4;
    }

    public int byteLengthLong() {
        return 8;
    }

    public int byteLengthDouble() {
        return 8;
    }

    public int byteLengthStringNative(String value) {
        return value.length();
    }

    public int byteLengthStringUTF8(String value) {
        return value.getBytes(StandardCharsets.UTF_8).length;
    }

    public int byteLengthStringUTF16(String value) {
        return value.getBytes().length;
    }
}
