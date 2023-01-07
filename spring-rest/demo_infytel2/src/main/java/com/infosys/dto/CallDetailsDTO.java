package com.infosys.dto;

import java.time.LocalDate;

public class CallDetailsDTO {
	
	private long calledBy;
	private long calledTo;
	private LocalDate calledOn;
	private int duration;
	public long getCalledBy() {
		return calledBy;
	}
	public void setCalledBy(long calledBy) {
		this.calledBy = calledBy;
	}
	public long getCalledTo() {
		return calledTo;
	}
	public void setCalledTo(long calledTo) {
		this.calledTo = calledTo;
	}
	public LocalDate getCalledOn() {
		return calledOn;
	}
	public void setCalledOn(LocalDate calledOn) {
		this.calledOn = calledOn;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "CallDetailsDTO [calledBy=" + calledBy + ", calledTo=" + calledTo + ", calledOn=" + calledOn
				+ ", duration=" + duration + "]";
	}
	
	
}
