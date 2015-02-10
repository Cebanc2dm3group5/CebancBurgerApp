package com.grupo5.cebancburger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo5.cebancburger.model.Customer;
import com.grupo5.cebancburger.model.User;

public class CustomerAddDataActivity  extends Activity{

	private final Activity activity = this;

	private int nId;

	private Customer customer;

	private TextView txtNombre, txtDireccion, txtLetraDNI, txtTelefono;
	private Button btnGuardar, btnCancelar;

	private AlertDialog.Builder builder;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_add_data_layout);

		Intent intent = getIntent();
		nId = intent.getIntExtra("id",-1);

		txtNombre = (TextView) findViewById(R.id.edtCustomerAddName);
		txtDireccion = (TextView) findViewById(R.id.edtCustomerAddAddress);
		txtLetraDNI = (TextView) findViewById(R.id.edtCustomerAddIDChar);
		txtTelefono = (TextView) findViewById(R.id.edtCustomerAddTel);
		btnGuardar = (Button) findViewById(R.id.btnCustomerAddSave);
		btnCancelar = (Button) findViewById(R.id.btnCustomerAddCancel);

		if (nId != -1){
			loadCustomerData();
		}

		btnGuardar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (txtNombre.getText().toString().equals("") || txtDireccion.getText().toString().equals("") || txtLetraDNI.getText().toString().equals("") || txtTelefono.getText().toString().equals("")){
					newBuilder();
					builder.setTitle("¡CUIDADO!")
					.setMessage("Introduzca todos los datos")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {


						}
					})
					.show();
				}else if (txtLetraDNI.getText().toString().length() > 1){
					newBuilder();
					builder.setTitle("¡CUIDADO!")
					.setMessage("Introduzca solo una letra del DNI")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {


						}
					})
					.show();
				}else if (txtLetraDNI.getText().toString().charAt(0) >= '0' && txtLetraDNI.getText().toString().charAt(0) <= '9'){
					newBuilder();
					builder.setTitle("¡CUIDADO!")
					.setMessage("No introduzca números en la letra del DNI")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {


						}
					})
					.show();
				}else{
					saveData();
					goBack();
				}
			}
		});

		btnCancelar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				newBuilder();
				builder.setTitle("Salir sin guardar")
				.setMessage("¿Estás seguro?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("Sí",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						// Yes button clicked, do something
						goBack();

					}
				}).setNegativeButton("No", null) // Do nothing on no
				.show();

			}
		});

	}

	private void saveData(){

		String name = txtNombre.getText().toString();
		char IDLet = txtLetraDNI.getText().toString().charAt(0);
		String address = txtDireccion.getText().toString();
		String telephone = txtTelefono.getText().toString();

		if (nId == -1){

			customer = new Customer(name,address,telephone,IDLet);
			customer.save(activity);

		}else{

			customer.setDireccion(address);
			customer.setId(nId);
			customer.setIdLet(IDLet);
			customer.setNombre(name);
			customer.setTelefono(telephone);

			customer.save(activity);

		}

		showToast("Cliente guardado");

	}

	private void loadCustomerData(){

		customer = new Customer(nId,this);
		txtNombre.setText(customer.getNombre());
		txtDireccion.setText(customer.getDireccion());
		txtLetraDNI.setText(Character.toString(customer.getIdLet()));
		txtTelefono.setText(customer.getTelefono());
	}

	public void newBuilder() {
		builder = new AlertDialog.Builder(this);

	}
	public void goBack() {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		finish();
	}
	public void showToast(String mensaje){
		Toast.makeText(getApplicationContext(),
				mensaje, Toast.LENGTH_SHORT)
				.show();
	}

}
