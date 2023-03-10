package com.example.demo.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PeriodOfUse implements Serializable {
	private ProgramPrize programPrize;
    private LocalDate initialDate;
    private LocalDate finalDate;
    
	public PeriodOfUse(ProgramPrize programPrize, LocalDate initialDate, LocalDate finalDate) {
		super();
		this.programPrize = programPrize;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}

	public ProgramPrize getProgramPrize() {
		return programPrize;
	}

	public void setProgramPrize(ProgramPrize programPrize) {
		this.programPrize = programPrize;
	}

	public LocalDate getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(LocalDate initialDate) {
		this.initialDate = initialDate;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(finalDate, initialDate, programPrize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodOfUse other = (PeriodOfUse) obj;
		return Objects.equals(finalDate, other.finalDate) && Objects.equals(initialDate, other.initialDate)
				&& Objects.equals(programPrize, other.programPrize);
	}

	public boolean isLater(PeriodOfUse periodOfUse) {
		return periodOfUse.initialDate.isAfter(this.finalDate)||periodOfUse.initialDate.isEqual(this.finalDate);
	}

}
