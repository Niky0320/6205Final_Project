import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class covid_simulation {
	
	read_config virus1 = new read_config("src/virus_1.config");
	location area1 = new location();
	Random random = new Random();
	
	public covid_simulation(){
		
		
		int population = virus1.getPop();
		int infector = virus1.getInf();
		int days = virus1.getDays();
		
		location area1 = new location();
		
		
		for(int i=0; i<population; i++) {
			person a = new person(i);
			a.masked(virus1.getUom());
			area1.add(a);	
		}
		
		for(int i=0; i<infector;i++) {
			person a = area1.getRandom();
			a.changeStatus("infected");
			a.updateID();
			area1.infectorAdd(a);
		}
		
		
		
		for(int i=1; i<=days; i++) {
			
			daily_simluation(virus1,i,area1);
			System.out.println("Day"+i);
			System.out.println(area1.getSize()+" : "+area1.getSizeII());
		}
		
	}
	

	public void daily_simluation(read_config virus,int day,location area1) {
		int count = 0;
		int numOfInfector = area1.getSizeII();
		for(int i = 0; i< numOfInfector;i++) {
			
			person infector = area1.getInfector();
			
			for(int j=0;j<virus.getR();j++) {
				
				person a = area1.getRandom();
				if(a.getStatus()=="normal" || a.getStatus()=="vaccined") {
					infect_proccess(a,area1,infector);
				}
				
				
			}
		}
		
		
		//检测到被感染的人就隔离
		for(int i=0; i< virus.getPot();i++) {
			person a = area1.getRandom();
			if(a.getStatus()=="infected" && !a.getQuarantined()) {
				a.changeQuarantined();
			}
		}
		
		//每天一部分人接种疫苗
		for(int i=0; i<=virus.getAv();i++) {
			person a = area1.getRandom();
			if(a.getStatus()=="normal") {
				a.changeStatus("vaccined");
			}
		}
		
		
		
		
	}


	private void infect_proccess(person a,location area,person infector) {
		
		double infect_rate = 1.0;
		
		//是否戴口罩
		if(a.getMask()) {
			infect_rate-=virus1.getEom();
		}
		
		//感染者是否已经被隔离
		if(infector.getQuarantined()) {
			infect_rate*=virus1.getBarriers();
		}
		
		//是否接种了疫苗
		if(a.getStatus()=="vaccined") {
			infect_rate*=(1-virus1.getEv());
		}
		
		//判断是否感染
		double judge = random.nextDouble();
		if(judge<=infect_rate) {
			a.changeStatus("infected");
			a.updateID();
			area.infectorAdd(a);
		}
		
	}


}
