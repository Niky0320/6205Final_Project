import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class read_config {
	Properties prop = new Properties();
	InputStream is = null;
	private String name; //name of disease
	private String k; //k factor
	private String r; //r factor
	private String uom; //usage of masks
	private String eom; //effectiveness of masks
	private String pot; //prevalence of testing
	private String ct; //contact tracing
	private String av; //availability of the vaccine
	private String ev; //efficacy of the vaccine
	private String barriers; //barriers to entry
	
	public read_config() {
		
	}
	
	public void read_config(String fileName) {
		
		try {
			is = new FileInputStream(fileName);
		}catch(FileNotFoundException ex) {
			System.out.println("File not found.");
		}
		
		try {
			prop.load(is);
		}catch(IOException ex) {
			System.out.println("IO exception");
		}
		
		this.name = prop.getProperty("virus.name");
		this.k = prop.getProperty("virus.k");
		this.r = prop.getProperty("virus.r");
		this.uom = prop.getProperty("virus.uom");
		this.eom = prop.getProperty("virus.eom");
		this.pot = prop.getProperty("virus.pot");
		this.ct = prop.getProperty("virus.ct");
		this.av = prop.getProperty("virus.av");
		this.ev = prop.getProperty("virus.ev");
		this.barriers = prop.getProperty("virus.barriers");
	}
	

	public String getName() {
		return this.name;
		
	}
	
	public Double getK() {
		return Double.parseDouble(this.k);
	}
	
	public Double getR() {
		return Double.parseDouble(this.r);
	}
	
	public Double getUom() {
		 return Double.parseDouble(this.uom);
	}
	
	public Double getEom() {
		 return Double.parseDouble(this.eom);
	}
	
	public Double getPot() {
		 return Double.parseDouble(this.pot);
	}
	
	public Double getCt() {
		 return Double.parseDouble(this.ct);
	}
	
	public Double getAv() {
		 return Double.parseDouble(this.av);
	}
	
	public Double getEv() {
		 return Double.parseDouble(this.ev);
	}
	
	public Double getBarriers() {
		 return Double.parseDouble(this.barriers);
	}
	
	
}
