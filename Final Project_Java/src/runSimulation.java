

public class runSimulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		covid_simulation test1 = new covid_simulation("virus_2.config");
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
		System.out.println(num);
		
//		covid_simulation test2 = new covid_simulation("virus_2.config");
	}

}
