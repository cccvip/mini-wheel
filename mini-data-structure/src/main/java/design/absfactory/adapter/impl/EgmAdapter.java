package design.absfactory.adapter.impl;

import design.absfactory.adapter.ICacheAdapter;
import design.absfactory.egm.Egm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EgmAdapter implements ICacheAdapter {

    private Logger logger = LoggerFactory.getLogger(EgmAdapter.class);
    Egm egm = new Egm();

    @Override
    public String get(String key) {
        logger.info("egm get {}",key);
        return egm.get(key);
    }

    @Override
    public void set(String key, String value) {
        logger.info("egm set {}",key);
        egm.set(key,value);
    }
}
