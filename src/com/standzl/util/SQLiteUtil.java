package com.standzl.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUtil extends SQLiteOpenHelper {
	
	private static String DBName="standzl";
	
	public SQLiteUtil(Context context){
			this(context,DBName,null,1);
	}
	
	private SQLiteUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
			String sql="create table if not exists "+Constant.TABLENAME+" (" +
					"_id int  primary key," +
					"enterPriserName varchar(100) not null," +
					"enterPriserRegNo varchar(200) not null," +
					"enterPriserPerson varchar(20) not null," +
					"enterPriserCreateDate varchar(20)," +
					"enterPriserStatus varchar(10)," +
					"enterPriserType varchar(40)," +
					"administrativeDivision varchar(40)," +
					"regeditMenoy varchar(20) not null," +
					"operatPeriodStart varchar(20)," +
					"operatPeriodEnd varchar(20)," +
					"registerBody varchar(40)," +
					"enterPriserAddr varchar(100)," +
					"operationRang varchar(300)," +
					"checkYear varchar(20)," +
					"checkResult varchar(10)," +
					"cancleDate varchar(20))";
			db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
