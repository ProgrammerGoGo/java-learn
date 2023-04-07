package com.programmer.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @Author programmerGoGo
 * @Description
 */
public class UserProxy {

    public static Object getProxy(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println(">> before.");
                    Object invoke = method.invoke(object, args);
                    System.out.println(">> after.");
                    return invoke;
                });
    }
}
