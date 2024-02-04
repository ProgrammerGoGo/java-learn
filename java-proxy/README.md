<img width="791" alt="截屏2023-05-27 下午4 50 38" src="https://github.com/ProgrammerGoGo/java-learn/assets/98639494/cdf43325-a559-47fe-b471-5dd8e1a55fb2">

抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。

真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。

代理（Proxy）类：提供了与真实主题相同的方法，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。

# 静态代理

# JDK代理

* [资料1](https://pdai.tech/md/spring/spring-x-framework-aop-source-4.html#%E4%BB%80%E4%B9%88%E6%98%AFjdk%E4%BB%A3%E7%90%86)
* [资料2](https://www.cnblogs.com/gonjan-blog/p/6685611.html)

```
JDK代理自动生成的class是由sun.misc.ProxyGenerator来生成的。
```

在java的java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口，通过这个类和这个接口可以生成JDK动态代理类和动态代理对象。大致可分为以下四个步骤：

1、创建一个 `InvocationHandler` 对象（也可以使用匿名对象的方法）并实现接口方法
```java
/**
 * 匿名对象实现 InvocationHandler
 *
 * proxy：代表动态代理对象
 * method：代表正在执行的方法（目标方法）
 * args：代表调用目标方法时传入的实参
 */
(proxy, method, args) -> {
    System.out.println(">> before.");
    Object invoke = method.invoke(object, args);
    System.out.println(">> after.");
    return invoke;
}
```
2、使用`Proxy`类的`getProxyClass`静态方法生成一个动态代理类`myProxyClass`。实际上`getProxyClass`方法内部是调用`ProxyGenerator`类的`generateProxyClass`方法实现的

3、获得`myProxyClass`中一个带`InvocationHandler`参数的构造器`constructor`

4、通过构造器`constructor`来创建一个动态实例`myProxy`

注：上面四个步骤可以通过Proxy类的newProxyInstances方法来简化
```java
com.programmer.proxy.jdkproxy.UserProxy#getProxy()
```

jdk为我们的生成了一个叫`$Proxy0`（这个名字后面的0是编号，有多个代理类会一次递增）的代理类，这个类文件时放在内存中的，我们在创建代理对象时，就是通过反射获得这个类的构造方法，然后创建的代理实例。通过对这个生成的代理类源码的查看，我们很容易能看出，动态代理实现的具体过程。

生成的代理类：`$Proxy0 extends Proxy implements UserServiceImpl`，我们看到代理类继承了`Proxy`类，所以也就决定了java动态代理只能对接口进行代理，Java的继承机制注定了这些动态代理类们无法实现对class的动态代理。

我们可以对`InvocationHandler`看做一个中介类，中介类持有一个被代理对象，在`invoke`方法中调用了被代理对象的相应方法。通过聚合方式持有被代理对象的引用，把外部对`invoke`的调用最终都转为对被代理对象的调用。

代理类调用自己方法时，通过自身持有的中介类对象来调用中介类对象的`invoke`方法，从而达到代理执行被代理对象的方法。也就是说，动态代理通过中介类实现了具体的代理功能。

# CGLib代理

* [CGLIB(Code Generation Library) 介绍与原理](https://www.runoob.com/w3cnote/cglibcode-generation-library-intro.html)
* [详述 JDK 和 CGLIB 动态代理的实现原理以及两者的区别](https://github.com/guobinhit/cg-blog/blob/master/articles/others/dynamic-proxy.md)

## 什么是CGLib代理

CGLIB是一个功能强大，高性能的代码生成包。它为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充。通常可以使用Java的动态代理创建代理，但当要代理的类没有实现接口或者为了更好的性能，CGLIB是一个好的选择。

CGLIB作为一个开源项目，其代码托管在github [CGLib源码地址](https://github.com/cglib/cglib)

## CGLib原理
CGLIB 原理：动态生成一个要代理类的子类，子类重写目标类中所有非final的方法。在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。

CGLIB 底层：使用字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。

CGLIB缺点：对于final方法，无法进行代理。


代理类将目标类作为自己的父类并为其中的每个非final委托方法创建两个方法：

一个是与目标方法签名相同的方法，它在方法中会通过super调用目标方法；

另一个是代理类独有的方法，称之为Callback回调方法，它会判断这个方法是否绑定了拦截器（实现了`MethodInterceptor`接口的对象），若存在则将调用`intercept`方法对目标方法进行代理，也就是在前后加上一些增强逻辑。`intercept`中就会调用上面介绍的签名相同的方法。

# 总结

静态代理：通过在代码中显式编码定义一个业务实现类的代理类，在代理类中对同名的业务方法进行包装，用户通过代理类调用被包装过的业务方法；

JDK动态代理：通过接口中的方法名，在动态生成的代理类中调用业务实现类的同名方法；

CGlib动态代理：通过继承业务类，生成的动态代理类是业务类的子类，通过重写业务方法进行代理；

静态代理在编译时产生class字节码文件，可以直接使用，效率高。  
JDK动态代理必须实现InvocationHandler接口，通过invoke调用被委托类接口方法是通过反射方式，比较消耗系统性能，但可以减少代理类的数量，使用更灵活。   
cglib代理无需实现接口，通过生成类字节码实现代理，比反射稍快，不存在性能问题，但cglib会继承目标对象，需要重写方法，所以目标对象不能为final类。  

