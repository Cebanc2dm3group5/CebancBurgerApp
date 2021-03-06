package com.grupo5.cebancburger.ddbbrepo;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerMeatTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerSizeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerTypeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.CustomerTable;
import com.grupo5.cebancburger.ddbbrepo.tables.DrinkTypeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DDBBSQLite {
	public static boolean initDDBB(String dbName, Activity activity) {
		boolean success = false;
		// Abrimos la base de datos 'BurgerAppDDBB' en modo escritura
		SQLiteDatabase db = getDDBB(dbName, activity);

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
			success = true;
			Toast.makeText(activity.getApplicationContext(), "DDBB Created",
					Toast.LENGTH_SHORT).show();
			// Cerramos la base de datos
//			db.close();
		}

		return success;
	}

	public static SQLiteDatabase getDDBB(String dbName, Activity activity) {
		// Abrimos la base de datos 'BurgerAppDDBB' en modo escritura
		BurgerAppSQLiteHelper dbHelper = new BurgerAppSQLiteHelper(activity,
				dbName, null, Options.getDDBBVersion());
		return dbHelper.getWritableDatabase();

	}
	


	public static void initData(Activity activity, SQLiteDatabase db) {
		UserTable ut = new UserTable();
		ut.initData(activity, db);

		DrinkTypeTable dtt = new DrinkTypeTable();
		dtt.initData(activity, db);

		BurgerTypeTable btt = new BurgerTypeTable();
		btt.initData(activity, db);

		BurgerMeatTable bmt = new BurgerMeatTable();
		bmt.initData(activity, db);

		BurgerSizeTable bst = new BurgerSizeTable();
		bst.initData(activity, db);

		CustomerTable ct = new CustomerTable();
		ct.initData(activity, db);
//		db.close();
	}

}
