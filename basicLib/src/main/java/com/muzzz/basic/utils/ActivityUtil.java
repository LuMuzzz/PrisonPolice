package com.muzzz.basic.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityUtil {

    private static Stack<Activity> sStack;
    private static ActivityUtil sManager;

    /**
     * 获取实例
     */
    public static synchronized ActivityUtil getInstance() {
        if (sManager == null) {
            sManager = new ActivityUtil();
            sStack = new Stack<>();
        }
        return sManager;
    }

    /**
     * 添加Activity
     */
    public synchronized void addActivity(Activity activity) {
        sStack.add(activity);
    }

    /**
     * 移除Activity
     */
    public synchronized void removeActivity(Activity activity) {
        sStack.remove(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : sStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            sStack.remove(activity);
        }
    }

    /**
     * 是否存在Activity
     */
    public boolean containsActivity(Class<?> cls) {
        for (Activity activity : sStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : sStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        sStack.clear();
    }
}
