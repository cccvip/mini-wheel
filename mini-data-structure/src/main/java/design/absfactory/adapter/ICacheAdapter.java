package design.absfactory.adapter;

public interface ICacheAdapter {
    String get(String key);
    void set(String key,String value);
}
