package patten;

public class LazySignleton {
    private static LazySignleton INSTANCE = null;
    private LazySignleton(){

    }
    public static LazySignleton getInstance(){

        if(INSTANCE == null){
            synchronized (LazySignleton.class) {
                if(INSTANCE == null){
                    INSTANCE = new LazySignleton();
                }
            }
        }
        return INSTANCE;
    }
}
