package com.young.view.happycoin.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.young.view.happycoin.R;
import com.young.view.happycoin.HappyCoinApplication;
import com.young.view.happycoin.error.MyException;

public class DBUtil extends SQLiteOpenHelper {

    private Context mContext;
    private List<String> sql = null ;
    StringBuffer userSql = new StringBuffer();
    StringBuffer orderSql = new StringBuffer();

    /**
     * @param context
     */

    public DBUtil(Context context ,String DbName,int mVersion) {
        super(context, DbName, null, mVersion);

        this.mContext = context;

//        userSql.append(" create table ")
//                .append(UserTable.tableName).append(" (")
//                .append(UserTable.id)
//                .append(" integer")
//                .append(" primary key AUTOINCREMENT ,")
//                .append(UserTable.userName)
//                .append(" text , ")
//                .append(UserTable.passWord)
//                .append(" text )");
//
//        orderSql.append(" create table ")
//                .append(OrderTable.table_name).append(" ( ")
//                .append(OrderTable.app_no)
//                .append(" integer")
//                .append(" primary key AUTOINCREMENT , ")
//                .append(OrderTable.app_type)
//                .append(" text , ")
//                .append(OrderTable.app_create_time)
//                .append(" text , ")
//                .append(OrderTable.app_emp_no)
//                .append(" text )");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            sql = new ArrayList<String>();
            sql.add(userSql.toString());
            sql.add(orderSql.toString());
            createTable(sql, db);
        } catch (MyException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

//    private void copyDatabase() {
//        try {
//            InputStream is = mContext.getAssets().open(DB_NAME);
//            if (is != null) {
//                File f = mContext.getDatabasePath(DB_NAME);
//                String path = f.getAbsolutePath();
//                f.delete();
//                File newFile = new File(path);
//                newFile.createNewFile();
//                FileOutputStream os = new FileOutputStream(newFile);
//                byte[] buffer = new byte[1024];
//                while (is.read(buffer) != -1) {
//                    os.write(buffer);
//                }
//
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        /*跟新数据库文件*/


    }



//    public static boolean saveDatabase(Activity activity) {
//        boolean isCopy = false;
//
//        File database = activity.getApplication().getDatabasePath(DB_NAME);
//        File bakDB = new File(SgccApplication.PATH_RES + "/" + DB_NAME);// .replace("eMenuStd.db","eMenuStd.bak"));
//        try {
//            if (bakDB.exists())
//                bakDB.delete();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        long result = FileUtil.copyFile(database, bakDB);
//        isCopy = (result == -1 ? false : true);
//        SgccApplication.loger.info("isCopy:" + isCopy);
//        return isCopy;
//    }

    public void createTable(List<String> sql, SQLiteDatabase db) throws MyException{
        try {
            db.beginTransaction();

            for(String str : sql){
                db.execSQL(str);
            }

            db.setTransactionSuccessful();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException(HappyCoinApplication.getInstance().getString(R.string.create_sqlite_file_error),e);
        } finally {
//            if (db != null)
//                db.close();
            db.endTransaction();
        }
    }

}