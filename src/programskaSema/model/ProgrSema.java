package programskaSema.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import programskaSema.utils.PomocnaKlasaDatumi;


public class ProgrSema {
	
	private int id;
	private Date datum;
	
	private List<Termin> sviTermini = new ArrayList<>();

	public ProgrSema(int id, Date datum) {
		this.id = id;
		this.datum = datum;
	}

	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");	
		StringBuilder termini = new StringBuilder();
		for (Termin itTermin: sviTermini) {
			termini.append(newLine);
			termini.append(itTermin);
		}
		
		return "ProgrSema [id=" + id + ", datum=" + PomocnaKlasaDatumi.DATE_FORMAT.format(datum) +"]" + termini;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgrSema other = (ProgrSema) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public List<Termin> getSviTermini() {
		return sviTermini;
	}

	public void setSviTermini(List<Termin> sviTermini) {
		this.sviTermini = sviTermini;
	}
	
	

}
