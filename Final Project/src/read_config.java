import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class read_config {
	Properties prop = new Properties();
	InputStream is = null;
	private String population;
	private String infector;
	private String days;
	private String name; //name of disease
	private String r; //r factor
	private String uom; //usage of masks
	private String eom; //effectiveness of masks
	private String pot; //prevalence of testing
	private String ct; //contact tracing
	private String av; //availability of the vaccine
	private String ev; //efficacy of the vaccine
	private String barriers; //barriers to entry
	private String rr; //recover rate
	private String datev; //date to get vaccine
	
	
	/*
	 * read the config and apply them
	 */
	public read_config(String fileName) {
		
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
		
		this.population = prop.getProperty("virus.population");
		this.infector = prop.getProperty("virus.infector");
		this.days = prop.getProperty("virus.days");
		this.name = prop.getProperty("virus.name");
		this.r = prop.getProperty("virus.r");
		this.uom = prop.getProperty("virus.uom");
		this.eom = prop.getProperty("virus.eom");
		this.pot = prop.getProperty("virus.pot");
		this.ct = prop.getProperty("virus.ct");
		this.av = prop.getProperty("virus.av");
		this.ev = prop.getProperty("virus.ev");
		this.barriers = prop.getProperty("virus.barriers");
		this.rr = prop.getProperty("virus.rr");
		this.datev = prop.getProperty("virus.dateofv");
	}
	
	/*
	 * get method for name
	 */
	public String getName() {
		return this.name;
		
	}
	
	
	/*
	 * get method for r
	 */
	public int getR() {
		return Integer.parseInt(this.r);
	}
	
	/*
	 * get method for usage of masks
	 */
	public Double getUom() {
		 return Double.parseDouble(this.uom);
	}
	
	
	/*
	 * get method for effectiveness of masks
	 */
	public Double getEom() {
		 return Double.parseDouble(this.eom);
	}
	
	/*
	 * get method for prevalence of testing
	 */
	public int getPot() {
		 return Integer.parseInt(this.pot);
	}
	
	/*
	 * get method for contacting tracing
	 */
	public Double getCt() {
		 return Double.parseDouble(this.ct);
	}
	
	/*
	 * get method for availability of vaccine
	 */
	public int getAv() {
		 return Integer.parseInt(this.av);
	}
	
	/*
	 * get method for effiency of vaccine
	 */
	public Double getEv() {
		 return Double.parseDouble(this.ev);
	}
	
	/*
	 * get method for barriers
	 */
	public Double getBarriers() {
		 return Double.parseDouble(this.barriers);
	}
	/*
	 * get method for whole population
	 */
	public int getPop() {
		return Integer.parseInt(this.population);
	}
	/*
	 * get method for initial infectors
	 */
	public int getInf() {
		return Integer.parseInt(this.infector);
	}
	/*
	 * get method for days to simulate
	 */
	public int getDays() {
		return Integer.parseInt(this.days);
	}
	/*
	 * get method for recover rate
	 */
	public int getRr() {
		return Integer.parseInt(this.rr);
	}
	/*
	 * get method for date start to get vaccine
	 */
	public int getDateofv() {
		return Integer.parseInt(this.datev);
	}
}
