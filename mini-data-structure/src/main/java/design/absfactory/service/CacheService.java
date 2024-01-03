package design.absfactory.service;

public interface CacheService {

    String get(final String key);

    void set(String key, String value);

}
