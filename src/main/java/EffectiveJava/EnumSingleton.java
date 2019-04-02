package EffectiveJava;

public enum  EnumSingleton {
    INSTANCE;
    public void leaveTheBuilding(){
        System.out.println("whoa baby,i'm outta here");
    }

    public static void main(String[] args) {

        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        enumSingleton.leaveTheBuilding();

    }
}
