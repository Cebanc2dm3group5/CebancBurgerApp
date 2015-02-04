package com.grupo5.cebancburger.ddbbrepo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.grupo5.cebancburger.ddbbrepo.tables.CustomerTable;
import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;

public class BurgerAppSQLiteHelper extends SQLiteOpenHelper {

	UserTable userTable = new UserTable();
	CustomerTable customerTable = new CustomerTable();

	public BurgerAppSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(userTable.getSqlCreate());
		db.execSQL(customerTable.getSqlCreate());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Se elimina la versi�n anterior de la tabla
		db.execSQL(userTable.getSqlUpdate());
		db.execSQL(customerTable.getSqlUpdate());

		// Se crea la nueva versi�n de la tabla
		db.execSQL(userTable.getSqlCreate());
		db.execSQL(customerTable.getSqlCreate());
	}

}
