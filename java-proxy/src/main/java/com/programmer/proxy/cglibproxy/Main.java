package com.programmer.proxy.cglibproxy;

/**
 * @Author programmerGoGo
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        UserService target = new UserService();
        UserService proxy = (UserService) new UserProxy(target).getProxy();
        proxy.save();
    }
}
