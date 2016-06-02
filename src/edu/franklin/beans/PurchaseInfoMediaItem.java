package edu.franklin.beans;

import java.sql.Date;

public class PurchaseInfoMediaItem {
    private MediaItem mediaItem;
    private int id;
    private double purchasePrice;
    private Date purchaseDate;
    private int purchaseInfoID;
    private int mediaItemID;

    public PurchaseInfoMediaItem() {

    }

    public MediaItem getMediaItem() {
        return this.mediaItem;
    }


    public void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getPurchaseInfoID() {
        return this.purchaseInfoID;
    }

    public void setPurchaseInfoID(int purchaseInfoID) {
        this.purchaseInfoID = purchaseInfoID;
    }

    public int getMediaItemID() {
        return this.mediaItemID;
    }

    public void setMediaItemID(int mediaItemID) {
        this.mediaItemID = mediaItemID;
    }
}
