package InnerStaticClass;

/**
 * 对于静态类总结是：1.如果类的构造器或静态工厂中有多个参数，设计这样类时，最好使用Builder模式，特别是当大多数参数都是可选的时候。
 2.如果现在不能确定参数的个数，最好一开始就使用构建器即Builder模式。
 */
public class InnerClass {
    private  int age;
    private  String name;
    private  static  class Builder{
        public int getAge() {
            return age;
        }

        public Builder withsetAge(int age) {
            this.age = age;
            return this;
        }


        public Builder withsetName(String name) {
            this.name = name;
            return this;
        }

        private  int age;
        private  String name;

        public Builder(int age) {
            this.age = age;
        }

        public InnerClass build(){
            return new InnerClass(this);
        }



    }

    public InnerClass(Builder builder) {
        this.age = builder.age;
        this.name=builder.name;

    }

    @Override
    public String toString() {
        return "InnerClass{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {

        InnerClass innerClass = new Builder(2).withsetAge(3).withsetName("test").build();
        System.out.println(innerClass.toString());

    }
}
