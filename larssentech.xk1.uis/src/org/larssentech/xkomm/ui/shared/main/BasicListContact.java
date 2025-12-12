package org.larssentech.xkomm.ui.shared.main;

public class BasicListContact {

	private String cleanString;
	private String markedString;

	private boolean isGood;

	private final String token = "+";

	public String getCleanString() {

		return this.cleanString;
	}

	public String toString() {

		return this.markedString;
	}

	public void setCleanString(String cleanString) {

		this.cleanString = cleanString;
		this.isGood = true;
	}

	public String getMarkedString() {

		return this.markedString;
	}

	public void mark() {

		this.markedString = this.token + this.getCleanString();
	}

	public void setMarkedString(String markedString) {

		this.markedString = markedString;
	}

	public String getToken() {

		return this.token;
	}

	public boolean isGood() {

		return this.isGood;
	}

	public void setGood(boolean isGood) {

		this.isGood = isGood;
	}

}
