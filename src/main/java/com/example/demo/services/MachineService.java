package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.MachineDTO;
import com.example.demo.dto.ProgramPrizeDTO;
import com.example.demo.mapper.Machine2MachineDTO;
import com.example.demo.mapper.ProgramPrize2ProgramPrizeDTO;
import com.example.demo.modelo.ProgramPrize;
import com.example.demo.objectmother.ObjectMother;

@Service
public class MachineService {
	private final ObjectMother objectMother;

	public MachineService() {
		try {
			this.objectMother = new ObjectMother();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Optional<List<MachineDTO>> getAllMachinesDTO() {
		return Optional.of(this.objectMother.getMachines().stream()
				.map(machine -> new Machine2MachineDTO().map(machine)).toList());
	}

	public Optional<List<ProgramPrizeDTO>> getAllProgramPrizesDTO() {
		return Optional.of(this.objectMother.getProgramPrizes().stream()
				.map(programPrize -> new ProgramPrize2ProgramPrizeDTO().map(programPrize)).toList());
	}

	public Optional<MachineDTO> getMachineById(long id) {
		return Optional.of(this.objectMother.getMachines().stream().filter(machine -> machine.getId().equals(id))
				.findFirst().map(machine ->new Machine2MachineDTO().map(machine)).orElseThrow());
	}

	public Boolean addNewRandomProgram(long id) {
		return objectMother.addRandomProgramPrize(id);
	}

	public Optional<ProgramPrize> addNewProgram(ProgramPrize programPrizeIn) {
		if(!objectMother.getProgramPrizes().contains(programPrizeIn)) {
			objectMother.addProgramPrize(programPrizeIn);
			return Optional.of(programPrizeIn);
		}
		return Optional.empty();
	}

	public boolean putNewPercentage(long id, float prizePercentage) {
		return objectMother.putNewPercentage(id, prizePercentage);
	}

	public Optional<ProgramPrize> deleteProgramPrizeUnused(long id) {
		return Optional.of(objectMother.delete(objectMother.getProgramPrizes().stream().filter(programaprize->programaprize.getId()==id&&programaprize.getHistorical().size()==0).findFirst()));
	}
}
