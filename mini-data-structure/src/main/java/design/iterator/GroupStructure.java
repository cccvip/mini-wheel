/*
 * Copyright @2024 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package design.iterator;


import design.iterator.core.Collection;
import design.iterator.core.Iterator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GroupStructure.
 *
 * @author Carl, 2024-01-10 13:41
 * @version CrisisGo v1.0
 */
public class GroupStructure implements Collection<Employee, Link> {

    // 组织ID，也是一个组织链的头部ID
    private String groupId;

    // 组织名称
    private String groupName;

    // 雇员列表
    private Map<String, Employee> employeeMap = new ConcurrentHashMap<>();

    // 组织架构关系；id->list
    private Map<String, List<Link>> linkMap = new ConcurrentHashMap<>();

    // 反向关系链
    private Map<String, String> invertedMap = new ConcurrentHashMap<>();


    public GroupStructure(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Override
    public boolean add(Employee employee) {
        employeeMap.put(employee.getuId(), employee);
        return true;
    }

    @Override
    public boolean remove(Employee employee) {
        employeeMap.remove(employee.getuId());
        return true;
    }

    @Override
    public boolean addLink(String key, Link link) {
        invertedMap.put(link.getTo(), link.getFrom());
        if (linkMap.containsKey(key)) {
            return linkMap.get(key).add(link);
        } else {
            List<Link> links = new LinkedList<>();
            links.add(link);
            linkMap.put(key, links);
            return true;
        }
    }

    @Override
    public boolean removeLink(String key) {
        linkMap.remove(key);
        return true;
    }

    @Override
    public Iterator<Employee> iterator() {

        return new Iterator<Employee>() {

            HashMap<String, Integer> keyMap = new HashMap<>();

            int totalIdx = 0;
            private String fromId = groupId;  // 雇员ID，From
            private String toId = groupId;   // 雇员ID，To

            @Override
            public boolean hasNext() {
                return totalIdx < employeeMap.size();
            }

            @Override
            public Employee next() {
                List<Link> links = linkMap.get(toId);
                int cursorIdx = getCursorIdx(toId);

                // 同级节点扫描
                if (null == links) {
                    cursorIdx = getCursorIdx(fromId);
                    links = linkMap.get(fromId);
                }

                // 上级节点扫描
                while (cursorIdx > links.size() - 1) {
                    fromId = invertedMap.get(fromId);
                    cursorIdx = getCursorIdx(fromId);
                    links = linkMap.get(fromId);
                }

                // 获取节点
                Link link = links.get(cursorIdx);
                toId = link.getTo();
                fromId = link.getFrom();
                totalIdx++;

                // 返回结果
                return employeeMap.get(link.getTo());
            }

            // 给每个层级定义宽度遍历进度
            public int getCursorIdx(String key) {
                int idx = 0;
                if (keyMap.containsKey(key)) {
                    idx = keyMap.get(key);
                    keyMap.put(key, ++idx);
                } else {
                    keyMap.put(key, idx);
                }
                return idx;
            }
        };
    }
}
