package EffectiveJava;

import java.util.Objects;

public class RequireNonNull {
    private  final  String bar;

    public RequireNonNull(String bar) {
        if(bar=="")
            bar=null;
        Objects.requireNonNull(bar,"bar must not be null");
        this.bar = bar;
    }

    public static void main(String[] args) {
        RequireNonNull requireNonNull = new RequireNonNull("");

    }
}
