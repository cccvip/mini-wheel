package mini.spring.test.service;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello";
    }
}
