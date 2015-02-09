package com.grupo5.cebancburger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.grupo5.cebancburger.model.Customer;
import com.grupo5.cebancburger.model.Order;
import com.grupo5.cebancburger.model.User;

public class MainActivity extends Activity {

	Button btnNext, btnExit, btnAdmin;
	EditText edtName, edtAddress, edtPhone;
	AlertDialog.Builder alert;

	private Activity activity = this;

	private int UserID;

	private AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = getIntent();
		UserID = intent.getIntExtra("id",-1);

		edtName = (EditText) findViewById(R.id.edtName);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		edtPhone = (EditText) findViewById(R.id.edtPhone);
		btnExit = (Button) findViewById(R.id.btnExit);
		btnNext = (Button) findViewById(R.id.btnNext);
		btnAdmin = (Button) findViewById(R.id.btnAdmin);

		if (!new User(UserID,activity).isAdmin())
			btnAdmin.setVisibility(View.GONE);

		alert = new AlertDialog.Builder(this);

		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goBack();
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
				//TODO Letra DNI
				char IDChar = 'a';

				if (name.equals("") || address.equals("") || phone.equals("")) {

					alert.setTitle("¡CUIDADO!")
					.setMessage("Introduce todos tus datos")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface dialog, int id) {
							// do things
						}
					}).show();
				} else {
					Customer cliente = new Customer(name, address, phone,IDChar);
					pedido.setCliente(cliente);
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
