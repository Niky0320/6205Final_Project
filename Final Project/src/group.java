import java.util.ArrayList;
import java.util.List;

public class group {
	
	private List<person> list = new ArrayList<>();
	private long size;
	
	public group() {
		this.size = 0;
	}
	
	public void add(person a) {
		this.list.add(a);
		this.size++;
	}
	
	public long getSize() {
		return this.size;
	}
	
	public void delete(person a) {
		this.list.remove(a);
	}
}
