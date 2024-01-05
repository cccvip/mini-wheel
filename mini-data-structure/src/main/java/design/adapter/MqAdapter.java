package design.adapter;

import com.alibaba.fastjson2.JSON;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MqAdapter {

    //解析Json,並且通過反射賦值
    public static MqInfo filter(String json, Map<String,String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String,String> jsonMap = JSON.parseObject(json,Map.class);
        MqInfo mqInfo = new MqInfo();
        for (String key : link.keySet()) {
            Object val = jsonMap.get(link.get(key));
            MqInfo.class
                    .getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class)
                    .invoke(mqInfo, val.toString());
        }
        return mqInfo;
    }

    public static void main(String[] args) {
        MqSource create_account = new MqSource();
        create_account.setNumber("100001");
        create_account.setAddress("河北省.廊坊市.广阳区.大学里职业技术学院");
        create_account.setDesc("在校开户");

        HashMap<String, String> link01 = new HashMap<String, String>();
        link01.put("userId", "number");
        link01.put("bizId", "number");
        link01.put("desc", "desc");
        MqInfo rebateInfo01 = null;
        try {
            rebateInfo01 = MqAdapter.filter(JSON.toJSONString(create_account), link01);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("mq.create_account(适配后)" + JSON.toJSONString(rebateInfo01));
    }
}
