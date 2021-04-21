import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class covid_simulation {
	read_config virus1;
	Random random = new Random();
	location area1 = new location("area1");
	location area2 = new location("area2");
	location area3 = new location("area3");
	location area4 = new location("area4");
	List<location> listOfArea = new ArrayList<>();
	
	
	List<String> file1 = new ArrayList<>();
	List<String> file2 = new ArrayList<>();
	List<String> file3 = new ArrayList<>();
	List<String> file4 = new ArrayList<>();
	
	public covid_simulation(String filename){
		/**
		 * 
		 * set initial population to random areas, get days to simulate and get initial infectors
		 * 
		 */
		virus1 = new read_config("src/"+filename);
		int population = virus1.getPop();
		int infector = virus1.getInf();
		int days = virus1.getDays();
		
		
		listOfArea.add(area1);
		listOfArea.add(area2);
		listOfArea.add(area3);
		listOfArea.add(area4);
		
		
		for(int i=0; i<population; i++) {
			person a = new person(i);
			a.masked(virus1.getUom());
			int ac = random.nextInt(4);
			switch(ac) {
			
			case 0:
				area1.add(a);
				a.setArea(area1);
				break;
			
			case 1:
				area2.add(a);
				a.setArea(area2);
				break;
			
			case 2:
				area3.add(a);
				a.setArea(area3);
				break;
				
			case 3:
				area4.add(a);
				a.setArea(area4);
				break;
			
			}
			
		}
		
		//randomly generates initial infectors
		for(int i=0; i<infector;i++) {
			int randomNum = random.nextInt(4);
			switch(randomNum) {
			case 0:
				AreaAddInf(area1);
				break;
			case 1:
				AreaAddInf(area2);
				break;
			case 2:
				AreaAddInf(area3);
				break;
			case 3:
				AreaAddInf(area4);
				break;
			}
		}
		
		
		//export the init info for GUI
		for(location area:listOfArea) {
			export(0,area);
		}
		
		
		//run daily simulation to start the simulation for days
		for(int i=1; i<=days; i++) {
			for(location area:listOfArea) {
				daily_simluation(virus1,i,area);
				export(i,area);
				
			}
		}
		
		//export
		exportToFile("simulation_1.csv",file1);
		exportToFile("simulation_2.csv",file2);	
		exportToFile("simulation_3.csv",file3);	
		exportToFile("simulation_4.csv",file4);	
	}
	
	public void AreaAddInf(location area) {
		person a = area.getRandom();
		a.changeStatus("infected");
		a.updateID(virus1.getRr());
		area.infectorAdd(a);
	}
	
	private void exportToFile(String string, List<String> list) {
		// TODO Auto-generated method stub
		File file = new File(string);
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
			for(String i:list) {
				output.write(i);
				output.newLine();
			}
			output.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void export(int day, location area) {
		
		String info = "";
		int size = 0;
		int size2 = 0;
		for(person a: area.getAll()) {
			if(a.getStatus()=="died") {
				size++;
			}
		}
		for(person a: area.getAll()) {
			if(a.getStatus()=="normal") {
				size2++;
			}
		}
		info = info + area.getSize() + "," + area.getSizeII() + "," + area.getSizeIII() + "," + size+","+area.getSizeV() + "," +size2;
		
		
		switch(area.getName()) {
			case "area1":
				file1.add(info);
				break;
			case "area2":
				file2.add(info);
				break;
			case "area3":
				file3.add(info);
				break;
			case "area4":
				file4.add(info);
				break;
		}
			
		
		
	}
	
	

	public void daily_simluation(read_config virus,int day,location area) {

		int numOfInfector = area.getSizeII();
		for(int i = 0; i< numOfInfector;i++) {
			
			person infector = area.getInfector();
			
			for(int j=0;j<virus.getR();j++) {
				
				person a = area.getRandom();
				if(a.getStatus()=="normal" || a.getStatus()=="vaccined") {
					infect_proccess(a,area,infector);
				}
				
				
			}
		}
		
		
		//Quarantine if detected infector
		for(int i=0; i< virus.getPot();i++) {
			person a = area.getRandom();
			if((a.getStatus()=="infected") && (!a.getQuarantined())) {
				a.changeQuarantined();
				area.quaAdd(a);
			}
		}
		
		//Citizen Randomly injects vaccine each day
		if(day>=virus1.getDateofv()) {
			for(int i=0; i<=virus.getAv();i++) {
				person a = area.getRandom();
				if(a.getStatus()=="normal") {
					a.changeStatus("vaccined");
					area.vacAdd(a);
				}
			}
		}
		
		//update infectors` days
		List<person> temp = new ArrayList<person>();
		for(person a : area.getInfectors()) {
			a.updateID(virus1.getRr());
			if(a.getStatus()=="vaccined") {
				temp.add(a);
			}
		}
		
		for(person a : temp) {
			area.deleteInfector(a);
			area.vacAdd(a);
		}
		
		
		Double ct = virus.getCt();
		
		List<person> temp3 = new ArrayList<person>();
		for(person a: area.getAll()) {
			double num = random.nextDouble();
			if((num<=ct) && (!a.getQuarantined()) && (a.getStatus()!="died") && (16<a.getAge()) && (a.getAge()<60)) {
				temp3.add(a);
			}
		}
		
		for(person a:temp3) {
			int i = random.nextInt(4);
			while(area == listOfArea.get(i)) {
				i = random.nextInt(4);
			}
			location moveTo = listOfArea.get(i);
			a.setArea(moveTo);
			moveTo.add(a);
			area.delete(a);
			
		}
		
	
		//recover in quarantine
		List<person> temp4 = new ArrayList<person>();
		for(person a: area.getQua()) {
			if(a.getStatus()=="vaccined"||a.getStatus()=="died") {
				temp4.add(a);
			}
		}
		
		for(person a: temp4) {
			area.quaDelete(a);
		}
		temp.clear();
		temp3.clear();
		temp4.clear();
		
	}


	private void infect_proccess(person a,location area,person infector) {
		
		double infect_rate = 1.0;
		//Mask on or not
		if(a.getMask()) {
			infect_rate= infect_rate*(1.0-virus1.getEom());
		}
		
		
		//Whether Quarantined or not after infected
		if(infector.getQuarantined()) {
			infect_rate=infect_rate*(1-virus1.getBarriers());
		}
		
		//Whether Vaccined or not
		if(a.getStatus()=="vaccined") {
			infect_rate=infect_rate*(1-virus1.getEv());
		}
		
		//Make a judgment on Infection
		double judge = random.nextDouble();
		if(judge<infect_rate) {
			a.changeStatus("infected");
			area.infectorAdd(a);
		}
		
	}


}
