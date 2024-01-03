package design.absfactory.adapter.impl;

import design.absfactory.IIR.IR;
import design.absfactory.adapter.ICacheAdapter;
import design.absfactory.egm.Egm;

public class IRAdapter implements ICacheAdapter {

    IR ir = new IR();

    @Override
    public String get(String key) {
        return ir.get(key);
    }

    @Override
    public void set(String key, String value) {
        ir.set(key,value);
    }
}
