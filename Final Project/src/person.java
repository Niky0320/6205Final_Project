
public class person {
	private int num;
	private boolean quarantined;
	private String status;
	private int infected_days;
	
	public person(int num){
		this.num = num;
		this.quarantined = false;
		this.status = "normal";
		this.infected_days = 0;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public boolean getQuarantined() {
		return this.quarantined;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void changeQuarantined() {
		this.quarantined = !this.quarantined;
	}
	
	public void changeStatus(String a) {
		this.status = a;
	}
	
	public void updateID() {
		this.infected_days++;
	}
	
}
