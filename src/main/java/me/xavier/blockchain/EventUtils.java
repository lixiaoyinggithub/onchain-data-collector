package me.xavier.blockchain;

public class EventUtils {

    public static String parseAddressFromTopic(String topic) {
        if (topic == null || topic.length() != 66) return null;
        return "0x" + topic.substring(26);
    }

}
