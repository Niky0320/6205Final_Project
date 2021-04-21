import java.util.Random;

public class person {
	private int num; // ID of person
	private boolean quarantined; // ÊÇ·ñ±»¸ôÀë
	private String status; // ×´Ì¬£¨Õı³££¬ËÀÍö£¬¸ĞÈ¾£¬ÒÑ×¢ÉäÒßÃç£©
	private int infected_days;
	private int age;
	private location area;
	private boolean masked;
	Random random = new Random();
	
	// constructor of person object
	public person(int num){
		this.num = num;
		this.quarantined = false;
		this.status = "normal";
		this.infected_days = 0;
		this.age = random.nextInt(100);
		
	}
	
	public void masked(Double uom) {
		Double randomNum = random.nextDouble();
		if(randomNum<=uom) {
			this.masked = true;
		}else {
			this.masked = false;
		}
	}
	
	public void setArea(location area) {
		this.area = area;
	}  
	
	
	//get id
	public int getNum() {
		return this.num;
	}
	
	//get quarantined
	public boolean getQuarantined() {
		return this.quarantined;
	}
	
	//get status
	public String getStatus() {
		return this.status;
	}
	
	//change the status of quarantined
	public void changeQuarantined() {
		this.quarantined = !this.quarantined;
	}
	
	//change the status of this person
	public void changeStatus(String a) {
		this.status = a;
	}
	
	//update the days of infected people
	public void updateID(int rr) {
		if(this.status=="infected") {
			this.infected_days++;
		}
		
		if(this.infected_days > 7 && this.infected_days<15) {
			int rand = random.nextInt(100);
			if(rand>90) {
				int recover = random.nextInt(100);
				if(recover<rr) {	
					this.status = "vaccined";
					this.infected_days = 0;
				}else{		
					this.status = "died";
					this.infected_days = 0;
				}
			}
		}else if(infected_days>=15) {
			int recover = random.nextInt(100);
			if(this.age>=65 || this.age<=16) {
				recover = recover+20;
			}
			if(recover<rr) {	
				this.status = "vaccined";
				this.infected_days = 0;
			}else{		
				this.status = "died";
				this.infected_days = 0;
			}
		}
	}
	
	
	public int getAge() {
		return this.age;
	}
	
	public location getArea() {
		return this.area;
	}
	
	public boolean getMask() {
		return this.masked;
	}
	
	public String getInfo() {
		String info = "";
		info = info + "ID:" + this.num +"Status:" + this.quarantined;
		return info;
	}
	
	
	
}
