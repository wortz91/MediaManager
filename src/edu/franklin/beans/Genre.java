package edu.franklin.beans;

import java.io.Serializable;

public class Genre implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8730483355855052236L;
	private int genreID;
    private String genreDescription;

    public Genre() {

    }

    public int getGenreID() {
        return this.genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenreDescription() {
        return this.genreDescription;
    }

    public void setGenreDescription(String genre) {
        this.genreDescription = genre;
    }


}
