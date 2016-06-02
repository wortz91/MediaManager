package edu.franklin.beans;

import java.util.ArrayList;

public class MediaItems {
	String searchCriteria = "";
	
	ArrayList<MediaItem> mediaItemList = new ArrayList<>();

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public ArrayList<MediaItem> getMediaItemList() {
		return mediaItemList;
	}

	public void setMediaItemList(ArrayList<MediaItem> mediaItemList) {
		this.mediaItemList = mediaItemList;
	}
	
	
}
