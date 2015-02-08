package com.grupo5.cebancburger;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import com.grupo5.cebancburger.adapters.ExpandibleListViewAdapter;
import com.grupo5.cebancburger.viewmodels.ExpandibleListview;

public class AdminExpandableLayoutActivity extends Activity{

	SparseArray<ExpandibleListview> grupos = new SparseArray<ExpandibleListview>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_listview_main);
		crearDatos();
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.lstAdmin);
		ExpandibleListViewAdapter adapter = new ExpandibleListViewAdapter(this, grupos);
		listView.setAdapter(adapter);
	}
	public void crearDatos() {
		ExpandibleListview grupo0 = new ExpandibleListview("Usuario");
		grupo0.children.add("Usuario");
		grupo0.children.add("Cliente");
		grupos.append(0, grupo0);
		ExpandibleListview grupo1 = new ExpandibleListview("Burger");
		grupo1.children.add("Tipo de Burger");
		grupo1.children.add("Tipo de Carne");
		grupo1.children.add("Tamaño de Burger");
		grupos.append(1, grupo1);
		ExpandibleListview grupo2 = new ExpandibleListview("Bebida");
		grupo2.children.add("Tipo de Bebida");
		grupos.append(2, grupo2);
		ExpandibleListview grupo3 = new ExpandibleListview("Pedido");
		grupo3.children.add("Pedido");
		grupos.append(3, grupo3);
	}

}
