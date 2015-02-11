package com.grupo5.cebancburger;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grupo5.cebancburger.ddbbrepo.tables.CustomerTable;
import com.grupo5.cebancburger.model.Customer;
import com.grupo5.cebancburger.model.Order;
import com.grupo5.cebancburger.model.User;

public class MainActivity extends Activity {

	Button btnNext, btnExit, btnAdmin;
	EditText edtName, edtAddress, edtPhone, edtIDChar;
	AlertDialog.Builder alert;

	private Activity activity;

	private int userID, customerID = -1;

	private AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		activity = this;
		Intent intent = getIntent();
		userID = intent.getIntExtra("id", -1);

		edtName = (EditText) findViewById(R.id.edtName);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		edtPhone = (EditText) findViewById(R.id.edtPhone);
		edtIDChar = (EditText) findViewById(R.id.edtIDChar);
		btnExit = (Button) findViewById(R.id.btnExit);
		btnNext = (Button) findViewById(R.id.btnNext);
		btnAdmin = (Button) findViewById(R.id.btnAdmin);
		if (!new User(userID, activity).isAdmin())
			btnAdmin.setVisibility(View.GONE);

		alert = new AlertDialog.Builder(this);

		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goBack();
			}

		});

		edtIDChar.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				String name = edtName.getText().toString();
				String letraIDStr = edtIDChar.getText().toString().toUpperCase();
				if (letraIDStr.length() > 0) {
					char letraID = letraIDStr.charAt(0);
					ArrayList<Customer> arrCust = CustomerTable.getCustomers(
							activity, "Name = '" + name + "' AND IDChar = '"
									+ letraID + "'");
					if (arrCust.size() == 1) {
						Toast.makeText(getApplicationContext(),
								"Cliente reconocido", Toast.LENGTH_SHORT)
								.show();
						Customer cliente = arrCust.get(0);
						edtAddress.setText(cliente.getDireccion());
						edtPhone.setText(cliente.getTelefono());
						customerID = cliente.getId();
					}
				}

			}

		});

		edtName.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				String name = edtName.getText().toString();
				String letraIDStr = edtIDChar.getText().toString();
				if (letraIDStr.length() > 0) {
					char letraID = letraIDStr.charAt(0);
					ArrayList<Customer> arrCust = CustomerTable.getCustomers(
							activity, "Name = '" + name + "' AND IDChar = '"
									+ letraID + "'");
					if (arrCust.size() == 1) {
						Toast.makeText(getApplicationContext(),
								"Cliente reconocido", Toast.LENGTH_SHORT)
								.show();
						Customer cliente = arrCust.get(0);
						edtAddress.setText(cliente.getDireccion());
						edtPhone.setText(cliente.getTelefono());
						customerID = cliente.getId();
					}
				}

			}

		});

		btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// get data
				Order pedido = new Order();

				String name = edtName.getText().toString();
				String address = edtAddress.getText().toString();
				String phone = edtPhone.getText().toString();
				char letraID = '0';
				if (!edtIDChar.getText().toString().equals("")){
					letraID = edtIDChar.getText().toString().toUpperCase().charAt(0);
				}

				if (name.equals("") || address.equals("") || phone.equals("") || letraID < 'A' || letraID > 'Z') {

					alert.setTitle("¡CUIDADO!")
							.setMessage("Introduce todos tus datos correctamente")
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// do things
										}
									}).show();
				} else {
					Customer cliente = null;
					if (customerID == -1) {
						cliente = new Customer(name, address, phone, letraID);
						cliente.save(activity);
						Toast.makeText(getApplicationContext(),
								"Cliente nuevo", Toast.LENGTH_SHORT).show();
					} else {
						cliente = CustomerTable.getCustomer(activity,
								customerID);
					}

					pedido.setCliente(cliente);

					pedido.setUserID(userID);
					// create intent
					Intent intent = new Intent(getApplicationContext(),
							BurgerSelectActivity.class);
					// add data to intent
					intent.putExtra("pedido", pedido);

					// start activity
					startActivityForResult(intent, 1);
				}
			}
		});

		btnAdmin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// create intent
				Intent intent = new Intent(getApplicationContext(),
						AdminExpandableLayoutActivity.class);

				// start activity
				startActivityForResult(intent, 1);
			}
		});

	}

	@Override
	public void onBackPressed() {
		goBack();
	}

	public void goBack() {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		finish();
	}

	public void newBuilder() {
		builder = new AlertDialog.Builder(this);
	}

}
