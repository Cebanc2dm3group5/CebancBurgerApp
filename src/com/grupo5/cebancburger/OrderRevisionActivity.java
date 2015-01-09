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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.grupo5.cebancburger.adapters.CardArrayAdapter;
import com.grupo5.cebancburger.model.Bebida;
import com.grupo5.cebancburger.model.Burger;
import com.grupo5.cebancburger.model.Pedido;
import com.grupo5.cebancburger.viewmodels.Card;

public class OrderRevisionActivity extends Activity {
	Button btnExit, btnSend;
	Pedido pedido;
	ArrayList<Burger> arrBurger;
	ArrayList<Bebida> arrBebida;
	TextView lblPrecio, lblRegalo;

	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;
	AlertDialog.Builder builder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_final_layout);

		// recogemos datos del intent
		Intent intent = getIntent();
		pedido = (Pedido) intent.getSerializableExtra("pedido");

		lblPrecio = (TextView) findViewById(R.id.lblPrecio);
		String precioText = String.valueOf(pedido.getPrecio());
		lblPrecio.setText(precioText);

		lblRegalo = (TextView) findViewById(R.id.lblRegalo);
		String regaloText = pedido.getRegalo();
		lblRegalo.setText(regaloText);

		listView = (ListView) findViewById(R.id.card_listView);

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

		btnExit = (Button) findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goBack();
			}
		});

		btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setSendYesNo("Confirmar pedido",
						"¿Desea confirmar el pedido?\nUna vez confirmado, no podrá deshacerlo.");

			}
		});
	}

	private void loadCardListData() {
		arrBurger = pedido.getBurger();
		arrBebida = pedido.getBebida();

		cardArrayAdapter.clear();
		for (int i = 0; i < arrBurger.size(); i++) {
			Card card = new Card(arrBurger.get(i).getCantidad() + " x "
					+ arrBurger.get(i).getTipoBurger() + " de "
					+ arrBurger.get(i).getTipoCarne(), "Tamaño: "
					+ arrBurger.get(i).getTamano(), arrBurger.get(i)
					.getPrecio());
			cardArrayAdapter.add(card);
		}
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
								if (position <= arrBurger.size()) {
									pedido.getBurger().remove(position - 1);
								} else {
									pedido.getBebida().remove(
											position - arrBurger.size() - 1);
								}

								loadCardListData();
								cardArrayAdapter.notifyDataSetChanged();
								String precioText = String.valueOf(pedido
										.getPrecio());
								lblPrecio.setText(precioText);
								String regaloText = pedido.getRegalo();
								lblRegalo.setText(regaloText);

							}
						}).setNegativeButton("No", null) // Do nothing on no
				.show();
	}

	private void setSendYesNo(String title, String msg) {
		// Put up the Yes/No message box
		newBuilder();
		if ((pedido.getBurger().size() != 0)
				&& (pedido.getBebida().size() != 0)) {
			builder.setTitle(title)
					.setMessage(msg)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton("Enviar",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// Yes button clicked, do something
									pedido.setFinalPrice();
									// TODO - Send the order to REST API

									// open last activity
									Intent intent = new Intent(
											getApplicationContext(),
											FinalActivity.class);
									// add data to intent
									startActivity(intent);

								}
							}).setNegativeButton("No enviar", null) // Do
																	// nothing
																	// on
																	// no
					.show();
		} else {
			builder.setTitle("Pedido vacío")
					.setMessage(
							"Debes pedir como mínimo una bebida y un burger")
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// do nothing
								}
							}).show();
		}
	}

	private void goBack() {
		newBuilder();
		builder.setTitle("Atras")
				.setMessage(
						"Si vuelves atras perderas los cambios ¿Estás seguro?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Yes button clicked, do something

						Intent returnIntent = new Intent();
						setResult(RESULT_CANCELED, returnIntent);
						finish();

					}
				}).setNegativeButton("No", null) // Do nothing
													// on no
				.show();
	}

	@Override
	public void onBackPressed() {
		goBack();
	}

	public void newBuilder() {
		builder = new AlertDialog.Builder(this);

	}
}
