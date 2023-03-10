package com.example.demo.mapper;

import com.example.demo.dto.MachineDTO;
import com.example.demo.modelo.Machine;

public class Machine2MachineDTO {
public MachineDTO map(Machine machine) {
	return new MachineDTO(machine.getName(),machine.getTotalRaised());
}
}
