package patten;

public class HurrySingleton {
    private static final HurrySingleton INSTANCE = new HurrySingleton();
    private HurrySingleton(){

    }
    public  static HurrySingleton getInstance(){
        return INSTANCE;
    }
}
