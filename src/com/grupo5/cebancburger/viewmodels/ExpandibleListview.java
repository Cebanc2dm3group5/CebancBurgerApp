package com.grupo5.cebancburger.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class ExpandibleListview {
	
	public String string;
	public final List<String> children = new ArrayList<String>();
	public ExpandibleListview(String string) {
		this.string = string;
	}
}
