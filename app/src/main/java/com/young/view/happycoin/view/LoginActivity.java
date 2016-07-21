package com.young.view.happycoin.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.view.happycoin.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backBtn  = null ;
    private TextView btn_forget_pwd = null ;/*忘记密码*/
    private Button  btn_rigister = null ;/*注册用户*/
    private EditText edit_et_pwd = null ;/*密码*/
    private TextView tv_pin = null ;/*验证码*/
    private Button btn_back_login = null ;/*返回登录*/
    private Button btn_login = null ;/*登录*/
    private Timer timer = null ;/*计时器*/
    private Handler handler = null ;
    private int index = 60 ;/*计时器*/
    private EditText et_account = null ;/*用户名*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        setListener();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 0:
                        index-- ;
                        if(index <1){
                            index = 60 ;
                            tv_pin.setText("获取验证码");
                            timer.cancel();
                            tv_pin.setEnabled(true);
                            timer = null ;
                        }else
                            tv_pin.setText(index+"s");

                        break;
                }
            }
        };
    }

    public void findView(){
        backBtn = (ImageView)findViewById(R.id.iv_back);
        btn_forget_pwd = (TextView) findViewById(R.id.btn_forget_pwd);
        btn_rigister = (Button)findViewById(R.id.btn_rigister);
        edit_et_pwd = (EditText)findViewById(R.id.et_pwd);
        tv_pin = (TextView)findViewById(R.id.tv_pin);
        btn_back_login = (Button)findViewById(R.id.btn_back_login);
        btn_login = (Button)findViewById(R.id.btn_login);
        et_account = (EditText)findViewById(R.id.et_account);
    }

    public void setListener(){
        backBtn.setOnClickListener(this);
        btn_forget_pwd.setOnClickListener(this);
        btn_rigister.setOnClickListener(this);
        btn_back_login.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_pin.setOnClickListener(this);
        et_account.addTextChangedListener(watch);
        edit_et_pwd.addTextChangedListener(watch);
    }

    public void initData(){
        edit_et_pwd.setHint("请输入密码");
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_forget_pwd:/*忘记密码*/
                break;
            case R.id.btn_rigister:/*注册用户*/
                rigister();
                break;
            case R.id.btn_back_login:/*返回登录*/
                back_login();
                break;
            case R.id.btn_login:/*登录*/
                break;
            case R.id.tv_pin:/*获取验证码*/
                tv_pin.setText(index+"s");
                startTimer();
                tv_pin.setEnabled(false);
                break;
        }
    }

    /*注册用户信息*/
    public void rigister() {
        et_account.setText("");
        edit_et_pwd.setText("");
        edit_et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        edit_et_pwd.setHint("请输入验证码");
        tv_pin.setVisibility(View.VISIBLE);
        btn_forget_pwd.setVisibility(View.GONE);
        btn_back_login.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
    }

    /*用户登录*/
    public void back_login() {
        et_account.setText("");
        edit_et_pwd.setText("");
        edit_et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edit_et_pwd.setHint("请输入密码");
        tv_pin.setVisibility(View.GONE);
        btn_forget_pwd.setVisibility(View.VISIBLE);
        btn_back_login.setVisibility(View.GONE);
        btn_login.setVisibility(View.VISIBLE);
        if(timer != null){
            timer.cancel();
            timer = null ;
        }

        tv_pin.setText("获取验证码");
        index = 60 ;

    }

    TextWatcher watch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {



        }

        @Override
        public void afterTextChanged(Editable s) {

            if(!TextUtils.isEmpty(et_account.getText().toString()))
                tv_pin.setEnabled(true);
            else
                tv_pin.setEnabled(false);

            if(!TextUtils.isEmpty(et_account.getText().toString()) && !TextUtils.isEmpty(edit_et_pwd.getText().toString())){
                btn_login.setEnabled(true);
            }
            else{
                btn_login.setEnabled(false);
            }

        }
    };
    public void startTimer(){
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Message message = handler.obtainMessage();
                message.what = 0 ;
                handler.sendMessage(message);
            }
        } , 1000 ,1000);
    }


}
