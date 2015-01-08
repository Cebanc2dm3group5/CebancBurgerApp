package com.grupo5.cebancburger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.grupo5.cebancburger.model.Bebida;
import com.grupo5.cebancburger.model.Pedido;

public class BebidaSelectActivity extends Activity {
	Pedido pedido;
	Button btnNext, btnExit, btnAddBebida;
	EditText edtBebidaNum;
	private String tipo_bebida = "Cola";
	ArrayAdapter<CharSequence> adaptadorTipoBebida;
	AlertDialog.Builder alert;

	// Pedido pedido;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bebida_select_layout);
		// recogemos datos del intent
		Intent intent = getIntent();
		pedido = (Pedido) intent.getSerializableExtra("pedido");

		edtBebidaNum = (EditText) findViewById(R.id.edtBebidaNumber);
		edtBebidaNum.setText("1");

		btnAddBebida = (Button) findViewById(R.id.btnAddBebida);
		btnAddBebida.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!(edtBebidaNum.getText().toString().equals(""))) {
					try {
						int cantidad = Integer.parseInt(edtBebidaNum.getText()
								.toString());

						Bebida bebida = new Bebida(tipo_bebida, cantidad);
						pedido.setBebida(bebida);

						Toast.makeText(getApplicationContext(),
								"Bebida guardada", Toast.LENGTH_SHORT).show();

						edtBebidaNum.setText("1");

					} catch (Exception e) {
						Toast.makeText(getApplicationContext(),
								"Se ha producido un error", Toast.LENGTH_SHORT)
								.show();
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"Debe indicar una cantidad", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});

		btnExit = (Button) findViewById(R.id.btnExit3);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alert.setTitle("Atras")
				.setMessage("Si vuelves atras perderas las bebidas seleccionadas ¿Estás seguro?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Sí",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						// Yes button clicked, do something

						Intent returnIntent = new Intent();
						setResult(RESULT_CANCELED, returnIntent);
						finish();

					}
				}).setNegativeButton("No", null) // Do nothing on no
				.show();

			}
		});

		btnNext = (Button) findViewById(R.id.btnNext3);
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (pedido.getBebida().size() < 1){
					alert.setTitle("¡CUIDADO " + pedido.getCliente().getNombre() + "!")
	            	.setMessage("Selecciona por lo menos una bebida")
	            	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int id) {
	                        //do nothing
	                    }})
	            	.show();
				}else{
					Intent intent = new Intent(getApplicationContext(),
							OrderRevisionActivity.class);
					// add data to intent
					intent.putExtra("pedido", pedido);
					startActivityForResult(intent, 2);
				}
			}
		});

		adaptadorTipoBebida = ArrayAdapter.createFromResource(this,
				R.array.tipo_bebida, android.R.layout.simple_spinner_item);
		Spinner spnTipoBebida = (Spinner) findViewById(R.id.spnBebidaType);

		adaptadorTipoBebida
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTipoBebida.setAdapter(adaptadorTipoBebida);

		spnTipoBebida
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						tipo_bebida = adaptadorTipoBebida.getItem(position)
								.toString();
					}

					public void onNothingSelected(AdapterView<?> parent) {
						tipo_bebida = "";
					}
				});
	}
}