package com.mesakh.firststartspringboot.models.response;

import java.util.ArrayList;
import java.util.List;

public class KeyValueItem {
    private int id;
    private String key;
    private String value;

    public KeyValueItem() {
    }

    public KeyValueItem(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static List<KeyValueItem> getAllStatus(){
        List<KeyValueItem> keyValueItemList = new ArrayList<>();
        keyValueItemList.add(new KeyValueItem(1,"ACT","Active"));
        keyValueItemList.add(new KeyValueItem(2,"DEL","Delete"));
        keyValueItemList.add(new KeyValueItem(3,"DRAFT","Draft"));
        return keyValueItemList;
    }
}
