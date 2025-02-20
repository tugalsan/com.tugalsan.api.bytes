package com.tugalsan.api.bytes.client;

import java.nio.charset.StandardCharsets;

public class TGS_ByteLengthUtils {
    
    private TGS_ByteLengthUtils(){
        
    }

    public static int typeInteger() {
        return 4;
    }

    public static int typeFloat() {
        return 4;
    }

    public static int typeLong() {
        return 8;
    }

    public static int typeDouble() {
        return 8;
    }

    public static int typeStringNative(String value) {
        return value.length();
    }

    public static int typeStringUTF8(String value) {
        return value.getBytes(StandardCharsets.UTF_8).length;
    }

    public static int typeStringUTF16(String value) {
        return value.getBytes().length;
    }
}
