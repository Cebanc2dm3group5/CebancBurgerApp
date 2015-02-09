package com.grupo5.cebancburger;

import android.app.Activity;
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
				
				String name = txtNombre.getText().toString();
				char IDLet = txtDireccion.getText().toString().charAt(0);
				String address = txtLetraDNI.getText().toString();
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
				
				Toast.makeText(getApplicationContext(),
						"Cliente guardado", Toast.LENGTH_SHORT)
						.show();


			}
		});

	}

	private void loadCustomerData(){

		customer = new Customer(nId,this);
		txtNombre.setText(customer.getNombre());
		txtDireccion.setText(customer.getDireccion());
		txtLetraDNI.setText(customer.getIdLet());
		txtTelefono.setText(customer.getTelefono());
	}

}
