package mini.spring.beans.factory.bean;

/**
 * @description: bean引用依赖
 * @author：carl
 * @date: 2023/8/12
 */
public class BeanReference {

    private final String name;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
