import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * group of different person in different status
 */
public class location{
	
	private List<person> list = new ArrayList<>();
	private List<person> list2 = new ArrayList<>(); //收到感染的人群
	private int size;
	private int size2; //隔离区人数
	
	public location() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public void add(person a) {
		this.list.add(a);
		this.size++;
		if(a.getStatus()=="infected") {
			this.list2.add(a);
			this.size2++;
		}
	}
	
	public void infectorAdd(person a) {
		this.list2.add(a);
		this.size2++;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getSizeII() {
		return this.size2;
	}
	
	public void delete(person a) {
		this.list.remove(a);
	}
	
	public person getRandom() {
		Random random = new Random();
		int i = random.nextInt(this.size);
		return list.get(i);
		
	}
	
	public person getInfector() {
		Random random = new Random();
		int i = random.nextInt(this.size2);
		return list2.get(i);
	}
	
	public void show() {
		System.out.println(size2);
	}
	
}
