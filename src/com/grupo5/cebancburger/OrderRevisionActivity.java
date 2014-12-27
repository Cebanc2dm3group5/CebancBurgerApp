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
import android.widget.Toast;

import com.grupo5.cebancburger.adapters.CardArrayAdapter;
import com.grupo5.cebancburger.viewmodels.Card;

public class OrderRevisionActivity extends Activity {
	Button btnExit, btnSend;

	private static final String TAG = "CardListActivity";
	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;
	private ArrayList<String> arr = new ArrayList<String>();
	AlertDialog.Builder builder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_final_layout);
		builder = new AlertDialog.Builder(this);
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
				setCardYesNo(position);
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
		
		btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setSendYesNo("Confirmar pedido", "¿Desea confirmar el pedido?\nUna vez confirmado, no podrá deshacerlo.");
				
			}
		});
	}

	private void loadCardListData() {
		cardArrayAdapter.clear();
		for (int i = 0; i < arr.size(); i++) {
			Card card = new Card("Card " + arr.get(i) + " Line 1", "Card "
					+ (i + 1) + " Line 2", i * 10);
			cardArrayAdapter.add(card);
		}
	}

	private void setCardYesNo(final int position) {
		// Put up the Yes/No message box
		builder.setTitle("Eliminar " + arr.get(position - 1))
				.setMessage("¿Estás seguro?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Eliminar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Yes button clicked, do something
								arr.remove(position - 1);
								loadCardListData();
								cardArrayAdapter.notifyDataSetChanged();
							}
						}).setNegativeButton("No", null) // Do nothing on no
				.show();
	}

	private void setSendYesNo(String title, String msg) {
		// Put up the Yes/No message box
		builder.setTitle(title)
				.setMessage(msg)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Enviar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Yes button clicked, do something
								// TODO - Send the order
							}
						}).setNegativeButton("No enviar", null) // Do nothing on
																// no
				.show();
	}
}
