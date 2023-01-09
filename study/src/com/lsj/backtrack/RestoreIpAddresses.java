package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIpAddresses {

    List<String> res = new ArrayList<>();
    LinkedList<String> cur = new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
        helper(0, 0, s);
        return res;
    }

    private void helper(int start, int level, String s) {
        // 全部找完了
        if (level == 4) {
            res.add(String.join(".", cur));
            return;
        }

        for (int i = start + 1; i < s.length(); i++) {
            if (s.length() - i  - 1> (3 - level) * 3) {
                continue;
            }
            if (isValid(s.substring(start, i))) {
                cur.add(s.substring(start, i));
                helper(i, level + 1, s);
                cur.removeLast();
            }
        }
    }

    private boolean isValid(String s) {
        if (s.length() > 3) {
            return false;
        }
        if (s.charAt(0) == '0' && s.length() > 1) {
            return false;
        }
        if (Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }

}
