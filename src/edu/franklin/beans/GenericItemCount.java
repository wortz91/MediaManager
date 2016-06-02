package edu.franklin.beans;

import java.io.Serializable;

public class GenericItemCount implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ID;
    String description;
    int count;

    public GenericItemCount() {

    }

    public int getID() {
        return this.ID;
    }

    public void setID(int iD) {
        this.ID = iD;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
