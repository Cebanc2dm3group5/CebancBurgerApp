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
import android.widget.TextView;

public class BurgerSelectActivity extends Activity {
	Button btnNext, btnExit;
	private String tamano = "", tipo_carne = "", tipo_burger = "";
	ArrayAdapter<CharSequence> adaptadorTamanoBurger, adaptadorTipoCarne,adaptadorTipoBurger;
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

		adaptadorTamanoBurger = ArrayAdapter.createFromResource(this, R.array.tamano_burger, android.R.layout.simple_spinner_item);
		Spinner spnTamano = (Spinner) findViewById(R.id.spnBurgerSize); 

		adaptadorTamanoBurger.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		spnTamano.setAdapter(adaptadorTamanoBurger); 

		spnTamano.setOnItemSelectedListener( 
				new AdapterView.OnItemSelectedListener() { 
					public void onItemSelected(AdapterView<?> parent, 
							android.view.View v, int position, long id) { 
						tamano = adaptadorTamanoBurger.getItem(position).toString(); 
					} 
					public void onNothingSelected(AdapterView<?> parent) { 
						tamano = ""; 
					} 
				});

		adaptadorTipoCarne = ArrayAdapter.createFromResource(this, R.array.tipo_carne, android.R.layout.simple_spinner_item);
		Spinner spnTipoCarne = (Spinner) findViewById(R.id.spnMeatType); 

		adaptadorTamanoBurger.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		spnTipoCarne.setAdapter(adaptadorTipoCarne); 

		spnTipoCarne.setOnItemSelectedListener( 
				new AdapterView.OnItemSelectedListener() { 
					public void onItemSelected(AdapterView<?> parent, 
							android.view.View v, int position, long id) { 
						tipo_carne = adaptadorTipoCarne.getItem(position).toString(); 
					} 
					public void onNothingSelected(AdapterView<?> parent) { 
						tipo_carne = ""; 
					} 
				});
		
		adaptadorTipoBurger = ArrayAdapter.createFromResource(this, R.array.tipo_burger, android.R.layout.simple_spinner_item);
		Spinner spnTipoBurger = (Spinner) findViewById(R.id.spnBurgerType); 

		adaptadorTipoBurger.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		spnTipoBurger.setAdapter(adaptadorTipoBurger); 

		spnTipoBurger.setOnItemSelectedListener( 
				new AdapterView.OnItemSelectedListener() { 
					public void onItemSelected(AdapterView<?> parent, 
							android.view.View v, int position, long id) { 
						tipo_burger = adaptadorTipoBurger.getItem(position).toString(); 
					} 
					public void onNothingSelected(AdapterView<?> parent) { 
						tipo_burger = ""; 
					} 
				});
		
	}
}
