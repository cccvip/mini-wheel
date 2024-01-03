package design.absfactory.service.impl;

import design.absfactory.IIR.IR;
import design.absfactory.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CacheServiceImpl implements CacheService {

    private Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    private Map<String,String> dataMap = new HashMap<>();

    @Override
    public String get(String key) {
        logger.info("pure get {}",key);
        return dataMap.get(key);
    }

    @Override
    public void set(String key, String value) {
        logger.info("pure set {}",key);
         dataMap.put(key,value);
    }
}
