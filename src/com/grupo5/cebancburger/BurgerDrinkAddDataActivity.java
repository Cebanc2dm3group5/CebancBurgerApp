package com.grupo5.cebancburger;

import java.text.NumberFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo5.cebancburger.ddbbrepo.tables.BurgerMeatTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerSizeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.BurgerTypeTable;
import com.grupo5.cebancburger.ddbbrepo.tables.DrinkTypeTable;
import com.grupo5.cebancburger.model.BurgerMeat;
import com.grupo5.cebancburger.model.BurgerSize;
import com.grupo5.cebancburger.model.BurgerType;
import com.grupo5.cebancburger.model.DrinkType;

public class BurgerDrinkAddDataActivity  extends Activity{

	private String sTitle;
	private TextView txtTitle, txtName, txtPrice;
	private Button btnGuardar,btnCancelar;

	private int nId;

	private BurgerMeat burgerMeat = new BurgerMeat(0,"",0);
	private BurgerSize burgerSize = new BurgerSize(0,"",0);
	private BurgerType burgerType = new BurgerType(0,"",0);
	private DrinkType drinkType = new DrinkType(0,"",0);

	private final Activity activity = this;

	private AlertDialog.Builder builder;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.burger_drink_add_data_layout);

		Intent intent = getIntent();
		nId = intent.getIntExtra("id",-1);
		sTitle = intent.getStringExtra("titulo");

		txtTitle = (TextView) findViewById(R.id.lblBurDriTitle);
		txtTitle.setText(sTitle);

		txtName = (TextView) findViewById(R.id.edtBurDriName);
		txtPrice = (TextView) findViewById(R.id.edtBurDriPrice);

		btnGuardar = (Button) findViewById(R.id.btnBurDriSave);
		btnCancelar = (Button) findViewById(R.id.btnBurDriCancel);

		if (nId != -1){
			loadData();
		}

		btnGuardar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (txtName.getText().toString().equals("") || txtPrice.getText().toString().equals("")){

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
					if (saveData())
						goBack();
						
					else
						showToast("Introudce el número correctamente");
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

	private void loadData(){

		if (sTitle.equals("Tipo de Burger")) {

			burgerType = BurgerTypeTable.getBurgerType(activity, nId);
			txtName.setText(burgerType.getDescription());
			txtPrice.setText(NumberFormat.getNumberInstance().format(burgerType.getPrice()));

		} else if (sTitle.equals("Tipo de Carne")) {

			burgerMeat = BurgerMeatTable.getBurgerMeat(activity, nId);
			txtName.setText(burgerMeat.getDescription());
			txtPrice.setText(NumberFormat.getNumberInstance().format(burgerMeat.getPrice()));

		} else if (sTitle.equals("Tamaño de Burger")) {

			burgerSize = BurgerSizeTable.getBurgerSize(activity, nId);
			txtName.setText(burgerSize.getDescription());
			txtPrice.setText(NumberFormat.getNumberInstance().format(burgerSize.getPrice()));

		} else if (sTitle.equals("Tipo de Bebida")) {

			drinkType = DrinkTypeTable.getDrinkType(activity, nId);
			txtName.setText(drinkType.getDescription());
			txtPrice.setText(NumberFormat.getNumberInstance().format(drinkType.getPrice()));

		}
	}

	private boolean saveData(){

		String name = txtName.getText().toString();
		double price;
		try{
			price = Double.parseDouble(txtPrice.getText().toString());


			if (sTitle.equals("Tipo de Burger")) {

				if (nId == -1){

					burgerType = new BurgerType(name,price);
					burgerType.save(activity);

				}else{

					burgerType.setId(nId);
					burgerType.setDescription(name);
					burgerType.setPrice(price);

					burgerType.save(activity);

				}

			} else if (sTitle.equals("Tipo de Carne")) {

				if (nId == -1){

					burgerMeat = new BurgerMeat(name,price);
					burgerMeat.save(activity);

				}else{

					burgerMeat.setId(nId);
					burgerMeat.setDescription(name);
					burgerMeat.setPrice(price);

					burgerMeat.save(activity);

				}

			} else if (sTitle.equals("Tamaño de Burger")) {

				if (nId == -1){

					burgerSize = new BurgerSize(name,price);
					burgerSize.save(activity);

				}else{

					burgerSize.setId(nId);
					burgerSize.setDescription(name);
					burgerSize.setPrice(price);

					burgerSize.save(activity);

				}

			} else if (sTitle.equals("Tipo de Bebida")) {

				if (nId == -1){

					drinkType = new DrinkType(name,price);
					drinkType.save(activity);

				}else{

					drinkType.setId(nId);
					drinkType.setDescription(name);
					drinkType.setPrice(price);

					drinkType.save(activity);

				}

			}

			showToast(sTitle + " guardado");
			
			return true;

		}catch (Exception e){
			return false;
		}
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
	public void newBuilder() {
		builder = new AlertDialog.Builder(this);

	}

	public void showToast(String mensaje){
		Toast.makeText(getApplicationContext(),
				mensaje, Toast.LENGTH_SHORT)
				.show();
	}

}
