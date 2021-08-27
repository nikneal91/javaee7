package com.hibernate.utils;

import java.util.Arrays;
import java.util.Date;

import com.hibernate.entity.EventAnimal;

public class ImportCSVEventData extends ImportCSVData<EventAnimal>{

	private String defaultValue(String value) {
		return null==value ? "undef":value;
	}
	
	
	protected EventAnimal returnFromLine(String line) {
		return returnFromLine(line, "\\s+");
	}


	@Override
	protected EventAnimal returnFromLine(String line, String delimeter) {
		EventAnimal event = new EventAnimal();
		String[] tokens = line.split(delimeter);
		
		event.setName(defaultValue(tokens[0]));
		event.setDate(new Date());
		event.setType(defaultValue(tokens[2]));
		if(tokens.length>3) {
			event.setRemarks(defaultValue(tokens[3]));
		} else {
			event.setRemarks("undef");	
		}
		System.out.println(Arrays.toString(tokens));
		return event;
	}
	
}
