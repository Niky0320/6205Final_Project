import java.util.Random;

public class person {
	private int num; // ID of person
	private boolean quarantined; // ÊÇ·ñ±»¸ôÀë
	private String status; // ×´Ì¬£¨Õı³££¬ËÀÍö£¬¸ĞÈ¾£¬ÒÑ×¢ÉäÒßÃç£©
	private int infected_days;
	private int age;
	private boolean tested;
	private String area;
	private boolean masked;
	Random random = new Random();
	
	// constructor of person object
	public person(int num){
		this.num = num;
		this.quarantined = false;
		this.status = "normal";
		this.infected_days = 0;
		this.age = random.nextInt(100);
		this.tested = false;
		this.area = "area1";
	}
	
	public void masked(Double uom) {
		Double randomNum = random.nextDouble();
		if(randomNum<=uom) {
			this.masked = true;
		}else {
			this.masked = false;
		}
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
	public void updateID() {
		this.infected_days++;
	}
	
	public boolean getTested() {
		return this.tested;
	}
	
	
	public int getAge() {
		return this.age;
	}
	
	public String getArea() {
		return this.area;
	}
	
	public boolean getMask() {
		return this.masked;
	}
	
	
}
