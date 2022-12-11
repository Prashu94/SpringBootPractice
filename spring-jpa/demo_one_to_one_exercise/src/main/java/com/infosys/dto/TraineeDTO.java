package com.infosys.dto;

public class TraineeDTO {
	
	private Integer traineeId;
	private String traineeName;
	private DesktopDTO desktop;
	
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	public String getTraineeName() {
		return traineeName;
	}
	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}
	public DesktopDTO getDesktop() {
		return desktop;
	}
	public void setDesktop(DesktopDTO desktop) {
		this.desktop = desktop;
	}
	
	@Override
	public String toString() {
		return "TraineeDTO [traineeId=" + traineeId + ", traineeName=" + traineeName + ", desktop=" + desktop + "]";
	}
	
	
	
}
