package com.example.demo.objectmother;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Machine;
import com.example.demo.modelo.PeriodOfUse;
import com.example.demo.modelo.ProgramPrize;

/**
 * Este OM es semialeatorio por un lado siempre teneis siete maquinas
 * cada máquina cuenta siempre con una historia de cinco programas de premios
 * Los programas de premios asignados son aleatorios.
 * El total recaudado por la maquina es aleatorio entre u minimo y un maximo
 * El ultimo programa de la lista de programas no será asignado a ningun historial
 *
 * @author estar
 *
 */
public class ObjectMother {

	private List<ProgramPrize> programPrizes;
	private List<Machine> machines;
	private int periodMinimumInDays=45;

	public ObjectMother() throws Exception {
		super();
		programPrizes = getProgramPrizesOM();
		machines=getMachinesOM();
	}

	private List<ProgramPrize> getProgramPrizesOM() {
		Long id;
		float PrizePercentage;
		int amount = 10;
		ArrayList<ProgramPrize> programPrizes = new ArrayList<ProgramPrize>();
		for (int i = 0; i < amount; i++) {
			programPrizes.add(new ProgramPrize(i + 1L));
		}

		return programPrizes;
	}

	private List<Machine> getMachinesOM() throws Exception {
		Long id;
		String[] name = { "saturno", "piratas del caribe", "hole in the ground", "salvados", "lost in paradise",
				"culmantur", "seyemi" };
		int year, month, day;
		LocalDate initalDate = LocalDate.of(1999, 1, 1);
		ArrayList<Machine> machines = new ArrayList<Machine>();
		for (int i = 0; i < name.length; i++) {
			Machine machine = new Machine(i + 1L, name[i]);
			machines.add(machine);
			//Todas las maquinas han usado cinco programas en su historia hasta el momento
			int programs = 5;
			for (int j = 0; j < programs; j++) {
				ProgramPrize randomProgramPrize = getRandomProgramPrize();
				randomProgramPrize.addHistorical(machine);
				LocalDate finalDate = caculateFinalDate(initalDate);
				machine.addMoment(new PeriodOfUse(randomProgramPrize, initalDate, finalDate));
				initalDate = finalDate;
			}
			float minimumRaised=15000;
			float maximumRaised=300000;
			machine.setTotalRaised(new MyRandom().nextFloat(minimumRaised,maximumRaised));

		}
		return machines;
	}

	public boolean addRandomProgramPrize(long id){
				Optional<Machine> machina=getMachines().stream()
				.filter(machine->machine.getId()==id).findFirst();
				if(machina.isPresent()) {
					Machine machine = machina.get();
					LocalDate newInicitalDate=machine.getLastPeriod().getFinalDate();
					machine.addMoment(new PeriodOfUse(getRandomProgramPrize(),newInicitalDate,newInicitalDate.plusDays(periodMinimumInDays)));
					return true;
				}
			return false;
	}
	private LocalDate caculateFinalDate(LocalDate initalDate) {
		return initalDate.plusDays(periodMinimumInDays);
	}

	private ProgramPrize getRandomProgramPrize() {
		//Para que siempre haya un programprize sin machine
		return programPrizes.get(new Random().nextInt(programPrizes.size()-1));
	}

	public List<ProgramPrize> getProgramPrizes() {
		return programPrizes;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void addProgramPrize(ProgramPrize programPrize){
		this.programPrizes.add(programPrize);
	}

	public boolean putNewPercentage(long id, float prizePercentage) {
		Optional<ProgramPrize> algo=programPrizes.stream().filter(prpz->prpz.getId()==id).findFirst();
		if(algo.isPresent()) {
			algo.get().setPrizePercentage(prizePercentage);
			return true;
		}
		return false;
	}

	public ProgramPrize delete(Optional<ProgramPrize> findFirst) {
		if(findFirst.isPresent()) {
			ProgramPrize o = findFirst.get();
			this.programPrizes.remove(o);
			return o;
		}
		return null;
	}

}
