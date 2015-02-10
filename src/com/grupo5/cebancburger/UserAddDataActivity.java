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

import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;
import com.grupo5.cebancburger.model.Burger;
import com.grupo5.cebancburger.model.User;

public class UserAddDataActivity extends Activity{

	private final Activity act = this;

	private int nId;

	private User user;

	private TextView txtUserName, txtPassword;
	private CheckBox cbAdmin;
	private Button btnGuardar,btnCancelar;

	private AlertDialog.Builder builder;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_add_data_layout);

		Intent intent = getIntent();
		nId = intent.getIntExtra("id",-1);

		txtUserName = (TextView) findViewById(R.id.edtUserAddUsername);
		txtPassword = (TextView) findViewById(R.id.edtUserAddPassword);
		cbAdmin = (CheckBox) findViewById(R.id.chkAdmin);
		btnGuardar = (Button) findViewById(R.id.btnUserAddSave);
		btnCancelar = (Button) findViewById(R.id.btnUserAddCancel);

		if (nId != -1){
			loadUserData();
		}

		btnGuardar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (txtUserName.getText().toString().equals("") || txtPassword.getText().toString().equals("")){

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
		String userName = txtUserName.getText().toString();
		String password = txtPassword.getText().toString();
		boolean admin = cbAdmin.isChecked();

		if (nId == -1){

			user = new User(userName,password,admin);
			user.save(act);

		}else{

			user.setUserID(nId);
			user.setUsername(userName);
			user.setPassword(password);
			user.setAdmin(admin);

			user.save(act);

		}

		showToast("Usuario guardado");
	}

	private void loadUserData(){

		user = new User(nId,this);
		txtUserName.setText(user.getUsername());
		txtPassword.setText(user.getPassword());
		cbAdmin.setChecked(user.isAdmin());
	}

	private void showToast(String mensaje){
		Toast.makeText(getApplicationContext(),
				mensaje, Toast.LENGTH_SHORT)
				.show();
	}
	public void newBuilder() {
		builder = new AlertDialog.Builder(this);

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
