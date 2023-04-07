package com.programmer.proxy.jdkproxy;

/**
 * @Author programmerGoGo
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        UserService proxy = (UserService) UserProxy.getProxy(target);
        proxy.save();
    }
}
