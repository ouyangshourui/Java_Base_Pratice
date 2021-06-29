package niuke;

public class KMP {
    public static int kmp (String S, String T) {
        // write code here
        int count =0 ;
        for(int i=0;i<=T.length()-S.length();i++){
            String windowStr =T.substring(i,i+S.length());
            if(S.equals(windowStr)){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        String s = "ababab";
        String t = "abababab";
        System.out.println(kmp(s,t));


    }
}
