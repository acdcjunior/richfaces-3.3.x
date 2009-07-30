package org.richfaces.demo.extendeddatamodel;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class AuctionItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -775342254945959655L;
	private Integer pk;
	private String description;
	private Double bid;
	private Double highestBid;
	private Integer qtyRequested;
	private Integer qtyAvialable;
	private boolean won = false;
	private boolean loose = false;
	private Double amount;
	
	@SuppressWarnings("unused")
	private AuctionItem() {};
	
	public AuctionItem(Integer pk) {
		this.pk = pk;
	}
	
	public Integer getPk() {
		return pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getBid() {
		return bid;
	}
	public void setBid(Double bid) {
		this.bid = bid;
	}
	public Double getHighestBid() {
		return highestBid;
	}
	public void setHighestBid(Double highestBid) {
		this.highestBid = highestBid;
	}
	public Integer getQtyRequested() {
		return qtyRequested;
	}
	public void setQtyRequested(Integer qty) {
		this.qtyRequested = qty;
	}
	public Double getAmount() {
		return this.amount;
	}
	public Integer getQtyAvialable() {
		return qtyAvialable;
	}
	public void setQtyAvialable(Integer qtyAvialable) {
		this.qtyAvialable = qtyAvialable;
	}
	public void placeBid(ActionEvent event) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (bid!=null) {
			if (bid<=highestBid) {
				ctx.addMessage(event.getComponent().getClientId(ctx), new FacesMessage(FacesMessage.SEVERITY_WARN,"Bid amount is lower that highest bid","You need to bid amount higher that highest bid"));
				won = false;
				loose = true;
			} else {
				highestBid = bid;
				won = true;
				loose = false;
				amount = bid;
			}
		} else {
			ctx.addMessage(event.getComponent().getClientId(ctx), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Bid value is missing","You must provide bid value"));
		}
	}
}
