/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package collection;

/**
 * @author yougu
 * @version : Main.java, v 0.1 2021年06月23日 5:56 下午 yougu Exp $
 */
import java.util.Scanner;
import java.util.Stack;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<String> resultStack = new Stack();
        StringBuffer outPut = new StringBuffer();
        StringBuffer tmpWord= new StringBuffer();
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String input=  in.nextLine();
            char[] inputArray= input.toCharArray();
            for( char ch:inputArray){
                if(('a'<=ch&ch<='z')||('A'<=ch&&ch<='Z')) {
                    tmpWord.append(ch);
                }else{
                    if(tmpWord.length()>0){
                        resultStack.add(tmpWord.toString());
                        tmpWord.delete(0,tmpWord.length());
                    }
                }
            }

            if(tmpWord.length()>0){
                resultStack.add(tmpWord.toString());
                tmpWord.delete(0,tmpWord.length());
            }
            while (!resultStack.isEmpty()){
                outPut.append(resultStack.pop());
                if(resultStack.size()>0)
                  outPut.append(" ");
            }

            System.out.println(outPut.toString());
            resultStack.clear();
            outPut.delete(0,outPut.length());
        }
    }
}