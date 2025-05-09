package week4;

import java.util.Arrays;

public class Week4Main {

    public static void main(String[] args) throws Exception {

        SomeApp app1 = SomeApp.class
                .getDeclaredConstructor()
                .newInstance();

        for (var method : SomeApp.class.getDeclaredMethods()) {
            System.out.println(Arrays.toString(
                    method.getDeclaredAnnotations()
            ));
        }

    }
}
















// // eager will run here
//        LazyObject<Integer> obj1 = new LazyObject<>(() -> 1234);
//        LazyObject<String> obj2 = new LazyObject<>(() -> "10 TB of info");
//
//        // stuff here
//
//        // lazy will run here
//        System.out.println(obj1.getValue());
//        System.out.println(obj2.getValue());