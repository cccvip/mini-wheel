package mini.spring.ioc.factory.bean;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
