package programskaSema.model;

import java.util.Date;

import programskaSema.utils.PomocnaKlasaDatumi;

public class Termin {
	
	private ProgrSema sema;
	private int redniBroj;
	private Emisija emisija;
	private Date pocetak;
	
	
	public Termin(ProgrSema sema, int redniBroj, Emisija emisija, Date pocetak) {
		super();
		this.sema = sema;
		this.redniBroj = redniBroj;
		this.emisija = emisija;
		this.pocetak = pocetak;
	}


	@Override
	public String toString() {
		return "Termin [sema=" + sema.getId() + ", redniBroj=" + redniBroj + ", emisija=" + emisija.getNaziv() + ", pocetak=" + PomocnaKlasaDatumi.TIME_FORMAT.format(pocetak) + "]";
	}


	public ProgrSema getSema() {
		return sema;
	}


	public void setSema(ProgrSema sema) {
		this.sema = sema;
	}


	public int getRedniBroj() {
		return redniBroj;
	}


	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}


	public Emisija getEmisija() {
		return emisija;
	}


	public void setEmisija(Emisija emisija) {
		this.emisija = emisija;
	}


	public Date getPocetak() {
		return pocetak;
	}


	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}
	
	

}
