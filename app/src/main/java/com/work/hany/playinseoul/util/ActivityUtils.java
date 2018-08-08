package com.work.hany.playinseoul.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import static dagger.internal.Preconditions.checkNotNull;

public class ActivityUtils {
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId, boolean addBackStack) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        String fragmentID = fragment.getClass().getName();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        if (addBackStack &&  fragmentManager.findFragmentByTag(fragmentID) == null) {
            transaction.addToBackStack(fragmentID);
        }
        transaction.commit();
    }

    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        addFragmentToActivity(fragmentManager,fragment,frameId,false);
    }
}
