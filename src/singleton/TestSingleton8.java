package singleton;

/**
 * @Author YSS
 * @Date 2020/6/14 19:01
 */
public class TestSingleton8 {
    public static void main(String[] args) {
        Singleton8.INSTANCE.whatever();
        ClassLoader classLoader = TestSingleton8.class.getClassLoader();
        while (classLoader!=null){
            System.out.println(classLoader.getClass().getName());
            classLoader=classLoader.getParent();
        }
    }
}
