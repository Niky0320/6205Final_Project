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
		location area2 = new location();
		location area3 = new location();
		location area4 = new location();
		List<location> listOfArea = new ArrayList<>();
		listOfArea.add(area1);
		listOfArea.add(area2);
		listOfArea.add(area3);
		listOfArea.add(area4);
		
		
		for(int i=0; i<population; i++) {
			person a = new person(i);
			a.masked(virus1.getUom());
			switch(a.getArea()) {
			
			case "area1":
				area1.add(a);
			
			case "area2":
				area2.add(a);
			
			case "area3":
				area3.add(a);
				
			case "area4":
				area4.add(a);
			
			}
			
		}
		
		//�漴�趨��ʼ��Ⱦ��
		for(int i=0; i<infector;i++) {
			int randomNum = random.nextInt(4);
			switch(randomNum) {
			case 0:
				AreaAddInf(area1);
			case 1:
				AreaAddInf(area2);
			case 2:
				AreaAddInf(area3);
			case 3:
				AreaAddInf(area4);
			}
		}
		
		
		
		for(int i=1; i<=days; i++) {
			for(location area:listOfArea) {
				daily_simluation(virus1,i,area);
			}
		}
		
		for(location area:listOfArea) {
			area.show();
		}
		
	}
	
	public void AreaAddInf(location area) {
		person a = area.getRandom();
		a.changeStatus("infected");
		a.updateID();
		area.infectorAdd(a);
	}

	public void daily_simluation(read_config virus,int day,location area1) {

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
		
		
		//��⵽����Ⱦ���˾͸���
		for(int i=0; i< virus.getPot();i++) {
			person a = area1.getRandom();
			if(a.getStatus()=="infected" && !a.getQuarantined()) {
				a.changeQuarantined();
			}
		}
		
		//ÿ��һ�����˽�������
		for(int i=0; i<=virus.getAv();i++) {
			person a = area1.getRandom();
			if(a.getStatus()=="normal") {
				a.changeStatus("vaccined");
			}
		}
		
		
		
		
	}


	private void infect_proccess(person a,location area,person infector) {
		
		double infect_rate = 1.0;
		
		//�Ƿ������
		if(a.getMask()) {
			infect_rate-=virus1.getEom();
		}
		
		//��Ⱦ���Ƿ��Ѿ�������
		if(infector.getQuarantined()) {
			infect_rate*=virus1.getBarriers();
		}
		
		//�Ƿ����������
		if(a.getStatus()=="vaccined") {
			infect_rate*=(1-virus1.getEv());
		}
		
		//�ж��Ƿ��Ⱦ
		double judge = random.nextDouble();
		if(judge<=infect_rate) {
			a.changeStatus("infected");
			a.updateID();
			area.infectorAdd(a);
		}
		
	}


}
