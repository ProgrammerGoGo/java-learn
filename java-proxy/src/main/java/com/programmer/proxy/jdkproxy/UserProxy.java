package com.programmer.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @Author programmerGoGo
 * @Description
 */
public class UserProxy {

    public static Object getProxy(Object object) {
        System.out.println("-------------------第一种创建代理类方法--------------"); 
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println(">> before.");
                    Object invoke = method.invoke(object, args);
                    System.out.println(">> after.");
                    return invoke;
                });

       //  System.out.println("-------------------第二种创建代理类方法--------------");        
       //  /**
       //  *  动态代理对象步骤
       //  *      1、创建一个与代理对象相关联的 InvocationHandler，以及真实的委托类实例
       //  *      2、Proxy类的getProxyClass静态方法生成一个动态代理类stuProxyClass，该类继承Proxy类，实现 Person.java接口；JDK动态代理的特点是代理类必须继承Proxy类
       //  *      3、通过代理类 proxyClass 获得他的带InvocationHandler 接口的构造函数 ProxyConstructor
       //  *      4、通过 构造函数实例 ProxyConstructor 实例化一个代理对象，并将  InvocationHandler 接口实例传递给代理类。
       //  */
       // // 1、创建 InvocationHandler 实例并设置代理的目标类对象
       // Person persontwo = new SoftwareEngineer("Vincent");
       // InvocationHandler Handlertwo = new PersonInvocationHandler<>(persontwo);        
       // // 2 创建代理类,是一个字节码文件, 把 proxyClass 保存起来就能看到 他继承Proxy 类，实现Person接口
       // Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});        
       // /** 代理类信息 */
       // System.out.println("package = " + proxyClass.getPackage() + " SimpleName = " + proxyClass.getSimpleName() + " name =" + proxyClass.getName() + " CanonicalName = " +                
       //                    "" + proxyClass.getCanonicalName() + " 实现的接口 Interfaces = " + Arrays.toString(proxyClass.getInterfaces()) +                
       //                    " superClass = " + proxyClass.getSuperclass() + " methods =" + Arrays.toString(proxyClass.getMethods()));        
       // // 3、  通过 proxyClass 获得 一个带有InvocationHandler参数的构造器constructor
       // Constructor<?> ProxyConstructor = proxyClass.getConstructor(InvocationHandler.class);        
       // // 4、通过构造器创建一个  动态代理类 实例
       // Person stuProxy = (Person) ProxyConstructor.newInstance(Handlertwo);        
       //  /** 检测生成的类是否是代理类 */
       // System.out.println("stuProxy isProxy "+Proxy.isProxyClass(stuProxy.getClass()));        
       //  /** 获取 代理类关联的 InvocationHandler 是哪个*/
       // InvocationHandler handlerObject = Proxy.getInvocationHandler(stuProxy);
       // System.out.println(handlerObject.getClass().getName());
       // stuProxy.goWorking(stuProxy.getName(), "广州");        
       //  // 保存代理類
       // saveClass("$PersonProxy0", proxyClass.getInterfaces(), "D:/123/");
    }
}
