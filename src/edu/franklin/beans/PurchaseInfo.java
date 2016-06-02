package edu.franklin.beans;

public class PurchaseInfo {
    private int purchaseID;
    private String purchaseLocation;

    public PurchaseInfo() {

    }

    public int getPurchaseID() {
        return this.purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getPurchaseLocation() {
        return this.purchaseLocation;
    }

    public void setPurchaseLocation(String purchaseLocation) {
        this.purchaseLocation = purchaseLocation;
    }
}
