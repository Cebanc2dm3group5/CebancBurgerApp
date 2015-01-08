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

import com.grupo5.cebancburger.model.Cliente;
import com.grupo5.cebancburger.model.Pedido;

public class MainActivity extends Activity {

	Button btnNext, btnExit;
	EditText edtName, edtAddress, edtPhone;
	AlertDialog.Builder alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		alert = new AlertDialog.Builder(this);

		edtName = (EditText) findViewById(R.id.edtName);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		edtPhone = (EditText) findViewById(R.id.edtPhone);

		btnExit = (Button) findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeApp();
			}
		});

		btnNext = (Button) findViewById(R.id.btnNext);
		btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// get data
				Pedido pedido = new Pedido();

				String name = edtName.getText().toString();
				String address = edtAddress.getText().toString();
				String phone = edtPhone.getText().toString();

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
					Cliente cliente = new Cliente(name, address, phone);
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
	}

	@Override
	public void onBackPressed() {
	}
	
	private void closeApp(){
		ActivityCompat.finishAffinity(this);
	}

}
