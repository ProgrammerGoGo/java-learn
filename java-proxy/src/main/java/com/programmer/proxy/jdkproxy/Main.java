package com.programmer.proxy.jdkproxy;

/**
 * @Author programmerGoGo
 * @Description
 */
public class Main {

    public static void main(String[] args) throws Exception {
        UserService target = new UserServiceImpl();
        UserService proxy = (UserService) UserProxy.getProxy(target);
        proxy.save();
        
        // 这里我们将jdk生成的代理类输出了出来，方便后面分析使用
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{target.getClass()});

        FileOutputStream os = new FileOutputStream("Proxy0.class");
        os.write(bytes);
        os.close();
    }
}
