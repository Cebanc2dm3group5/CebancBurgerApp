package com.grupo5.cebancburger.ddbbrepo;

import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BurgerAppSQLiteHelper extends SQLiteOpenHelper {

	UserTable userTable = new UserTable();

	public BurgerAppSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(userTable.getSqlCreate());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Se elimina la versión anterior de la tabla
		db.execSQL(userTable.getSqlUpdate());

		// Se crea la nueva versión de la tabla
		db.execSQL(userTable.getSqlCreate());
	}

}
