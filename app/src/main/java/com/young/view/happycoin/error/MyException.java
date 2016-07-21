package com.young.view.happycoin.error;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Toast;

import com.young.view.happycoin.R;
import com.young.view.happycoin.HappyCoinApplication;

/**
 * Created by Administrator on 2016/4/21.
 */
public class MyException extends Exception {
    private int errorCode;
    private String errorMessage;
    private Activity activity = null ;

    public MyException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        errorMessage = detailMessage;
    }

    public MyException(String detailMessage){
        super(detailMessage);
        errorMessage = detailMessage;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String getMessage() {

        return errorMessage;
    }


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        String result;

        if (!TextUtils.isEmpty(errorMessage)) {
            result = errorMessage;
        } else {

            String name = "code" + errorCode;
            int i = HappyCoinApplication.getInstance().getResources()
                    .getIdentifier(name, "string", HappyCoinApplication.getInstance().getPackageName());

            try {
                result = HappyCoinApplication.getInstance().getString(i);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
                result = HappyCoinApplication.getInstance().getString(R.string.unknown_error_error_code);
            }
        }


        return result;

    }

    @Override
    public void printStackTrace() {
        errorMessage = getErrorMsg();
        HappyCoinApplication.handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HappyCoinApplication.getInstance(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        super.printStackTrace();
    }

}
