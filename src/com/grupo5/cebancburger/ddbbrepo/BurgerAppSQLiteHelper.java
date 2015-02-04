package com.grupo5.cebancburger.ddbbrepo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.grupo5.cebancburger.ddbbrepo.tables.*;

public class BurgerAppSQLiteHelper extends SQLiteOpenHelper {

	UserTable userTable = new UserTable();
	CustomerTable customerTable = new CustomerTable();
	BurgerTypeTable burgerTypeTable = new BurgerTypeTable();

	public BurgerAppSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(userTable.getSqlCreate());
		db.execSQL(customerTable.getSqlCreate());
		db.execSQL(burgerTypeTable.getSqlCreate());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Se elimina la versión anterior de la tabla
		db.execSQL(userTable.getSqlUpdate());
		db.execSQL(customerTable.getSqlUpdate());
		db.execSQL(burgerTypeTable.getSqlUpdate());

		// Se crea la nueva versión de la tabla
		db.execSQL(userTable.getSqlCreate());
		db.execSQL(customerTable.getSqlCreate());
		db.execSQL(burgerTypeTable.getSqlCreate());
	}

}
