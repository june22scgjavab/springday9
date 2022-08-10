package com.example.utility;

import java.time.LocalDateTime;

public class ErrorInfo {
private String errorMessage;
private Integer errorNo;
private LocalDateTime timeStamp;
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
public Integer getErrorNo() {
	return errorNo;
}
public void setErrorNo(Integer errorNo) {
	this.errorNo = errorNo;
}
public LocalDateTime getTimeStamp() {
	return timeStamp;
}
public void setTimeStamp(LocalDateTime timeStamp) {
	this.timeStamp = timeStamp;
}

}
