package com.recipe_browsing_history.model;

public class Recipe_browing_historyVO {
	 private String member_id;

    private String recipe_id;

	
	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getRecipe_id() {
		return recipe_id;
	}


	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}


	public Recipe_browing_historyVO(String member_id, String recipe_id) {
		super();
		this.member_id = member_id;
		this.recipe_id = recipe_id;
	}


	public Recipe_browing_historyVO() {
		super();
	}


	@Override
	public String toString() {
		return "Recipe_browing_historyVO [member_id=" + member_id + ", recipe_id=" + recipe_id + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
		result = prime * result + ((recipe_id == null) ? 0 : recipe_id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe_browing_historyVO other = (Recipe_browing_historyVO) obj;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		if (recipe_id == null) {
			if (other.recipe_id != null)
				return false;
		} else if (!recipe_id.equals(other.recipe_id))
			return false;
		return true;
	}

	


}
