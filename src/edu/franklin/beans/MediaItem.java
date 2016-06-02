package edu.franklin.beans;

import java.io.Serializable;

public class MediaItem implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
    private int genreID;
    private int mediaTypeID;
    private String name;
    private int year;
    private String comments;
    private double currentValue;
    private String genreDescription;
    private String mediaTypeDescription;

    public MediaItem() {

    }

    public int getID() {
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getGenreID() {
        return this.genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public int getMediaTypeID() {
        return this.mediaTypeID;
    }

    public void setMediaTypeID(int mediaID) {
        this.mediaTypeID = mediaID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getCurrentValue() {
        return this.currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public String getGenreDescription() {
        return this.genreDescription;
    }

    public void setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
    }

    public String getMediaTypeDescription() {
        return this.mediaTypeDescription;
    }

    public void setMediaTypeDescription(String mediaTypeDescription) {
        this.mediaTypeDescription = mediaTypeDescription;
    }
    
    public int compareTo(Object object) {
    	try {
			boolean matches = (this.name.equals(((MediaItem) object).name));
			
			System.out.println("this.name:" + this.name.toString() + ", o1.name:" + ((MediaItem)object).name.toString() + " are the same?" + matches);
			
			if (matches) {
			    return 0;
			} else {
			    return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return -1;
		}
    }

}
