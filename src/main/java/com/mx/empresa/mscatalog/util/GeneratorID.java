package com.mx.empresa.mscatalog.util;

import java.util.UUID;

public class GeneratorID {

    private GeneratorID() {
        throw new UnsupportedOperationException("Utility class not should be instantiated");
    }

    public static String generateUniqueId(Integer length) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
    }
}
