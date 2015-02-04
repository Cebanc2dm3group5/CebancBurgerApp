package com.grupo5.cebancburger.ddbbrepo;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DDBBSQLite {
	public static boolean initDDBB(String dbName, Activity activ) {
		boolean success = false;
		// Abrimos la base de datos 'DBUsuarios' en modo escritura
		BurgerAppSQLiteHelper dbHelper = new BurgerAppSQLiteHelper(activ,
				"DBUsuarios", null, 1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
			success = true;
			Toast.makeText(activ.getApplicationContext(), "DDBB Created",
					Toast.LENGTH_SHORT).show();
			// Cerramos la base de datos
			db.close();
		}

		return success;
	}

}
