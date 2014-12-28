package com.grupo5.cebancburger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class BebidaSelectActivity extends Activity {

	Button btnNext, btnExit;
	private String tipo_bebida = "";
	ArrayAdapter<CharSequence> adaptadorTipoBebida;

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
		
		adaptadorTipoBebida = ArrayAdapter.createFromResource(this, R.array.tipo_bebida, android.R.layout.simple_spinner_item);
		Spinner spnTipoBebida = (Spinner) findViewById(R.id.spnBebidaType); 

		adaptadorTipoBebida.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		spnTipoBebida.setAdapter(adaptadorTipoBebida); 

		spnTipoBebida.setOnItemSelectedListener( 
				new AdapterView.OnItemSelectedListener() { 
					public void onItemSelected(AdapterView<?> parent, 
							android.view.View v, int position, long id) { 
						tipo_bebida = adaptadorTipoBebida.getItem(position).toString(); 
					} 
					public void onNothingSelected(AdapterView<?> parent) { 
						tipo_bebida = ""; 
					} 
				});
	}
}