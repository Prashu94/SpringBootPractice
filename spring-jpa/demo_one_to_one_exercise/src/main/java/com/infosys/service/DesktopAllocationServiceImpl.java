package com.infosys.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.DemoOneToOneExerciseApplication;
import com.infosys.dto.DesktopDTO;
import com.infosys.dto.DesktopStatus;
import com.infosys.dto.TraineeDTO;
import com.infosys.entity.Desktop;
import com.infosys.entity.Trainee;
import com.infosys.exception.InfyTrainingException;
import com.infosys.repository.DesktopRepository;
import com.infosys.repository.TraineeRepository;
import com.infosys.utility.Converter;


@Service(value = "desktopAllocationService")
@Transactional
public class DesktopAllocationServiceImpl implements DesktopAllocationService{
	public static final Log LOGGER = LogFactory.getLog(DemoOneToOneExerciseApplication.class);
	@Autowired
	private TraineeRepository traineeRepository;
	
	@Autowired
	private DesktopRepository desktopRepository;
	
	@Override
	public TraineeDTO getTraineeDetails(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRepository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		
		TraineeDTO traineeDTO = Converter.convertTraineeEntityToDTO(trainee);
		return traineeDTO;
	}

	@Override
	public DesktopDTO getDesktopDetails(String desktopId) throws InfyTrainingException {
		Optional<Desktop> optional = desktopRepository.findById(desktopId);
		Desktop desktop = optional.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
		
		DesktopDTO desktopDTO = Converter.convertDesktopEntityToDTO(desktop);
		return desktopDTO;
	}

	@Override
	public Integer addTrainee(TraineeDTO traineeDTO) throws InfyTrainingException {
		Trainee trainee = Converter.convertTraineeDTOToTrainee(traineeDTO);
		traineeRepository.save(trainee);
		return trainee.getTraineeId();
	}

	@Override
	public void allocateDesktop(Integer traineeId, String desktopId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRepository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		
		Optional<Desktop> optional1 = desktopRepository.findById(desktopId);
		Desktop desktop = optional1.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
		
		// Check if the desktop is already allocated to this trainee or another trainee
		if(trainee.getDesktop()!=null) {
			if(trainee.getDesktop().getDesktopId().equals(desktopId)) {
				throw new InfyTrainingException("Service.TRAINEE_DESKTOP_FOUND");
			}
		}else if(desktop.getDesktopStatus().equals(DesktopStatus.AVAILABLE)) {
			desktop.setDesktopStatus(DesktopStatus.ALLOCATED);
			trainee.setDesktop(desktop);
		}
		
		traineeRepository.save(trainee);
	}

	@Override
	public void deallocateDesktop(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRepository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		
		if(trainee.getDesktop()!=null) {
			trainee.getDesktop().setDesktopStatus(DesktopStatus.AVAILABLE);
			trainee.setDesktop(null);
		}else {
			new InfyTrainingException("Service.DESKTOP_NOT_ALLOCATED");
		}
		
		traineeRepository.save(trainee);
	}

	@Override
	public void deleteTrainee(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRepository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		if(trainee.getDesktop()!=null) {
			trainee.getDesktop().setDesktopStatus(DesktopStatus.AVAILABLE);
			trainee.setDesktop(null);
		}else {
			new InfyTrainingException("Service.DESKTOP_NOT_ALLOCATED");
		}
		
		traineeRepository.delete(trainee);
	}
	
}
