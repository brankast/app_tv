package programskaSema.ui;

import java.sql.Date;
import java.util.List;

import programskaSema.dao.ProgrSemaDAO;
import programskaSema.dao.TerminDAO;
import programskaSema.model.ProgrSema;
import programskaSema.model.Termin;
import programskaSema.utils.PomocnaKlasaDatumi;


public class ProgrSemaUI {

	public static void prikaziSemu() {
		System.out.println("Unesite datum programske seme (" + PomocnaKlasaDatumi.DATE_FORMAT.toPattern() + "): ");
		Date datum = new Date(PomocnaKlasaDatumi.ocitajDatum().getTime());

		try {
			ProgrSema program = ProgrSemaDAO.get(datum) ;

			if(program != null) {
			List<Termin> termini = TerminDAO.getAllByPS(program);
			System.out.println("Programska sema za datum " + datum + ":");
			System.out.println("------------------------------------------------------------------------");
			System.out.printf("%5s %24s %20s %17s", "termin",  "pocetak", "naziv emsije", "trajanje[min]");
			System.out.println();
			System.out.println("===== ============================= ================= ================");
			for(Termin t : termini) {
				System.out.printf("%5s %25s %20s %15s",
						t.getRedniBroj(), t.getPocetak(), t.getEmisija().getNaziv(), t.getEmisija().getVremeTrajanjaMin());
				System.out.println();
				System.out.println("----- ----------------------------- ----------------- ----------------");
					}
				}
			else {
				System.out.println("Programska sema sa ovim datumom ne postoji.");
			}
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
		
	}

}
