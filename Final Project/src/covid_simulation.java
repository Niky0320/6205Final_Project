import java.util.Scanner;

public class covid_simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		read_config virus1 = new read_config();
		virus1.read_config("src/virus_1.config");
		
		String name = virus1.getName();
		Double k = virus1.getK();
		Double r = virus1.getR();
		Double uom = virus1.getUom();
		Double eom = virus1.getEom();
		Double pot = virus1.getPot();
		Double ct = virus1.getCt();
		Double av = virus1.getAv();
		Double ev = virus1.getEv();
		Double bar = virus1.getBarriers();
		
		Scanner sca = new Scanner(System.in);
		System.out.println("input the population:");
		if(sca.hasNextLong()) {
			long population = sca.nextLong();
			System.out.println("input the infected:");
			int infected = sca.nextInt();
			System.out.println("input the days:");
			int days = sca.nextInt();
		}else {
			System.out.println("closed");
			sca.close();
		}
	}
	
	public long simulation(long population, int infected,int days, read_config virus){
		
		long pop = population;
		int infector = infected;
		int day = days;
		
		group infect_group = new group();
		group normal_group = new group();
		group q_group = new group();
		group v_group = new group();
		group d_group = new group();
		
		for(int i=0; i<population; i++) {
			person a = new person(i);
		}
		
		for(int i=1; i<=days; i++) {
			
		}
		
		return 0;
	}

}
