package EffectiveJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 并非所有的类都能用try-with-resources处理。实现AutoCloseable接口是使用try-with-resources语句的前提。
 * 在try-with-resources语句中，只有实现了AutoCloseable接口的类才会被视为资源进行相关处理，否则会出现编译错误
 */
public class TryResource {
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            return bufferedReader.readLine();
        }
    }

    public static void main (String[] args) throws  Exception {
        //String path = args[0];
        System.out.println(firstLineOfFile("pom.xml"));
    }
}
