package com.young.view.happycoin.dao;

import com.young.view.happycoin.error.MyException;
import com.young.view.happycoin.modle.Entity;

import java.util.List;
import java.util.Map;

public abstract class AbstractDao {

	protected  void setTableName(String tableName){};

	protected  void setPrimaryKey(String primaryKey){};

	protected  void setSelectStatement(String selectStatement){};

	public abstract  boolean insertData(List<String> tableNames , List<List<Map<String , String>>> datas) throws MyException;

	public  abstract boolean insertData(String tableName ,List<Map<String ,String>> data) throws MyException;

	public  abstract boolean updateData(String tableName , Map<String, String> data , String whereCase , String[] whereArgs) throws MyException;

	public  abstract  boolean deleteData(String tableName , String whereCase , String[] whereArgs) throws MyException;

	public abstract  List<Entity>  selectData(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit , Entity entity) throws MyException;

	public abstract  List<Entity> select(String selectStatement ,String[] selectionArgs , Entity entity);

	public abstract  int  selectCount(String selectStatement);
}
