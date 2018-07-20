package com.work.hany.playinseoul.util;

import android.content.res.Resources;

public class DisplayUtils {

    public static float dpToPx(float dp) {
     return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    public static float pxToDp(float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }
}
