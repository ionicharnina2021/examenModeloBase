package com.example.demo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Machine {
	private Long id;
	private String name;
	public List<PeriodOfUse> history = new ArrayList<PeriodOfUse>();
	public float totalRaised = 0;

	public Machine(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public void addMoment(PeriodOfUse moment) {
		if(history.size()==0 || moment.isLater(history.get(history.size()-1))) {
			history.add(moment);
		}
	}

	public LocalDate getFinalDayOfLastPeriod() {
		return getLastPeriod().getFinalDate();
	}
	public PeriodOfUse getLastPeriod() {
		return history.get(history.size() - 1);
	}

	public int getAmountOfPrograms() {
		return history.size();
	}

	public float getTotalRaised() {
		return totalRaised;
	}

	public void setTotalRaised(float totalRaised) {
		this.totalRaised = totalRaised;
	}

	public Long getId() {
		return id;
	}

	public String getName(){
		return this.name;
	}

}
