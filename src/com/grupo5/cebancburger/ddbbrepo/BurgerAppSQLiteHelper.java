package com.grupo5.cebancburger.ddbbrepo;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.grupo5.cebancburger.ddbbrepo.tables.BurgerMeatTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerSizeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerTypeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.CustomerTable;
import com.grupo5.cebancburger.ddbbrepo.tables.DrinkTypeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderLineBurgerTable;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderLineDrinkTable;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderTable;
import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;

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
	Activity context;

	public BurgerAppSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = (Activity) context;
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
		
		DDBBSQLite.initData(this.context);

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
		onCreate(db);
	}

}
