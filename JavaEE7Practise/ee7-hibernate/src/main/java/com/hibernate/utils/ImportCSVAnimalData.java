package com.hibernate.utils;

import java.util.Arrays;
import java.util.Date;

import com.hibernate.entity.PetAnimal;

public class ImportCSVAnimalData extends ImportCSVData<PetAnimal> {

	
	public PetAnimal returnFromLine(String line) {
		return returnFromLine(line, "\\s+");
	}

	@Override
	protected PetAnimal returnFromLine(String line, String delimeter) {
		PetAnimal animal = new PetAnimal();
		String[] tokens = line.split(delimeter);
		animal.setBirth(new Date());
		animal.setDeath(new Date());
		animal.setName(tokens[0]);
		animal.setOwner(tokens[1]);
		animal.setSpecies(tokens[2]);
		if(tokens[3].length()==1) {
			animal.setSex(tokens[3]);
		} else {
			animal.setSex("u");
		}
		System.out.println(Arrays.toString(tokens));

		return animal;

	
	}

	
	
}
