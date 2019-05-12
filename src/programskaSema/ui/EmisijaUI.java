package programskaSema.ui;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import programskaSema.dao.EmisijaDAO;
import programskaSema.model.Emisija;

public class EmisijaUI {

	public static void prikazSvih() {
		try {
			List<Emisija> emisije = EmisijaDAO.getAll();
			
			System.out.println();
			System.out.printf("%5s %24s %20s %17s", "ID",  "NAZIV", "VREME TRAJANJA", "REJTING");
			System.out.println();
			System.out.println("===== ============================= ================= ================");
			
			for (Emisija itEmisija: emisije) {
				System.out.printf("%5s %25s %20s %15s",
						itEmisija.getId(),  itEmisija.getNaziv(), itEmisija.getVremeTrajanjaMin(), itEmisija.getRejting());
				System.out.println();
				System.out.println("----- ----------------------------- ----------------- ----------------");
			}
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
		
	}
	
	
	
	public static void citajIzFajla(File dokument) throws IOException {
		if(dokument.exists()){
			
			BufferedReader in = new BufferedReader(new FileReader(dokument));

			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			
			String s2;
			while((s2 = in.readLine()) != null) {
				
				String [] tokeni = s2.split(",");
				
				String t1 = tokeni[0];
				String t2 = tokeni[1];
				
				System.out.println(t1 + t2);
				
				int emisijaId = Integer.parseInt(t1.trim());
				int rejting = Integer.parseInt(t2.trim());
				
				try {
				Emisija emisija = EmisijaDAO.getByID(emisijaId);
				
				emisija.setRejting(rejting);
				EmisijaDAO.ucitajRejting(emisija);
				
				System.out.println("Rejtinzi su evidentirani.");
				}
				catch (Exception ex) {
					System.out.println("Greska u radu sa bazom!");

					ex.printStackTrace();
				}
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}
	
	
	public static void procitajIUpisi() {
		try {
			File studFajl = new File("C:\\Users\\user\\Desktop\\JWD\\PROJEKTI1\\rejtinzi.csv");
			citajIzFajla(studFajl);
			
			}catch (Exception ex) {
				System.out.println("Greska pri ucitavanju podataka!");
				ex.printStackTrace();
			}
	}

}
