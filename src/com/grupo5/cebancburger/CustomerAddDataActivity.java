package com.grupo5.cebancburger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CustomerAddDataActivity  extends Activity{
	
	private int nId;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_add_data_layout);

		Intent intent = getIntent();
		nId = intent.getIntExtra("id",-1);
		
	}

}
