package com.young.view.happycoin;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**统一处理Activity生命周期
 * Created by Administrator on 2016/4/27.
 */
public class AppActvityLifecycleCallback implements Application.ActivityLifecycleCallbacks{

    private int visibleActivityCount = 0 ;/*可见Activity的个数*/

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d("Activity is destroyed:" , activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d("Activity is started:" , activity.getClass().getSimpleName());

    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d("Activity is stop:" , activity.getClass().getSimpleName());
    }
}
