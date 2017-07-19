package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Calculator {
    int add(int a, int b);
}

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/8.
 */
public class TestProxy {
    private Calculator proxy;

    public TestProxy(Calculator calculator, InvocationHandler handler) {
        proxy = (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(),
                calculator.getClass().getInterfaces(),
                handler);

    }

    public Calculator getProxy() {
        return proxy;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator() {
            @Override
            public int add(int a, int b) {
                System.out.println(a + " + " + b + " = " + (a + b));
                return a + b;
            }
        };
        LogHandler handler = new LogHandler(calculator);
        TestProxy testProxy = new TestProxy(calculator, handler);
        Calculator proxy = testProxy.getProxy();
        proxy.add(3, 4);
    }
}

class LogHandler implements InvocationHandler {
    private Object obj;

    LogHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.doBefore();
        Object o = method.invoke(obj, args);
        this.doAfter();
        return o;
    }

    private void doBefore() {
        System.out.println("do this before");
    }

    private void doAfter() {
        System.out.println("do this after");
    }
}