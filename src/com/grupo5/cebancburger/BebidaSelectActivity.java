package com.grupo5.cebancburger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BebidaSelectActivity extends Activity {

	Button btnNext, btnExit;

	// Pedido pedido;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bebida_select_layout);
		// recogemos datos del intent
		Intent intent = getIntent();
		// pedido = intent.getSerializableExtra("pedido");
		
		btnExit = (Button) findViewById(R.id.btnExit3);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				setResult(RESULT_CANCELED, returnIntent);
				finish();
			}
		});

		btnNext = (Button) findViewById(R.id.btnNext3);
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						OrderRevisionActivity.class);
				// TODO - send pedido object
				startActivityForResult(intent, 2);
			}
		});
	}
}