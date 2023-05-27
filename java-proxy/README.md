<img width="791" alt="截屏2023-05-27 下午4 50 38" src="https://github.com/ProgrammerGoGo/java-learn/assets/98639494/cdf43325-a559-47fe-b471-5dd8e1a55fb2">
抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
代理（Proxy）类：提供了与真实主题相同的方法，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。

# 静态代理

# JDK代理

* [资料1](https://pdai.tech/md/spring/spring-x-framework-aop-source-4.html#%E4%BB%80%E4%B9%88%E6%98%AFjdk%E4%BB%A3%E7%90%86)

在java的java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口，通过这个类和这个接口可以生成JDK动态代理类和动态代理对象。

大致可分为以下四个步骤：

1、创建一个InvocationHandler对象（也可以使用匿名对象的方法）




# CGLib代理

