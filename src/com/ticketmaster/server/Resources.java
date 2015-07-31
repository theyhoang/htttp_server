package com.ticketmaster.server;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yen.hoang on 7/7/15.
 */

public class Resources {

    // TODO: synchronization
    public static HashMap<String, String> data = new HashMap<String, String>();
    public static List<String> logs = new ArrayList<>();

    public static boolean addData(String dataToAddString) {
        String[] dataToAdd = dataToAddString.split("=");

        if (dataToAdd.length >= 2) {
            String key = dataToAdd[0];
            String value = dataToAdd[1];
            data.put(key, value);
            return true;
        } else {
            return false;
        }
    }

    public static void clearData() {
        data.clear();
    }

    public static boolean removeData(String dataToRemoveString) {
        String[] dataToRemove = dataToRemoveString.split("=");
        if (dataToRemove.length >= 2) {
            String key = dataToRemove[0];
            String value = dataToRemove[1];
            if (data.containsKey(key)) {
                data.remove(key);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String getData() {
        String dataString = "";
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!dataString.isEmpty()) {
                dataString = dataString.concat("&");
            }
            dataString = dataString.concat(entry.getKey() + "=" + entry.getValue());
        }
        return dataString;
    }

    public static void addLog(String log) {
        logs.add(log);
    }

    public static List<String> getLogs() {
        return logs;
    }
}
