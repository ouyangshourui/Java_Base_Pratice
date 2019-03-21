package collection;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public class PersonIterator extends AbstractCollection<Person> {
    ArrayList<Person> list = new ArrayList<>();
    Person persons[];
    int size=0;
    int index=0;



    public PersonIterator(int size) {
        this.size = size;
        persons = new Person[5];
    }

    public  boolean add(Person p){

        if(index>=persons.length){
            return false;
        }else {
            persons[index++]=p;
            return true;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator<Person>(){
            int innerIndex=0;
            @Override
            public boolean hasNext() {
                if(innerIndex<index){
                    return true;
                }else return false;
            }

            @Override
            public Person next() {
                return persons[innerIndex++];
            }

            @Override
            public void remove() {

            }

        };


    }

    @Override
    public int size() {
        return size;
    }


    public static void main(String[] args) {
        Person p1 = new Person("aa",11);
        Person p2 = new Person("bb",11);
        Person p3 = new Person("cc",11);
        Person p4 = new Person("dd",11);
        Person p5 = new Person("ee",11);

        PersonIterator personIterator = new PersonIterator(5);
        personIterator.add(p1);
        personIterator.add(p2);
        personIterator.add(p3);
        personIterator.add(p4);
        personIterator.add(p5);


        for(Person p:personIterator.persons){
            System.out.println(p);

        }




    }


}
