package com.grupo5.cebancburger;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.grupo5.cebancburger.adapters.CardArrayAdapter;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderLineBurgerTable;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderLineDrinkTable;
import com.grupo5.cebancburger.model.Burger;
import com.grupo5.cebancburger.model.Drink;
import com.grupo5.cebancburger.viewmodels.Card;

public class ViewPedidoLineasActivity extends Activity{

	private ListView listView;
	
	private Button btnAceptar;

	private CardArrayAdapter cardArrayAdapter;
	
	private int orderID;
	
	private ArrayList<Burger> arrBurger;
	private ArrayList<Drink> arrDrink;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pedido_lineas_layout);

		Intent intent = getIntent();
		orderID = intent.getIntExtra("id", -1);
		listView = (ListView) findViewById(R.id.card_ped_lin_listView);
		loadCardListData();
		listView.setAdapter(cardArrayAdapter);
		
		btnAceptar = (Button) findViewById(R.id.btnAceptarLinea);
		
		btnAceptar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goBack();
			}
		});

	}

	private void loadCardListData() {

		arrBurger = OrderLineBurgerTable.getOrderBurgers(this, orderID);
		arrDrink = OrderLineDrinkTable.getOrderDrinks(this, orderID);
		
		cardArrayAdapter.clear();

		for (int i = 0; i < arrBurger.size(); i++) {
			Card card = new Card(arrBurger.get(i).getCantidad() + " x "
					+ arrBurger.get(i).getTipoBurger() + " de "
					+ arrBurger.get(i).getTipoCarne(), "Tamaño: "
					+ arrBurger.get(i).getTamano(), arrBurger.get(i)
					.getPrecio());
			cardArrayAdapter.add(card);
		}
		for (int i = 0; i < arrDrink.size(); i++) {
			Card card = new Card(arrDrink.get(i).getCantidad() + " x "
					+ arrDrink.get(i).getTipo(), "", arrDrink.get(i)
					.getPrecio());
			cardArrayAdapter.add(card);
		}

	}
	
	public void goBack() {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		goBack();
	}

}
