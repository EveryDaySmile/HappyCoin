package com.young.view.happycoin.dao;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.young.view.happycoin.HappyCoinApplication;
import com.young.view.happycoin.R;
import com.young.view.happycoin.error.MyException;
import com.young.view.happycoin.modle.Entity;

public class DataUtilDao extends AbstractDao {
    private String mTableName = "";
    private static DBUtil mDbutil = null;
    public SQLiteDatabase rd = null;
    public SQLiteDatabase wt = null;
    public static DataUtilDao singleton = null;

    @Override
    protected void setTableName(String tableName) {
        super.setTableName(tableName);
        mTableName = tableName;
    }

    public static DataUtilDao getInstance() {

        if (singleton == null) {

            singleton = new DataUtilDao();

            mDbutil = new DBUtil(HappyCoinApplication.getInstance() , HappyCoinApplication.DB_NAME , HappyCoinApplication.mVersion);

            singleton.rd = mDbutil.getReadableDatabase();
            singleton.wt = mDbutil.getWritableDatabase();
        }
        return singleton;
    }


    public static DataUtilDao getInstance(Context context) {


        singleton = new DataUtilDao();

        mDbutil = new DBUtil(context, HappyCoinApplication.DB_NAME, HappyCoinApplication.mVersion);

        singleton.rd = mDbutil.getReadableDatabase();
        singleton.wt = mDbutil.getWritableDatabase();
        return singleton;
    }

    /**
     * 单表插入
     *
     * @param data
     * @return
     * @throws
     */
    @Override
    public boolean insertData(String tableName , List<Map<String, String>> data) throws MyException {
        boolean flag = true;
        ContentValues c = null;
        try {
            wt.beginTransaction();
            for (Map m : data) {
                Iterator keys = m.keySet().iterator();
                c = new ContentValues();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    String value = (String) m.get(key);
                    c.put(key, value);
                }
                wt.insert(tableName, null, c);
            }
            wt.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            throw new MyException(HappyCoinApplication.getInstance().getString(R.string.db_insert_data_error), e);
        } finally {

            if (wt != null) {
                wt.endTransaction();
                wt.close();
                wt = null ;
            }

            if(rd !=  null){
                rd.close();
                rd = null ;
            }


            if(mDbutil != null){
                mDbutil.close();
                mDbutil = null ;
            }


            singleton = null;
        }

        return flag;
    }

    /**
     * 多表插入
     *
     * @param tableNames
     * @param datas
     * @return
     */
    @Override
    public boolean insertData(List<String> tableNames, List<List<Map<String, String>>> datas) throws MyException {
        boolean flag = true;
        ContentValues c = null;
        try {
            wt.beginTransaction();
            for (int i = 0; i < tableNames.size(); i++) {
                String tableName = tableNames.get(i);
                List<Map<String, String>> data = datas.get(i);
                for (Map m : data) {
                    Iterator keys = m.keySet().iterator();
                    c = new ContentValues();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        String value = (String) m.get(key);
                        c.put(key, value);
                    }
                    wt.insert(tableName, null, c);
                }
            }
            wt.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            throw new MyException(HappyCoinApplication.getInstance().getString(R.string.db_insert_data_error), e);
        } finally {

            if (wt != null) {
                wt.endTransaction();
                wt.close();
                wt = null ;
            }

            if(rd !=  null){
                rd.close();
                rd = null ;
            }


            if(mDbutil != null){
                mDbutil.close();
                mDbutil = null ;
            }


            singleton = null;
        }

        return flag;
    }

    @Override
    public boolean updateData(String tableName , Map<String, String> data , String whereCase , String[] whereArgs) throws MyException {
        ContentValues c = null ;
        boolean flag = true ;
        try{
            Iterator keys = data.keySet().iterator();
            c = new ContentValues();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = data.get(key);
                c.put(key, value);
            }
            wt.update(tableName , c, whereCase , whereArgs);
        }catch(Exception e){
            e.printStackTrace();
            flag = false ;
            throw  new MyException(HappyCoinApplication.getInstance().getString(R.string.db_update_data_error),e);

        }finally {
            if (wt != null) {
                wt.endTransaction();
                wt.close();
                wt = null ;
            }

            if(rd !=  null){
                rd.close();
                rd = null ;
            }


            if(mDbutil != null){
                mDbutil.close();
                mDbutil = null ;
            }


            singleton = null;
        }
        return false;
    }

    @Override
    public boolean deleteData(String tableName , String whereCase , String[] whereArgs) throws MyException {

        boolean flag = true ;
        try{
            wt.delete(tableName , whereCase , whereArgs) ;

        }catch (Exception e){
            flag = false ;
            throw  new MyException(HappyCoinApplication.getInstance().getString(R.string.db_delete_data_error) , e);

        }finally {

            if (wt != null) {
                wt.endTransaction();
                wt.close();
                wt = null ;
            }

            if(rd !=  null){
                rd.close();
                rd = null ;
            }


            if(mDbutil != null){
                mDbutil.close();
                mDbutil = null ;
            }


            singleton = null;
        }
        return false;
    }

    @Override
    public List<Entity> selectData(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit,Entity entity) throws MyException {
        List<Entity> data = null ;
        Cursor cursor = null ;
        try {
            cursor= rd.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            data = getEntity(cursor, entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(HappyCoinApplication.getInstance().getString(R.string.db_select_data_error),e);
        } finally {
            if (wt != null) {
                wt.endTransaction();
                wt.close();
                wt = null ;
            }

            if(rd !=  null){
                rd.close();
                rd = null ;
            }


            if(mDbutil != null){
                mDbutil.close();
                mDbutil = null ;
            }


            singleton = null;
        }

        return data;
    }

    /**
     *
     * @param selectStatement 自定义查询语句
     * @param selectionArgs 占位符
     * @param entity
     * @return
     */
    @Override
    public List<Entity> select(String selectStatement,String[] selectionArgs ,Entity entity) {
        List<Entity> data = null ;
        Cursor cursor = null ;
        try {
            cursor = rd.rawQuery(selectStatement , selectionArgs );
            data =  getEntity(cursor,entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wt != null) {
                wt.endTransaction();
                wt.close();
                wt = null ;
            }

            if(rd !=  null){
                rd.close();
                rd = null ;
            }


            if(mDbutil != null){
                mDbutil.close();
                mDbutil = null ;
            }


            singleton = null;
        }
        return data;
    }

    @Override
    public int selectCount(String selectStatement) {
        return 0;
    }

    public List<Entity> getEntity(Cursor cursor ,Entity entity){
        List<Entity> data = null ;
        Entity e = null ;
        Class c = entity.getClass();
        StringBuffer sb = null;
        Method[] methods = null ;
        try {
            methods = c.getDeclaredMethods();
            data = new ArrayList<>();
                while(cursor.moveToNext()){
                    e = entity ;
                    for(Method m : methods){
                        sb = new StringBuffer();
                        System.out.println("方法名称:" + m.getName());
                        String methodName = m.getName();
                        if(methodName.startsWith("set"))
                            sb.append(methodName.substring(3,4).toLowerCase()).append(methodName.substring(4,methodName.length()));
                        else continue;
                        System.out.println("字段名称:" + sb.toString());
                        int index = cursor.getColumnIndex(sb.toString()) ;
                        if(index != -1)
                            m.invoke(e, cursor.getString(index));
                    }
                    data.add(e);
            }
        }catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }


        return data ;
    }

}
