package programskaSema.ui;



import java.sql.Date;
import java.sql.Time;
import java.util.List;

import programskaSema.dao.EmisijaDAO;
import programskaSema.dao.ProgrSemaDAO;
import programskaSema.dao.TerminDAO;
import programskaSema.model.Emisija;
import programskaSema.model.ProgrSema;
import programskaSema.model.Termin;
import programskaSema.utils.PomocnaKlasaDatumi;

public class TerminUI {

	@SuppressWarnings("deprecation")
	public static void dodavanje() {
	
		Termin termin = null;
		try {
		System.out.println();
		System.out.println("Unesite datum programske seme (" + PomocnaKlasaDatumi.DATE_FORMAT.toPattern() + "): ");
		Date datum = new Date(PomocnaKlasaDatumi.ocitajDatum().getTime());

		ProgrSema sema = ProgrSemaDAO.get(datum);
		
		if (sema == null) {
			System.out.println("Nije kreirana sema za uneti datum.");
		}
		
		System.out.println("Unesite ID emisije koju zelite dodati: ");
		int emisijaID = PomocnaKlasaDatumi.ocitajCeoBroj();
		
		Emisija emisija = EmisijaDAO.getByID(emisijaID);
		
		if (emisija == null) {
			System.out.println("Uneta emisija ne postoji.");
		}
		
		List<Termin> termini = TerminDAO.getAllByPS(sema);
		if (!termini.isEmpty()) {
		int size = termini.size();
		
		int sledeciRB = size + 1;
		
		
		Time pocetakPrethodne = new Time(termini.get(size-1).getPocetak().getTime());
		int vremeTrajanja = termini.get(size-1).getEmisija().getVremeTrajanjaMin();
		
		int minutiPrethodni = pocetakPrethodne.getMinutes();
		int satiPrethodni = pocetakPrethodne.getHours();
		
		Time pocetakNove = new Time(0);
		
		
		int suma = minutiPrethodni+vremeTrajanja;
		
		if (suma > 60 && suma < 120) {
			pocetakNove.setHours(satiPrethodni+1);
			pocetakNove.setMinutes(suma-60);
			System.out.println("Program ce poceti sa emitovanjem u: " + pocetakNove);
		}else if(suma < 60) {
			pocetakNove.setHours(satiPrethodni);
			pocetakNove.setMinutes(minutiPrethodni+vremeTrajanja);
		}else if(suma > 120) {
			pocetakNove.setHours(satiPrethodni+2);
			pocetakNove.setMinutes(minutiPrethodni+vremeTrajanja-120);
		}
		
		
		termin = new Termin(sema, sledeciRB, emisija, pocetakNove);
		
		}else {
			
			Time pocetak = new Time(0);
			pocetak.setHours(0);
			pocetak.setMinutes(0);
			
			System.out.println("Program ce poceti sa emitovanjem u: " + pocetak);
			termin = new Termin(sema, 1, emisija, pocetak);
			
		
		}
		TerminDAO.add(termin);	
		System.out.println("Termin je uspesno dodat u programski semu.");
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
		
	}

}
