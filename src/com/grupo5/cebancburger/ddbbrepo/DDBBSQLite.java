package com.grupo5.cebancburger.ddbbrepo;

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
			initData(activity);
			Toast.makeText(activity.getApplicationContext(), "DDBB Created",
					Toast.LENGTH_SHORT).show();
			// Cerramos la base de datos
			db.close();
		}

		return success;
	}

	public static SQLiteDatabase getDDBB(String dbName, Activity activity) {
		// Abrimos la base de datos 'BurgerAppDDBB' en modo escritura
		BurgerAppSQLiteHelper dbHelper = new BurgerAppSQLiteHelper(activity,
				dbName, null, 13);
		return dbHelper.getWritableDatabase();

	}
	
	public static void initData(Activity activity){
		UserTable ut = new UserTable();
		ut.initData(activity);
	}

}
