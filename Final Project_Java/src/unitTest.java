import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class unitTest {

	@Test
	void test() {
		covid_simulation test = new covid_simulation("virus_1.config");
		int a11 = test.area1.getSize();
		int a12 = test.area1.getSizeII();
		int a13 = test.area1.getSizeIII();
		int a14 = test.area1.getSizeV();
		
		assertTrue(a11>0);
		assertTrue(a12>0);
		assertTrue(a13>=0);
		assertTrue(a14>0);
		
		
	}
	@Test
	void test2() {
		covid_simulation test1 = new covid_simulation("virus_1.config");
		System.out.println(test1.area1.getSizeV()+test1.area2.getSizeV()+test1.area3.getSizeV()+test1.area4.getSizeV());
		int num = 0;
		for(person a: test1.area1.getAll()) {
			if(a.getStatus()=="infected") {
				num++;
			}
		}
		for(person a: test1.area2.getAll()) {
			if(a.getStatus()=="infected") {
				num++;
			}
		}
		for(person a: test1.area3.getAll()) {
			if(a.getStatus()=="infected") {
				num++;
			}
		}
		for(person a: test1.area4.getAll()) {
			if(a.getStatus()=="infected") {
				num++;
			}
		}
		System.out.println("Number" + num);
		assertTrue(num>0);

	}
	
	@Test
	void test3() {
		covid_simulation test1 = new covid_simulation("virus_1.config");
		System.out.println(test1.area1.getSizeV()+test1.area2.getSizeV()+test1.area3.getSizeV()+test1.area4.getSizeV());
		int num = 0;
		for(person a: test1.area1.getAll()) {
			if(a.getStatus()=="vaccined") {
				num++;
			}
		}
		for(person a: test1.area2.getAll()) {
			if(a.getStatus()=="vaccined") {
				num++;
			}
		}
		for(person a: test1.area3.getAll()) {
			if(a.getStatus()=="vaccined") {
				num++;
			}
		}
		for(person a: test1.area4.getAll()) {
			if(a.getStatus()=="vaccined") {
				num++;
			}
		}
		System.out.println("Number" + num);
		assertTrue(num>0);
	}
	
	@Test
	void test4() {
		location test = new location("Boston");
		person person1 = new person(0);
		person person2 = new person(1);
		person person3 = new person(2);
		
		test.add(person1);
		test.add(person3);
		
		System.out.print("add function" + person1);
		assertTrue(test.getSize()==2);
		
		person2.changeStatus("infected");
		person2.changeQuarantined();
		test.add(person2);
		test.quaAdd(person2);
		
		List<person> ga = test.getAll();
		person gi = test.getInfector();
		List<person> is = test.getInfectors();
		String gn = test.getName();
		List<person> gq = test.getQua();

		assertTrue(ga.size()==3);
		assertTrue(test.getInfector() == person2);
		assertTrue(gq.size()==1);
		assertTrue(test.getQua().get(0) == person2);
	}
	


	@Test
	void test5 () {
		read_config test = new read_config("src/virus_1.config");
		int pop = test.getPop();
		int inf = test.getInf();
		String name = test.getName();
		int r = test.getR();
		Double uom = test.getUom();
		Double eom = test.getEom();
		int pot = test.getPot();
		Double ct = test.getCt();
		int av = test.getAv();
		Double ev = test.getEv();
		Double bar = test.getBarriers();
		int rr = test.getRr();
		int dov = test.getDateofv();
		
		assertEquals(pop, 100000);
		assertEquals(inf, 5);
		assertEquals(name, "covid-19");
		assertEquals(r, 3);
		assertEquals(uom, 0.95);
		assertEquals(eom, 0.99);
		assertEquals(pot, 1500);
		assertEquals(ct, 0.005);
		assertEquals(av, 300);
		assertEquals(ev, 0.99999);
		assertEquals(bar, 0.95);
		assertEquals(rr, 89);
		assertEquals(dov, 200);
	}
}
