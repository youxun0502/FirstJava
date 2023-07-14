package lambda;

import constructor.Car;
import interFace.Notebook;

import java.lang.reflect.Method;
import java.util.*;

public class TestLambda {
    public static void main(String[] args) {

        MyLambda myLambda;
        //anonymous class
        myLambda = new MyLambda() {
            @Override
            public int randomIntValue(int x) {
                return 123*x;
            }
        };
        System.out.println(myLambda.randomIntValue(1));//123
        
        final int divide = 10;
        
        MyLambda myLambda1;
        myLambda1 = x -> 123 * x / divide;
        System.out.println(myLambda1.randomIntValue(2));//246

        MyLambda myLambda2 = TestLambda::generateRandom;
        System.out.println("myLambda2 = " + myLambda2);
        for (Method declaredMethod : TestLambda.class.getDeclaredMethods()) {
            System.out.println(declaredMethod+",hash="+declaredMethod.hashCode());
        }
        System.out.println(myLambda2.randomIntValue(3));

        //Consumer
        //foreach
        List<String> names = new ArrayList<>();
        names.add("Vincent");
        names.add("David");
        names.add("Mary");
        names.add("Kevin");
        names.add("Jordan");
        names.add("Ken");
        names.forEach(name -> {
            System.out.println(name);
        });
        //
        names.forEach(name -> System.out.println(name) );

        //method reference, println符合Consumer的方法
        names.forEach(System.out::println);

        //2. Function
        Map<String, Integer> result = new HashMap<>();
        List<String> votes = Arrays.asList("小英","小明","小王","小英","小白","小王");
        /*
        for (String name : votes) {
            if ( !result.containsKey(name)){
                result.put(name, 0);
            }
            int newCount = result.get(name) + 1;
            result.put(name, newCount);
        }  */
        //可以用Function
        for (String name : votes) {
            result.computeIfAbsent(name, key -> 0);
            result.computeIfPresent(name, (key, value) -> {
                return value +1;
            });
        }
        System.out.println(result);

        names.removeIf(name -> {
            return name.length() <= 5;
        });
        System.out.println(names);

        //Method Reference : addWarranty()
        Notebook nb = new Notebook("Asus", 30000, 365);
        Notebook nb1 = new Notebook("Acer", 30000, 300);
        Notebook nb2 = new Notebook("Asus", 30000, 200);
        List<Notebook> nbs = Arrays.asList(nb, nb1, nb2);
        nbs.forEach(item -> item.addWarranty());
        

        nbs.forEach(Notebook::addWarranty);
        nbs.forEach(item-> System.out.println(item.desc()));
        Map<Notebook, Integer> map = new HashMap<>();
        map.put(nb, 50);
        map.put(nb1, 75);
        map.put(nb2, 100);

        map.forEach((item, newWarranty) -> item.setWarranty(newWarranty));
        map.forEach(Notebook::setWarranty);
        nbs.forEach(item-> System.out.println(item.desc()));

        MyCarLambda lambda3 = Car::new;
        Car car = lambda3.produce("red");
        System.out.println(car.getColor()+","+car.getModel());


    }

    public static int generateRandom(int y){
        return 123 * y;
    }

}
