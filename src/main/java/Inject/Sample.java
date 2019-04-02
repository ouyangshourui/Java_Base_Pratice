package Inject;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class Sample {

    @Inject
    private HelloPrinter printer;

    public void sayHello(){
        System.out.println("hello Inject");

    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        Sample sample = injector.getInstance(Sample.class);
        sample.sayHello();
    }


}

@Singleton
class HelloPrinter {

    public void print() {
        System.out.println("Hello, World");
    }

}
