package design.absfactory.IIR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class IR {

    private Logger logger = LoggerFactory.getLogger(IR.class);

    private Map<String,String> IRMap = new HashMap<>();

    public void set(String key,String value){
        IRMap.put(key,value);
    }

    public String get(String key){
        return IRMap.get(key);
    }
}
