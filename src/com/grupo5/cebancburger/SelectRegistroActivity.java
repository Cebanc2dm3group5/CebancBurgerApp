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
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerMeatTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerSizeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerTypeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.CustomerTable;
import com.grupo5.cebancburger.ddbbrepo.tables.DrinkTypeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;
import com.grupo5.cebancburger.model.BurgerMeat;
import com.grupo5.cebancburger.model.BurgerSize;
import com.grupo5.cebancburger.model.BurgerType;
import com.grupo5.cebancburger.model.Customer;
import com.grupo5.cebancburger.model.DrinkType;
import com.grupo5.cebancburger.model.User;
import com.grupo5.cebancburger.viewmodels.Card;

public class SelectRegistroActivity extends Activity {

	private String sTitle;
	private TextView txtTitle;
	private Button btnAnadir;
	private int nId;
	private ListView listView;

	private ArrayList<User> arrUser;
	private ArrayList<Customer> arrCustomer;
	private ArrayList<ArrayList<String>> arrTypeSize;

	private CardArrayAdapter cardArrayAdapter;

	private Activity activity = this;

	AlertDialog.Builder builder;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_registro_layout);

		Intent intent = getIntent();
		sTitle = intent.getStringExtra("titulo");

		txtTitle = (TextView) findViewById(R.id.txtTitleSelReg);
		txtTitle.setText(sTitle);

		btnAnadir = (Button) findViewById(R.id.btnAnadirReg);
		btnAnadir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				nId = -1;
				openAddData();

			}
		});

		listView = (ListView) findViewById(R.id.card_sel_reg_listView);

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
				setAction(position - 1);
				return false;
			}
		});

	}

	private void openAddData() {

		if (sTitle.equals("Usuario")) {

			Intent intent = new Intent(getApplicationContext(),
					UserAddDataActivity.class);
			// add data to intent
			intent.putExtra("id", nId);
			startActivityForResult(intent, 11);

		} else if (sTitle.equals("Cliente")) {

			Intent intent = new Intent(getApplicationContext(),
					CustomerAddDataActivity.class);
			// add data to intent
			intent.putExtra("id", nId);
			startActivityForResult(intent, 12);

		} else {

			Intent intent = new Intent(getApplicationContext(),
					BurgerDrinkAddDataActivity.class);
			// add data to intent
			intent.putExtra("id", nId);
			intent.putExtra("titulo", sTitle);
			startActivityForResult(intent, 13);

		}

	}

	private void loadCardListData() {

		if (sTitle.equals("Usuario")) {

			arrUser = UserTable.getAllUsers(this);

			cardArrayAdapter.clear();

			for (int i = 0; i < arrUser.size(); i++) {
				Card card = new Card("Nombre Usuario: "
						+ arrUser.get(i).getUsername(), "Contraseña: "
						+ arrUser.get(i).getPassword(), 0);
				cardArrayAdapter.add(card);
			}

		} else if (sTitle.equals("Cliente")) {

			// TODO
			arrCustomer = CustomerTable.getAllCustomers(this);

			cardArrayAdapter.clear();

			for (int i = 0; i < arrCustomer.size(); i++) {
				Card card = new Card("Nombre: "
						+ arrCustomer.get(i).getNombre(), arrCustomer.get(i)
						.getDireccion(), 0);
				cardArrayAdapter.add(card);
			}

		} else if (sTitle.equals("Tipo de Burger")) {

			arrTypeSize = BurgerTypeTable.getBurgerTypes(this);

			cardArrayAdapter.clear();

			for (int i = 0; i < arrTypeSize.get(0).size(); i++) {
				Card card = new Card(arrTypeSize.get(1).get(i), "",
						Double.parseDouble(arrTypeSize.get(2).get(i)));
				cardArrayAdapter.add(card);
			}

		} else if (sTitle.equals("Tipo de Carne")) {

			arrTypeSize = BurgerMeatTable.getBurgerMeats(this);

			cardArrayAdapter.clear();

			for (int i = 0; i < arrTypeSize.get(0).size(); i++) {
				Card card = new Card(arrTypeSize.get(1).get(i), "",
						Double.parseDouble(arrTypeSize.get(2).get(i)));
				cardArrayAdapter.add(card);
			}

		} else if (sTitle.equals("Tamaño de Burger")) {

			arrTypeSize = BurgerSizeTable.getBurgerSizes(this);

			cardArrayAdapter.clear();

			for (int i = 0; i < arrTypeSize.get(0).size(); i++) {
				Card card = new Card(arrTypeSize.get(1).get(i), "",
						Double.parseDouble(arrTypeSize.get(2).get(i)));
				cardArrayAdapter.add(card);
			}

		} else if (sTitle.equals("Tipo de Bebida")) {

			arrTypeSize = DrinkTypeTable.getDrinkTypes(this);

			cardArrayAdapter.clear();

			for (int i = 0; i < arrTypeSize.get(0).size(); i++) {
				Card card = new Card(arrTypeSize.get(1).get(i), "",
						Double.parseDouble(arrTypeSize.get(2).get(i)));
				cardArrayAdapter.add(card);
			}

		}
	}

	private void setAction(final int position) {

		newBuilder();
		builder.setTitle("¿Que deseas hacer?")
				.setMessage("Selecciona una acción")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Editar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								getId(position);
								openAddData();

							}
						})
				.setNegativeButton("Borrar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								getId(position);
								setCardYesNo();

							}
						}).show();

	}

	private void getId(int position) {

		if (sTitle.equals("Usuario")) {

			nId = arrUser.get(position).getUserID();

		} else if (sTitle.equals("Cliente")) {

			nId = arrCustomer.get(position).getId();

		} else {

			nId = Integer.parseInt(arrTypeSize.get(position).get(0));

		}

	}

	private void setCardYesNo() {
		// Put up the Yes/No message box

		if (sTitle.equals("Usuario")) {

			new User(nId, activity).delete(activity);

		} else if (sTitle.equals("Cliente")) {

			// new Customer(nId,activity).delete(activity);

		} else if (sTitle.equals("Tipo de Burger")) {

			new BurgerType(nId, "", 0.0).delete(activity);

		} else if (sTitle.equals("Tipo de Carne")) {

			new BurgerMeat(nId, "", 0.0).delete(activity);

		} else if (sTitle.equals("Tamaño de Burger")) {

			new BurgerSize(nId, "", 0.0).delete(activity);

		} else if (sTitle.equals("Tipo de Bebida")) {

			new DrinkType(nId, "", 0.0).delete(activity);

		}

		/*
		 * newBuilder(); builder.setTitle("Eliminar item")
		 * .setMessage("¿Estás seguro?")
		 * .setIcon(android.R.drawable.ic_dialog_alert)
		 * .setPositiveButton("Eliminar", new DialogInterface.OnClickListener()
		 * { public void onClick(DialogInterface dialog, int which) { // Yes
		 * button clicked, do something if (position <= arrBurger.size()) {
		 * pedido.getBurger().remove(position - 1); }
		 * 
		 * loadCardListData(); cardArrayAdapter.notifyDataSetChanged();
		 * 
		 * } }).setNegativeButton("No", null) // Do nothing on no .show();
		 */
	}

	public void newBuilder() {
		builder = new AlertDialog.Builder(this);
	}

}
