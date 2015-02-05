package com.grupo5.cebancburger.adapters;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;


import com.grupo5.cebancburger.R;
import com.grupo5.cebancburger.viewmodels.ExpandibleListview;

public class ExpandibleListViewAdapter extends BaseExpandableListAdapter{

	private final SparseArray<ExpandibleListview> grupos;
	public LayoutInflater inflater;
	public Activity activity;
	// Constructor
	public ExpandibleListViewAdapter(Activity act, SparseArray<ExpandibleListview> grupos) {
		activity = act;
		this.grupos = grupos;
		inflater = act.getLayoutInflater();
	}
	// Nos devuelve los datos asociados a un subitem en base
	// a la posici�n
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return grupos.get(groupPosition).children.get(childPosition);
	}
	// Devuelve el id de un item o subitem en base a la
	// posici�n de item y subitem
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}
	// En base a la posici�n del item y de subitem nos devuelve
	// el objeto view correspondiente y el layout para los subitems
	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final String children = (String) getChild(groupPosition, childPosition);
		TextView textvw = null;
		TextView txtItem = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.expandable_layout_subitem, null);
		}
		textvw = (TextView) convertView.findViewById(R.id.textView1);
		textvw.setText(children); 
		switch (groupPosition){
		case 0:
			switch (childPosition){
			case 0:
				textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.subgrupo, 0, 0, 0);
				break;
			case 1:
				textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.subgrupo, 0, 0, 0);
				break;
			}
			break;
		case 1:
			switch (childPosition){
			case 0:
				textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.subgrupo, 0, 0, 0);
				break;
			case 1:
				textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.subgrupo, 0, 0, 0);
				break;
			case 2:
				textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.subgrupo, 0, 0, 0);
				break;
			}
			break;
		case 2:
			textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.subgrupo, 0, 0, 0);
			break;
		}
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}
	// Nos devuelve la cantidad de subitems que tiene un �tem
	@Override
	public int getChildrenCount(int groupPosition) {
		return grupos.get(groupPosition).children.size();
	}
	//Los datos de un �tem especificado por groupPosition
	@Override
	public Object getGroup(int groupPosition) {
		return grupos.get(groupPosition);
	}
	//La cantidad de �tem que tenemos definidos
	@Override
	public int getGroupCount() {
		return grupos.size();
	}
	//M�todo que se invoca al contraer un �tem
	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}
	//M�todo que se invoca al expandir un �tem
	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}
	//Devuelve el id de un �tem
	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}
	//Obtenemos el layout para los �tems
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.expandable_listview_item, null);
		}
		ExpandibleListview grupo = (ExpandibleListview) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(grupo.string);
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}
	@Override
	public boolean hasStableIds() {
		return false;
	}
	//Nos informa si es seleccionable o no un �tem o subitem
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}
