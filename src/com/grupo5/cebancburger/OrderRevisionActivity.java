package com.grupo5.cebancburger;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.grupo5.cebancburger.adapters.CardArrayAdapter;
import com.grupo5.cebancburger.viewmodels.Card;

public class OrderRevisionActivity extends Activity {
	Button btnExit, btnSend;

	private static final String TAG = "CardListActivity";
	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;
	private ArrayList<String> arr = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_final_layout);
		listView = (ListView) findViewById(R.id.card_listView);

		listView.addHeaderView(new View(this));
		listView.addFooterView(new View(this));

		arr.add("one");
		arr.add("two");
		arr.add("thre");

		cardArrayAdapter = new CardArrayAdapter(getApplicationContext(),
				R.layout.list_item_card);

		loadCardListData();
		listView.setAdapter(cardArrayAdapter);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				arr.remove(position - 1);
				loadCardListData();
				cardArrayAdapter.notifyDataSetChanged();
				return false;
			}
		});

		btnExit = (Button) findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				setResult(RESULT_CANCELED, returnIntent);
				finish();
			}
		});
	}
	
	private void loadCardListData(){
		cardArrayAdapter.clear();
		for (int i = 0; i < arr.size(); i++) {
			Card card = new Card("Card " + arr.get(i) + " Line 1", "Card "
					+ (i + 1) + " Line 2", i * 10);
			cardArrayAdapter.add(card);
		}
	}

}
