package com.lsj.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {
        String msg1 = "Hello";
        String msg2 = " world";
        Map<String, String> map = new HashMap<>();
        System.out.println(getString(map, () -> msg1 + msg2));


    }

    private static String getString(Map<String, String> map, Supplier<String> param) {
        return Optional.ofNullable(map.get("1"))
                .orElse(param.get());
    }
}
