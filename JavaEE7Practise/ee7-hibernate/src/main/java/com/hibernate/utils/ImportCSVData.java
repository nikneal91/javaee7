package com.hibernate.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.hibernate.entity.EventAnimal;
import com.hibernate.entity.PetAnimal;

public abstract class ImportCSVData<T> {

	
	
	public File init(String filename) {
		String path = this.getClass().getClassLoader().getResource(filename).getPath();
		File f = new File(path);
		return f;
	}

	
	
	
	public List<T> load(File f,String delimeter) {
		List<T> animals = new ArrayList<T>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			try {
				String line = br.readLine();
				System.out.println("read " +line);
				while(line!=null) {
					T animal = null;
					if ( null != delimeter) {
						animal = returnFromLine(line, delimeter);
					}else {
						animal = returnFromLine(line);
					}
					animals.add(animal);
				
				line=br.readLine();
				System.out.println("reading " +line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return animals;
	}

	
	protected abstract T returnFromLine(String line);
	
	protected abstract T returnFromLine(String line,String delimeter);
	
	
}
