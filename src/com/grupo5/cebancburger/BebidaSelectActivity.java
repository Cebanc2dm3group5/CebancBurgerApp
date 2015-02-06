package com.grupo5.cebancburger;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.grupo5.cebancburger.adapters.CardArrayAdapter;
import com.grupo5.cebancburger.ddbbrepo.tables.DrinkTypeTable;
import com.grupo5.cebancburger.model.Drink;
import com.grupo5.cebancburger.model.Order;
import com.grupo5.cebancburger.viewmodels.Card;

public class BebidaSelectActivity extends Activity {
	Order pedido;
	Button btnNext, btnExit, btnAddBebida;
	EditText edtBebidaNum;
	private int tipo_bebida = 0;
	private double precio_bebida = 0;
	
	ArrayList<ArrayList<String>> arrTiposBebida = new ArrayList<ArrayList<String>>();
	
	ArrayAdapter<String> adaptadorTipoBebida;

	ArrayList<Drink> arrBebida;

	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;
	AlertDialog.Builder builder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bebida_select_layout);
		// recogemos datos del intent
		Intent intent = getIntent();
		pedido = (Order) intent.getSerializableExtra("pedido");
		
		// llenamos el array con la información de las bebidas
		arrTiposBebida = DrinkTypeTable.getDrinkTypes(this);

		edtBebidaNum = (EditText) findViewById(R.id.edtBebidaNumber);
		edtBebidaNum.setText("1");

		builder = new AlertDialog.Builder(this);
		listView = (ListView) findViewById(R.id.card_bebidas_listView);

		listView.addHeaderView(new View(this));
		listView.addFooterView(new View(this));

		cardArrayAdapter = new CardArrayAdapter(getApplicationContext(),
				R.layout.list_item_card);

		loadCardListData();
		listView.setAdapter(cardArrayAdapter);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				setCardYesNo(position);
				return false;
			}
		});

		btnAddBebida = (Button) findViewById(R.id.btnAddBebida);
		btnAddBebida.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!(edtBebidaNum.getText().toString().equals(""))
						&& (!(edtBebidaNum.getText().toString().equals("0")))) {
					try {
						int cantidad = Integer.parseInt(edtBebidaNum.getText()
								.toString());

						Drink bebida = new Drink(tipo_bebida, cantidad, precio_bebida);
						pedido.setBebida(bebida);

						Toast.makeText(getApplicationContext(),
								"Bebida guardada", Toast.LENGTH_SHORT).show();
						loadCardListData();
						cardArrayAdapter.notifyDataSetChanged();

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
				goBack();
			}
		});

		btnNext = (Button) findViewById(R.id.btnNext3);
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (pedido.getBebida().size() < 1) {
					newBuilder();
					builder.setTitle(
							"¡CUIDADO " + pedido.getCliente().getNombre() + "!")
							.setMessage("Selecciona por lo menos una bebida")
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// do nothing
										}
									}).show();
				} else {
					Intent intent = new Intent(getApplicationContext(),
							OrderRevisionActivity.class);
					// add data to intent
					intent.putExtra("pedido", pedido);
					startActivityForResult(intent, 3);
				}
			}
		});
		
		String[] arrDescBebida = {};
		for (int i=0; i<arrTiposBebida.get(1).size(); i++){
			arrDescBebida[i] = arrTiposBebida.get(1).get(i);
		}
//		adaptadorTipoBebida = ArrayAdapter.createFromResource(this,
//				android.R.layout.simple_spinner_item, arrTiposBebida);
		adaptadorTipoBebida = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrTiposBebida.get(1));
		Spinner spnTipoBebida = (Spinner) findViewById(R.id.spnBebidaType);

		adaptadorTipoBebida
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTipoBebida.setAdapter(adaptadorTipoBebida);

		spnTipoBebida
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						tipo_bebida = position;
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	private void loadCardListData() {
		arrBebida = pedido.getBebida();

		cardArrayAdapter.clear();

		for (int i = 0; i < arrBebida.size(); i++) {
			Card card = new Card(arrBebida.get(i).getCantidad() + " x "
					+ arrBebida.get(i).getTipo(), "", arrBebida.get(i)
					.getPrecio());
			cardArrayAdapter.add(card);
		}
	}

	private void setCardYesNo(final int position) {
		// Put up the Yes/No message box
		newBuilder();
		builder.setTitle("Eliminar item")
				.setMessage("¿Estás seguro?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Eliminar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Yes button clicked, do something
								if (position <= arrBebida.size()) {
									pedido.getBebida().remove(position - 1);
								}

								loadCardListData();
								cardArrayAdapter.notifyDataSetChanged();

							}
						}).setNegativeButton("No", null) // Do nothing on no
				.show();
	}

	private void goBack() {
		if (pedido.getBebida().size() != 0) {
			newBuilder();
			builder.setTitle("Atrás")
					.setMessage(
							"Si vuelves atrás perderás las bebidas seleccionadas ¿Estás seguro?")
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
		} else {
			Intent returnIntent = new Intent();
			setResult(RESULT_CANCELED, returnIntent);
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		goBack();
	}

	public void newBuilder() {
		builder = new AlertDialog.Builder(this);

	}
}