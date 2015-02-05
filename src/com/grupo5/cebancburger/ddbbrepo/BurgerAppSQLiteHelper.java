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
	BurgerSizeTable burgerSizeTable = new BurgerSizeTable();
	BurgerMeatTable burgerMeatTable = new BurgerMeatTable();
	DrinkTypeTable drinkTypeTable = new DrinkTypeTable();
	OrderTable orderTable = new OrderTable();
	OrderLineBurgerTable orderLineBurgerTable = new OrderLineBurgerTable();
	OrderLineDrinkTable orderLineDrinkTable = new OrderLineDrinkTable();

	public BurgerAppSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se crean las tablas
		db.execSQL(userTable.getSqlCreate());
		db.execSQL(customerTable.getSqlCreate());
		db.execSQL(burgerTypeTable.getSqlCreate());
		db.execSQL(burgerSizeTable.getSqlCreate());
		db.execSQL(burgerMeatTable.getSqlCreate());
		db.execSQL(drinkTypeTable.getSqlCreate());
		db.execSQL(orderTable.getSqlCreate());
		db.execSQL(orderLineBurgerTable.getSqlCreate());
		db.execSQL(orderLineDrinkTable.getSqlCreate());
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Se elimina la versión anterior de la tabla
		db.execSQL(userTable.getSqlUpdate());
		db.execSQL(customerTable.getSqlUpdate());
		db.execSQL(burgerTypeTable.getSqlUpdate());
		db.execSQL(burgerSizeTable.getSqlUpdate());
		db.execSQL(burgerMeatTable.getSqlUpdate());
		db.execSQL(drinkTypeTable.getSqlUpdate());
		db.execSQL(orderTable.getSqlUpdate());
		db.execSQL(orderLineBurgerTable.getSqlUpdate());
		db.execSQL(orderLineDrinkTable.getSqlUpdate());

		// Se crea la nueva versión de la tabla
		db.execSQL(userTable.getSqlCreate());
		db.execSQL(customerTable.getSqlCreate());
		db.execSQL(burgerTypeTable.getSqlCreate());
		db.execSQL(burgerSizeTable.getSqlCreate());
		db.execSQL(burgerMeatTable.getSqlCreate());
		db.execSQL(drinkTypeTable.getSqlCreate());
		db.execSQL(orderTable.getSqlCreate());
		db.execSQL(orderLineBurgerTable.getSqlCreate());
		db.execSQL(orderLineDrinkTable.getSqlCreate());
	}

}
