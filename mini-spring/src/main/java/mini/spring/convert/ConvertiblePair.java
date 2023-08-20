package mini.spring.convert;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public class ConvertiblePair {
    private final Class<?> sourceType;

    private final Class<?> targetType;

    public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    public Class<?> getSourceType() {
        return this.sourceType;
    }

    public Class<?> getTargetType() {
        return this.targetType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != ConvertiblePair.class) {
            return false;
        }
        ConvertiblePair other = (ConvertiblePair) obj;
        return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);

    }

    @Override
    public int hashCode() {
        return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
    }

}
