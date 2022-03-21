package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        int countDel = 0;
        int countChange = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User user : current) {
            map.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            if (map.containsKey(user.getId())) {
                if (!user.getName().equals(map.get(user.getId()))) {
                    countChange++;
                    info.setChanged(countChange);
                }
            } else {
                countDel++;
                info.setDeleted(countDel);
            }
        }
        info.setAdded(current.size() - previous.size() + countDel);
        return info;
    }
}
