package com.grupo5.cebancburger;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.grupo5.cebancburger.adapters.CardArrayAdapter;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderTable;
import com.grupo5.cebancburger.model.Order;
import com.grupo5.cebancburger.viewmodels.Card;

public class ViewPedidoActivity extends Activity{
	
	private ListView listView;
	
	private CardArrayAdapter cardArrayAdapter;
	
	private ArrayList<Order> arrOrder;
	
	private int nID;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pedido_layout);
		
		listView = (ListView) findViewById(R.id.card_sel_ped_listView);
		cardArrayAdapter = new CardArrayAdapter(getApplicationContext(),
				R.layout.list_item_card);
		listView.setAdapter(cardArrayAdapter);
		loadCardListData();

		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				nID = arrOrder.get(position).getOrderID();
				
				Intent intent = new Intent(getApplicationContext(),
						ViewPedidoLineasActivity.class);
				// add data to intent
				intent.putExtra("id", nID);
				startActivityForResult(intent, 40);
				return false;
			}
		});
		
	}
	
	private void loadCardListData() {

			arrOrder = OrderTable.getAllOrders(this);

			cardArrayAdapter.clear();

			for (int i = 0; i < arrOrder.size(); i++) {
				arrOrder.get(i).setFinalPrice();
				Card card = new Card(arrOrder.get(i).getDate().toString(),
						arrOrder.get(i).getTime().toString(),arrOrder.get(i).getFinalPrecio());
				cardArrayAdapter.add(card);
			}
		
	}

}
