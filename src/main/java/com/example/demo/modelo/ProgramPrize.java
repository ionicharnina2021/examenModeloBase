package com.example.demo.modelo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.objectmother.MyRandom;

public class ProgramPrize {
	private Long id;
	private float PrizePercentage;
	private List<Machine> historical = new ArrayList<>();

	public ProgramPrize() {
	}

	/*
	 * public ProgramPrize(Long id, float prizePercentage, List<Machine> historical)
	 * { this.id = id; PrizePercentage = prizePercentage; this.historical =
	 * historical; }
	 */

	public ProgramPrize(Long id) {
		super();
		this.id = id;
		int minimunPercentage = 10, maximumPercentage = 30;
		this.PrizePercentage = new MyRandom().nextFloat(minimunPercentage, maximumPercentage);
	}

	public void addHistorical(Machine machine) {
		historical.add(machine);

	}

	public float getPrizePercentage() {
		return PrizePercentage;
	}

	public void setPrizePercentage(float prizePercentage) {
		PrizePercentage = prizePercentage;
	}

	public List<Machine> getHistorical() {
		return historical;
	}

	/**
	 * GET ID FROM ProgramPrize
	 * 
	 * @author Fran
	 */
	public long getId() {
		return this.id;
	}

}