package com.programmer.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author programmerGoGo
 * @Description
 */
public class UserProxy implements MethodInterceptor {
    private Object target;

    public UserProxy(Object target) {
        this.target = target;
    }

    /**
    * 功能主要是在调用业务类方法之前和之后添加增强逻辑.
    * 因为 intercept方法 具有 MethodProxy类型 参数的原因 不再需要代理类的引用对象了,直接通过 proxy 对象访问被代理对象的方法(这种方式更快)。
    * 当然 也可以通过反射机制，通过 method 引用实例 Object result = method.invoke(target, args); 形式反射调用被代理类方法，
    * target 实例代表被代理类对象引用, 初始化 MethodInterceptor实现类（这里是UserProxy类） 时候被赋值 。但是Cglib不推荐使用这种方式
    * 
    * @param obj    代表Cglib 生成的动态代理类 【代理对象本身】
    * @param method 代理类中被拦截的接口方法 Method 实例 【目标对象的方法 用于反射】
    * @param args   接口方法参数
    * @param proxy  用于调用父类真正的业务类方法。可以直接调用被代理类接口方法
    * @return
    * @throws Throwable
    */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(">> before.");
        Object invoke = proxy.invokeSuper(obj, args);        // 等同于 Object invoke = method.invoke(target, args);
        System.out.println(">> after.");
        return invoke;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        // 为代理类指定需要代理的类，也即是父类
        enhancer.setSuperclass(target.getClass());
        // 设置方法拦截器回调引用，对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept() 方法进行拦截
        enhancer.setCallback(this);
        // 获取动态代理类对象并返回
        return enhancer.create();
    }
}
