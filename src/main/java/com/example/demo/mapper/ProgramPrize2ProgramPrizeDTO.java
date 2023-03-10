package com.example.demo.mapper;

import com.example.demo.dto.ProgramPrizeDTO;
import com.example.demo.modelo.ProgramPrize;

public class ProgramPrize2ProgramPrizeDTO {
public ProgramPrizeDTO map(ProgramPrize programPrize) {
	return new ProgramPrizeDTO(programPrize.getId(),programPrize.getPrizePercentage(),programPrize.getHistorical().size());
}
}
