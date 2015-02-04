package com.grupo5.cebancburger.ddbbrepo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.grupo5.cebancburger.ddbbrepo.tables.*;

public class BurgerAppSQLiteHelper extends SQLiteOpenHelper {

	UserTable userTable = new UserTable();
	//CustomerTable customerTable = new CustomerTable();
	BurgerTypeTable burgerTypeTable = new BurgerTypeTable();
	BurgerSizeTable burgerSizeTable = new BurgerSizeTable();
	BurgerMeatTable burgerMeatTable = new BurgerMeatTable();
	DrinkTypeTable drinkTypeTable = new DrinkTypeTable();

	public BurgerAppSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(userTable.getSqlCreate());
		//db.execSQL(customerTable.getSqlCreate());
		db.execSQL(burgerTypeTable.getSqlCreate());
		db.execSQL(burgerSizeTable.getSqlCreate());
		db.execSQL(burgerMeatTable.getSqlCreate());
		db.execSQL(drinkTypeTable.getSqlCreate());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Se elimina la versi�n anterior de la tabla
		db.execSQL(userTable.getSqlUpdate());
		//db.execSQL(customerTable.getSqlUpdate());
		db.execSQL(burgerTypeTable.getSqlUpdate());
		db.execSQL(burgerSizeTable.getSqlUpdate());
		db.execSQL(burgerMeatTable.getSqlUpdate());
		db.execSQL(drinkTypeTable.getSqlUpdate());

		// Se crea la nueva versi�n de la tabla
		db.execSQL(userTable.getSqlCreate());
		//db.execSQL(customerTable.getSqlCreate());
		db.execSQL(burgerTypeTable.getSqlCreate());
		db.execSQL(burgerSizeTable.getSqlCreate());
		db.execSQL(burgerMeatTable.getSqlCreate());
		db.execSQL(drinkTypeTable.getSqlCreate());
	}

}
