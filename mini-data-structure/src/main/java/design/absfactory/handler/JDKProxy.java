package design.absfactory.handler;

import com.alibaba.fastjson2.JSON;
import design.absfactory.IIR.IR;
import design.absfactory.adapter.ICacheAdapter;
import design.absfactory.adapter.impl.EgmAdapter;
import design.absfactory.service.CacheService;
import design.absfactory.service.impl.CacheServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxy {
    private static Logger logger = LoggerFactory.getLogger(JDKProxy.class);

    public static <T> T getProxy(Class<T> interfaceClass, ICacheAdapter cacheAdapter) throws Exception {
        InvocationHandler handler = new JDKInvocationHandler(cacheAdapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        logger.info(JSON.toJSONString(classes));
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, handler);
    }

    public static void main(String[] args) {
        CacheService proxy_EGM = null;
        try {
            proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EgmAdapter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        proxy_EGM.set("user_name_01","xiao");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println(val01);
        proxy_EGM = new CacheServiceImpl();
        proxy_EGM.set("user_name_01","cc");
        val01 = proxy_EGM.get("user_name_01");
        System.out.println(val01);
    }
}
