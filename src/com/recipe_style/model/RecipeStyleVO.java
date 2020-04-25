package com.recipe_style.model;

import java.io.Serializable;

public class RecipeStyleVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rcstyle_no;
	private String rcstyle;
	public String getRcstyle_no() {
		return rcstyle_no;
	}
	public void setRcstyle_no(String rcstyle_no) {
		this.rcstyle_no = rcstyle_no;
	}
	public String getRcstyle() {
		return rcstyle;
	}
	public void setRcstyle(String rcstyle) {
		this.rcstyle = rcstyle;
	}
	@Override
	public String toString() {
		return "RecipeStyleVO [rcstyle_no=" + rcstyle_no + ", rcstyle=" + rcstyle + "]";
	}
}
