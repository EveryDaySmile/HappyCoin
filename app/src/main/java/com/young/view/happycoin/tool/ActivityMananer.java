package com.young.view.happycoin.tool;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;

public class ActivityMananer {
	private List<Activity> mList = new LinkedList<Activity>();
	public static ActivityMananer activityMananer;

	public static ActivityMananer getInstance() {
		if (activityMananer == null) {
			activityMananer = new ActivityMananer();
		}
		return activityMananer;
	}

	/**
	 *
	 * 
	 * @param activity
	 * @param class1
	 */
	public void openActivity(Activity activity, Class<?> class1) {
		Intent intent = new Intent();
		intent.setClass(activity, class1);
		activity.startActivity(intent);
		activity.finish();
	}
 
	/**
	 *
	 * 
	 * @param activity
	 * @param class1
	 */
	public void openActivity2(Activity activity, Class<?> class1) {
		Intent intent = new Intent();
		intent.setClass(activity, class1);
		activity.startActivity(intent);
	}
	public void addActivity(Activity activity) {
		mList.add(activity);
	}
	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
			// Process.killProcess(Process.myPid());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
