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

在java的java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口，通过这个类和这个接口可以生成JDK动态代理类和动态代理对象。

大致可分为以下四个步骤：

1、创建一个 `InvocationHandler` 对象（也可以使用匿名对象的方法）并实现接口方法
```java
// 匿名对象实现 InvocationHandler
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

