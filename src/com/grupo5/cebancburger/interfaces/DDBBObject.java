package com.grupo5.cebancburger.interfaces;

import android.app.Activity;
import android.content.ContentValues;

public interface DDBBObject {
	public void save(Activity activity);
	public void delete(Activity activity);
	public ContentValues getContentValue(Activity activity);
	public ContentValues getContentValueForEdit(Activity activity, int id);
	
}
