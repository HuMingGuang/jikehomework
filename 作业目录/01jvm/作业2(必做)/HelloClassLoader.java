import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = "Hello";
        String methodName = "hello";
        // 类的加载
        ClassLoader classLoader = new HelloClassLoader();
        Class<?> clazz = classLoader.loadClass(className);
        // 打印类的方法
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
        }
        // 创建类的对象:获取类的构造方法，实例化出一个对象
        Object obj = clazz.getDeclaredConstructor().newInstance();

        // 调用具体对象obj其名字为methodName的方法
        Method method = clazz.getMethod(methodName);
        method.invoke(obj);
    }

    /**
     * 重写findClass方法，该方法在进行loadClass时会使用到
     *
     * @param name 类名
     * @return 返回对应的类实例
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        final String suffix = ".xlass";
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + suffix)) {
            // 1.读取出输入流，并放置bytes中
            int len = inputStream.available();
            byte[] bytes = new byte[len];
            inputStream.read(bytes);
            // 2.解码
            byte[] resBytes = decode(bytes);
            // 3.读取resBytes中从0~resBytes.length的数据，将其转化为类的实例，并将该类的实例名定义为name
            return defineClass(name, resBytes, 0, resBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    /**
     * 解码
     *
     * @param bytes 源码
     * @return 解码后的码
     */
    private static byte[] decode(byte[] bytes) {
        byte[] resBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            // 进行255解码
            resBytes[i] = (byte) (255 - bytes[i]);
        }
        return resBytes;
    }
}
