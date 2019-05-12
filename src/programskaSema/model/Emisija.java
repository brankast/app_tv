package programskaSema.model;

public class Emisija {
	
	private int id;
	private String naziv;
	private int vremeTrajanjaMin;
	private int rejting;
	
	
	public Emisija(int id, String naziv, int vremeTrajanjaMin, int rejting) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.vremeTrajanjaMin = vremeTrajanjaMin;
		this.rejting = rejting;
	}


	@Override
	public String toString() {
		return "Emisija [id=" + id + ", naziv=" + naziv + ", vremeTrajanjaMin=" + vremeTrajanjaMin + ", rejting="
				+ rejting + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emisija other = (Emisija) obj;
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


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public int getVremeTrajanjaMin() {
		return vremeTrajanjaMin;
	}


	public void setVremeTrajanjaMin(int vremeTrajanjaMin) {
		this.vremeTrajanjaMin = vremeTrajanjaMin;
	}


	public int getRejting() {
		return rejting;
	}


	public void setRejting(int rejting) {
		this.rejting = rejting;
	}
	
	

}
