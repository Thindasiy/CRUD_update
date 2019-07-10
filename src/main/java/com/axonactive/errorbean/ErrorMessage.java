package com.axonactive.errorbean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private int errorCode;
	private String errorMessages;
	private String timeStamp;

	public ErrorMessage() {
	}

	public ErrorMessage(int errorCode, String errorMessage, String timeStamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessages = errorMessage;
		this.timeStamp = timeStamp;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void seterrorMessage(String errorMessage) {
		this.errorMessages = errorMessage;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	

}
