package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.MachineDTO;
import com.example.demo.dto.ProgramPrizeDTO;
import com.example.demo.modelo.ProgramPrize;
import com.example.demo.services.MachineService;

@RestController
public class MachineController {
	private final MachineService machinesService;

	public MachineController(MachineService machinesService) {
		this.machinesService = machinesService;
	}

	@GetMapping("machines/all")
	public ResponseEntity<List<MachineDTO>> getMachines() {
		return this.machinesService.getAllMachinesDTO()
				.map(machinedtolist -> new ResponseEntity<List<MachineDTO>>(machinedtolist, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("programprizes/get/all")
	public ResponseEntity<List<ProgramPrizeDTO>> getProgramPrizes() {
		return this.machinesService.getAllProgramPrizesDTO()
				.map(programprizedtolist->new ResponseEntity<List<ProgramPrizeDTO>>(programprizedtolist,HttpStatus.OK))
				.orElse(new ResponseEntity<List<ProgramPrizeDTO>>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("machines/{id}")
	public ResponseEntity<MachineDTO> getMachineById(@PathVariable long id) {
		return this.machinesService.getMachineById(id)
				.map(mach->new ResponseEntity<MachineDTO>(mach,HttpStatus.OK))
				.orElse(new ResponseEntity<MachineDTO>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("machines/addrandomprogram/{id}")
	public boolean addRandomProgramToMachine(@PathVariable long id) {
		return this.machinesService.addNewRandomProgram(id);
	}

	@PostMapping("programprizes/addifnotrepeated")
	public ResponseEntity<ProgramPrize> addProgramPrizeIfPercentageNotRepeated(@RequestBody ProgramPrize programPrize) {
		return this.machinesService.addNewProgram(programPrize)
				.map(programprize->new ResponseEntity<ProgramPrize>(programprize,HttpStatus.OK))
				.orElse(new ResponseEntity<ProgramPrize>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("programprizes/modify")
	public Boolean modifyProgramForHavingANewPrizePercentage(@RequestParam Long id, @RequestParam Float percentage) {
		return machinesService.putNewPercentage(id,percentage);
	}

	@DeleteMapping("programprizes/delete/{id}")
	public ResponseEntity<ProgramPrize> deleteProgramIfNotInUse(@PathVariable long id) {
		return machinesService.deleteProgramPrizeUnused(id)
				.map(programprize->new ResponseEntity<ProgramPrize>(programprize,HttpStatus.OK))
				.orElse(new ResponseEntity<ProgramPrize>(HttpStatus.NOT_FOUND));
	}
}
