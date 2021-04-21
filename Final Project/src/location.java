import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * group of different person in different status
 */
public class location{
	
	private List<person> list = new ArrayList<>();
	private List<person> list2 = new ArrayList<>(); //收到感染的人群
	private List<person> list3 = new ArrayList<>(); //隔离区的人群
	private List<person> list5 = new ArrayList<>(); //people who get vac
	private int size;	//amount of person in the area
	private int size2; //感染人数
	private int size3; //隔离区人数
	private int size5; //amount of vac people
	private String name;
	
	public location(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.size=0;
		this.size2=0;
		this.size3=0;
		this.size5=0;
	}
	
	
	public void add(person a) {
		this.list.add(a);
		this.size++;
		if(a.getStatus()=="infected") {
			this.list2.add(a);
			this.size2++;
		}else if(a.getStatus()=="vaccined") {
			this.list5.add(a);
			this.size5++;
		}
	}
	
	public void infectorAdd(person a) {
		this.list2.add(a);
		this.size2++;
	}
	
	public void quaAdd(person a) {
		this.list3.add(a);
		this.size3++;
	}
	
	
	public void vacAdd(person a) {
		this.list5.add(a);
		this.size5++;
	}
	
	public List<person> getQua(){
		return this.list3;
	}
	
	public void quaDelete(person a) {
		this.list3.remove(a);
		this.size3--;
	}
	
	public int getSize() {
		return this.size;
	}
	//return the number of infectors in this area
	public int getSizeII() {
		return this.size2;
	}
	
	public int getSizeIII() {
		return this.size3;
	}
	
	public int getSizeV() {
		return this.size5;
	}
	
	
	public void delete(person a) {
		this.list.remove(a);
		this.size--;
		if(a.getStatus()=="infected") {
			this.list2.remove(a);
			this.size2--;
		}else if(a.getStatus()=="vaccined") {
			this.list5.remove(a);
			this.size5--;
		}
	}
	
	public void deleteInfector(person a) {
		this.list2.remove(a);
		this.size2--;
	}
	
	
	public person getRandom() {
		Random random = new Random();
		int i = random.nextInt(this.size);
		return list.get(i);
		
	}
	//return a random infector
	public person getInfector() {
		Random random = new Random();
		int i = random.nextInt(this.size2);
		return list2.get(i);
	}
	
	
	public void show() {
		System.out.println(this.size2);
	}
	
	public List<person> getInfectors(){
		return this.list2;
	}
	
	public List<person> getAll(){
		return this.list;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	
}
