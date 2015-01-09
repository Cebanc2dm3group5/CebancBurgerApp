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
import android.widget.TextView;
import android.widget.Toast;

import com.grupo5.cebancburger.adapters.CardArrayAdapter;
import com.grupo5.cebancburger.model.Burger;
import com.grupo5.cebancburger.model.Pedido;
import com.grupo5.cebancburger.viewmodels.Card;

public class BurgerSelectActivity extends Activity {
	Button btnNext, btnExit, btnAddBurger;
	EditText edtBurgerNum;
	TextView lblTitleBurger;

	private String tamano = "Normal", tipo_carne = "Buey",
			tipo_burger = "Clásica";
	ArrayAdapter<CharSequence> adaptadorTamanoBurger, adaptadorTipoCarne,
			adaptadorTipoBurger;
	Pedido pedido;
	ArrayList<Burger> arrBurger;

	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;
	AlertDialog.Builder builder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.burgers_select_layout);

		lblTitleBurger = (TextView) findViewById(R.id.lblTitleBurger);

		// recogemos datos del intent
		Intent intent = getIntent();
		pedido = (Pedido) intent.getSerializableExtra("pedido");
		lblTitleBurger.setText("Hola " + pedido.getCliente().getNombre()
				+ ", ¿qué te apetece?");
		edtBurgerNum = (EditText) findViewById(R.id.edtBurgerNumber);
		edtBurgerNum.setText("1");

		builder = new AlertDialog.Builder(this);
		listView = (ListView) findViewById(R.id.card_burger_listView);

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

		btnAddBurger = (Button) findViewById(R.id.btnAddBurger);
		btnAddBurger.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!(edtBurgerNum.getText().toString().equals(""))) {
					try {
						int cantidad = Integer.parseInt(edtBurgerNum.getText()
								.toString());

						Burger burger = new Burger(tamano, tipo_carne,
								tipo_burger, cantidad);

						pedido.setBurger(burger);

						Toast.makeText(getApplicationContext(),
								"Burger guardada", Toast.LENGTH_SHORT).show();
						loadCardListData();
						cardArrayAdapter.notifyDataSetChanged();

						edtBurgerNum.setText("1");

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

		btnExit = (Button) findViewById(R.id.btnExit2);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goBack();
			}
		});

		btnNext = (Button) findViewById(R.id.btnNext2);
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (pedido.getBurger().size() < 1) {
					newBuilder();
					builder.setTitle(
							"¡CUIDADO " + pedido.getCliente().getNombre() + "!")
							.setMessage("Selecciona por lo menos un burger")
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// do nothing
										}
									}).show();
				} else {
					// on first activity
					Intent intent = new Intent(getApplicationContext(),
							BebidaSelectActivity.class);
					// add data to intent
					intent.putExtra("pedido", pedido);

					// start activity
					startActivityForResult(intent, 2);
				}
			}
		});

		adaptadorTamanoBurger = ArrayAdapter.createFromResource(this,
				R.array.tamano_burger, android.R.layout.simple_spinner_item);
		Spinner spnTamano = (Spinner) findViewById(R.id.spnBurgerSize);

		adaptadorTamanoBurger
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTamano.setAdapter(adaptadorTamanoBurger);

		spnTamano
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						tamano = adaptadorTamanoBurger.getItem(position)
								.toString();
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		adaptadorTipoCarne = ArrayAdapter.createFromResource(this,
				R.array.tipo_carne, android.R.layout.simple_spinner_item);
		Spinner spnTipoCarne = (Spinner) findViewById(R.id.spnMeatType);

		adaptadorTamanoBurger
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTipoCarne.setAdapter(adaptadorTipoCarne);

		spnTipoCarne
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						tipo_carne = adaptadorTipoCarne.getItem(position)
								.toString();
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		adaptadorTipoBurger = ArrayAdapter.createFromResource(this,
				R.array.tipo_burger, android.R.layout.simple_spinner_item);
		Spinner spnTipoBurger = (Spinner) findViewById(R.id.spnBurgerType);

		adaptadorTipoBurger
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTipoBurger.setAdapter(adaptadorTipoBurger);

		spnTipoBurger
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						tipo_burger = adaptadorTipoBurger.getItem(position)
								.toString();
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

	}

	private void loadCardListData() {
		arrBurger = pedido.getBurger();

		cardArrayAdapter.clear();
		for (int i = 0; i < arrBurger.size(); i++) {
			Card card = new Card(arrBurger.get(i).getCantidad() + " x "
					+ arrBurger.get(i).getTipoBurger() + " de "
					+ arrBurger.get(i).getTipoCarne(), "Tamaño: "
					+ arrBurger.get(i).getTamano(), arrBurger.get(i)
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
								if (position <= arrBurger.size()) {
									pedido.getBurger().remove(position - 1);
								}

								loadCardListData();
								cardArrayAdapter.notifyDataSetChanged();

							}
						}).setNegativeButton("No", null) // Do nothing on no
				.show();
	}

	public void goBack() {
		if (pedido.getBurger().size() != 0) {
			newBuilder();
			builder.setTitle("Atrás")
					.setMessage(
							"Si vuelves atrás perderás las burgers seleccionadas ¿Estás seguro?")
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
