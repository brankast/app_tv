package programskaSema.ui;

import programskaSema.dao.ConnectionManager;
import programskaSema.utils.PomocnaKlasaDatumi;

public class ApplicationUI {
	
	public static void main(String[] args) {
		try {
			ConnectionManager.open();
		} catch (Exception ex) {
			System.out.println("Neuspesna konekcija na bazu!");

			ex.printStackTrace();
			return;
		}

		int odluka = -1;
		while (odluka != 0) {
			ApplicationUI.ispisiMenu();
			
			System.out.print("opcija: ");
			odluka = PomocnaKlasaDatumi.ocitajCeoBroj();
			switch (odluka) {
				case 0:
					System.out.println("Izlaz iz programa");
					break;
				case 1:
					EmisijaUI.prikazSvih();
					break;
				case 2:
					ProgrSemaUI.prikaziSemu();
					break;
				case 3:
					TerminUI.dodavanje();
					break;
				case 4:
					EmisijaUI.procitajIUpisi();
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;
			}
		}
		
		try {
			ConnectionManager.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void ispisiMenu() {
		System.out.println("PROGRAMSKA SEMA - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Prikaz svih emisija");
		System.out.println("\tOpcija broj 2 - Prikaz programske seme za odredjeni datum");
		System.out.println("\tOpcija broj 3 - Dodavanje novog termina u program");
		System.out.println("\tOpcija broj 4 - Ucitaj podatke o rejtinzima i azuriraj ih u bazu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}

}
