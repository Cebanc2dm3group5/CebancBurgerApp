package com.grupo5.cebancburger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BurgerSelectActivity extends Activity {
	Button btnNext, btnExit;
	//Pedido pedido;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.burgers_select_layout);
		
		//recogemos datos del intent
		Intent intent = getIntent();
//		pedido = intent.getSerializableExtra("pedido");	
		
		btnExit = (Button) findViewById(R.id.btnExit2);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				setResult(RESULT_CANCELED, returnIntent);
				finish();
			}
		});
		
		btnNext = (Button) findViewById(R.id.btnNext2);
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// on first activity
				Intent intent = new Intent(getApplicationContext(), BebidaSelectActivity.class);
				// TODO - send pedido object 
				startActivityForResult(intent, 2);
			}
		});
	}
}
