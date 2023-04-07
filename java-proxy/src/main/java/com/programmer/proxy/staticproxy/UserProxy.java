package com.programmer.proxy.staticproxy;

/**
 * @Author programmerGoGo
 * @Description
 */
public class UserProxy implements UserService {
    private UserService target;

    public UserProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println(">> before.");
        target.save();
        System.out.println(">> after.");
    }
}
