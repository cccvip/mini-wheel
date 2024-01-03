package design.absfactory.egm;

import design.absfactory.IIR.IR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Egm {

    private Map<String,String> egmMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(Egm.class);

    public void set(String key,String value){
        egmMap.put(key,value);
    }

    public String get(String key){
        return egmMap.get(key);
    }

}
