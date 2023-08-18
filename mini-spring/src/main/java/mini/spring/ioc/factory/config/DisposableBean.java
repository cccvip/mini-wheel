package mini.spring.ioc.factory.config;

/**
 * @description: 销毁Bean执行
 * @author：carl
 * @date: 2023/8/15
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
