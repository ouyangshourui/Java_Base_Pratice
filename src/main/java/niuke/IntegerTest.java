package niuke;

public class IntegerTest {
    public static void main(String argn[]){
        int i=7;
        String str8=Integer.toString(i,8);
        String str16=Integer.toString(i,16).toUpperCase();
        System.out.println("0"+str8+" 0X"+str16);
    }
}
