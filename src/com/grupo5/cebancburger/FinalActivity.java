package com.grupo5.cebancburger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FinalActivity extends Activity {
	Button btnExit, btnNuevo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);

		btnExit = (Button) findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			      closeApp();

			}
		});

		btnNuevo = (Button) findViewById(R.id.btnNuevo);
		btnNuevo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// create intent
				Intent intent = new Intent(getApplicationContext(),
						ViewPedidoActivity.class);
				// add data to intent
				startActivityForResult(intent, 50);
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
