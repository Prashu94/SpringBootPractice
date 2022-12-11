package com.infosys.utility;

import com.infosys.dto.DesktopDTO;
import com.infosys.dto.TraineeDTO;
import com.infosys.entity.Desktop;
import com.infosys.entity.Trainee;

public class Converter {
	
	public static TraineeDTO convertTraineeEntityToDTO(Trainee trainee) {
		TraineeDTO traineeDTO = new TraineeDTO();
		traineeDTO.setTraineeId(trainee.getTraineeId());
		traineeDTO.setTraineeName(trainee.getTraineeName());
		if(trainee.getDesktop()!=null) {
			traineeDTO.setDesktop(convertDesktopEntityToDTO(trainee.getDesktop()));
		}
		return traineeDTO;
	}
	
	public static DesktopDTO convertDesktopEntityToDTO(Desktop desktop) {
		DesktopDTO desktopDTO = new DesktopDTO();
		desktopDTO.setDesktopId(desktop.getDesktopId());
		desktopDTO.setDesktopMake(desktop.getDesktopMake());
		desktopDTO.setDesktopModel(desktop.getDesktopModel());
		desktopDTO.setDesktopStatus(desktop.getDesktopStatus());
		return desktopDTO;
	}
	
	public static Trainee convertTraineeDTOToTrainee(TraineeDTO traineeDTO) {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(traineeDTO.getTraineeId());
		trainee.setTraineeName(traineeDTO.getTraineeName());
		if(trainee.getDesktop()!=null) {
			trainee.setDesktop(convertDesktopDTOToDesktop(traineeDTO.getDesktop()));
		}
		return trainee;
	}
	
	public static Desktop convertDesktopDTOToDesktop(DesktopDTO desktopDTO) {
		Desktop desktop = new Desktop();
		desktop.setDesktopId(desktopDTO.getDesktopId());
		desktop.setDesktopMake(desktopDTO.getDesktopMake());
		desktop.setDesktopModel(desktopDTO.getDesktopModel());
		desktop.setDesktopStatus(desktopDTO.getDesktopStatus());
		return desktop;
	}
}
