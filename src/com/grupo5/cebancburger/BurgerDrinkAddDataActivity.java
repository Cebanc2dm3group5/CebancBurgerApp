package com.grupo5.cebancburger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BurgerDrinkAddDataActivity  extends Activity{
	
	private String sTitle;
	private TextView txtTitle;
	private int nId;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.burger_drink_add_data_layout);

		Intent intent = getIntent();
		sTitle = intent.getStringExtra("titulo");
		nId = intent.getIntExtra("id",-1);

		txtTitle = (TextView) findViewById(R.id.lblBurDriTitle);
		txtTitle.setText(sTitle);

	}

}
