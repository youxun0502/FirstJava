package reference;

/**
 * Created by vincent on 2017/7/10.
 */
public class PassStringArrayTest {
    public static void main(String[] args) {
        
        String[] names = {"David"};
        System.out.println("before update name = "+names[0]);
        update(names);
        System.out.println("after update name = "+names[0]);
        
    }

    public static void update(String[] names1){
        names1[0] = "Vincent";
    }
}
