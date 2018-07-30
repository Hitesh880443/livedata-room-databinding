package com.hitesh.livedata.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String formatDate(Date mDate) {
        SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
        return spf.format(mDate);
    }
}
