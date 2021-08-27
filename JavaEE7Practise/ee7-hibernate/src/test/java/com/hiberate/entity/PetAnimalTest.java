package com.hiberate.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.hibernate.entity.EventAnimal;
import com.hibernate.entity.PetAnimal;
import com.hibernate.utils.ImportCSVAnimalData;
import com.hibernate.utils.ImportCSVData;
import com.hibernate.utils.ImportCSVEventData;

public class PetAnimalTest {

	private static SessionFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new Configuration().configure().buildSessionFactory();

	}

	@AfterClass
	public static void tearDown() throws Exception {
		factory.close();
	}

	@Test
	@Ignore
	public void test() {
		Session session = factory.openSession();
		System.out.println("session details " + session.getStatistics());
		org.hibernate.Transaction tx = session.beginTransaction();
		PetAnimal animal = new PetAnimal();
		animal.setId(1);
		animal.setBirth(new Date());
		animal.setDeath(new Date());
		animal.setName("sheerly");
		animal.setOwner("nikhil");
		animal.setSex("Male");
		animal.setSpecies("CAT");
		session.persist(animal);
		
		tx.commit();
		System.out.println("local save " + animal);
		
		// fail("Not yet implemented");
		session.close();
	}

	@Test
	public void testCsvPet() {
		ImportCSVData<PetAnimal> data = new ImportCSVAnimalData();
		//System.out.println("final arraylist" + Arrays.toString((data.load(data.init())).toArray()) );
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		List<PetAnimal> animals = data.load(data.init("pet.txt"), null);
		System.out.println("Size :: " + animals.size());
		for (PetAnimal animal : animals) {
			System.out.println("saving " + animal);
			session.persist(animal);
		}
		tx.commit();
		session.close();
	}
	
	
	@Test
	@Ignore
	public void testCsvEvent() {
		ImportCSVData<EventAnimal> data = new ImportCSVEventData();
		//System.out.println("final arraylist" + Arrays.toString((data.load(data.init())).toArray()) );
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		List<EventAnimal> animals = data.load(data.init("event.txt"), "\\t");
		System.out.println("Size :: " + animals.size());
		for (EventAnimal animal : animals) {
			System.out.println("saving " + animal);
			session.persist(animal);
		}
		tx.commit();
		session.close();
	}
	
}
