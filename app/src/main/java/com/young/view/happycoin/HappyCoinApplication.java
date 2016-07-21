package com.young.view.happycoin;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.young.view.happycoin.dao.DBUtil;
import com.young.view.happycoin.tool.CrashHandler;
import com.young.view.happycoin.tool.DateUtil;
import com.young.view.happycoin.tool.FilePaths;
import com.young.view.happycoin.tool.FileUtil;
import com.young.view.happycoin.tool.PdaHelper;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * 程序启动
 *
 * @author admin
 */
public class HappyCoinApplication extends Application {
    public static Logger loger;
    public static HappyCoinApplication instance;
    private String MODEL_PATH ;
    /** 资源文件目录 */
    public static String PATH_RES;
    public static DBUtil dbUtil = null ;
    public SQLiteDatabase db = null ;

    public  static String DB_NAME = "sgcc_mobile.db";/*数据库名称*/
    public static int mVersion = 2;/*数据版本*/

    public static Handler handler = null ;

    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        PATH_RES = FileUtil.getDir();
        FilePaths.createFile();
        createLog();
        loger.info("[取派版本" + PdaHelper.getVersion(HappyCoinApplication.this) + "启动]");
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        registerActivityLifecycleCallbacks(new AppActvityLifecycleCallback());/*注册Activity生命周期回调接口，统一管理Activity生命周期*/
        createDB();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        };



    }

    /**
     * 复制模板
     */
    private void createModel() {
        String modelFileName = MODEL_PATH + "JobDataModel.xls";
        try {
            InputStream is = this.getAssets().open("JobDataModel.xls");
            if (is != null) {
                FileOutputStream os = new FileOutputStream(modelFileName);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    os.write(buffer);
                }
                is.close();
                os.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            HappyCoinApplication.loger.info(e.getMessage());
        }
    }
    /**
     * /** 日志
     */
    private void createLog() {
        LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(FilePaths.LOG_PATH  + DateUtil.getFormatDateLogString("yyyy-MM-dd") + ".log");
        logConfigurator.setRootLevel(Level.DEBUG);
        logConfigurator.setLevel("org.apache", Level.ERROR);
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(1024 * 1024 * 2);
        logConfigurator.setMaxBackupSize(6);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();
        loger = Logger.getLogger(FilePaths.LOG_PATH  + DateUtil.getFormatDateLogString("yyyy-MM-dd") + ".log");//FilePaths.LOG_PATH
    }

    public synchronized static HappyCoinApplication getInstance() {
        return instance;
    }

    /**
     * 创建数据库文件
     * @throws
     */
    public void createDB(){
        try {
            dbUtil = new DBUtil(this , DB_NAME , mVersion);
            db = dbUtil.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(db != null)
                db.close();
            if(dbUtil != null )
                dbUtil.close();
        }
    }
}
