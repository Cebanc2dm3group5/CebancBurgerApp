package com.grupo5.cebancburger;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;
import com.grupo5.cebancburger.model.User;

public class LoginActivity  extends Activity{

	private final Activity activity = this;
	
	private TextView txtUserName, txtPassword;
	private Button btnSalir, btnAceptar;
	
	private ArrayList<User> garrUser;
	
	private int UserID = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		DDBBSQLite.initDDBB(Options.getDDBBName(), this);
		
		txtUserName = (TextView) findViewById(R.id.edtUsername);
		txtPassword = (TextView) findViewById(R.id.edtPassword);
		btnSalir = (Button) findViewById(R.id.btnExitLogin);
		btnAceptar = (Button) findViewById(R.id.btnNextLogin);
		
		btnAceptar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				userExists();
				
			}
		});
		
		btnSalir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeApp();
			}
		});
		
	}
	
	private void closeApp(){
		ActivityCompat.finishAffinity(this);
	}
	
	private void userExists(){
		
		String condition = "UserName = '" + txtUserName.getText() + "' AND Password = '" + txtPassword.getText() + "'";
		garrUser = UserTable.getUsers(activity, condition);
		
		if (garrUser.size() != 1){
			
			UserID = -1;
			
			Toast.makeText(getApplicationContext(),
					 "Este usuario no existe", Toast.LENGTH_SHORT)
					.show();
			
		}else{
			
			UserID = garrUser.get(0).getUserID();
			
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			// add data to intent
			intent.putExtra("id", UserID);
			startActivityForResult(intent, 20);
			
		}
		
	}
	
}
