package com.programmer.proxy.staticproxy;

/**
 * @Author programmerGoGo
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        UserService userProxy = new UserProxy(target);
        userProxy.save();
    }
}
