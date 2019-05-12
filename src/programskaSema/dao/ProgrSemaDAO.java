package programskaSema.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import programskaSema.model.Emisija;
import programskaSema.model.ProgrSema;
import programskaSema.model.Termin;

public class ProgrSemaDAO {

	public static ProgrSema get(Date datum) throws Exception {
		ProgrSema program = null;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT  programskaSema.id, emisija.id, emisija.naziv, emisija.vremeTrajanjaMin, emisija.rejting, termin.redniBroj, termin.pocetak FROM programskasema " + 
					"LEFT JOIN termin ON programskasema.id = termin.semaID " + 
					"LEFT JOIN emisija ON emisija.id = termin.emisijaID " + 
					"WHERE programskasema.datum = ?";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			stmt.setDate(1, datum);

			rset = stmt.executeQuery();
			while (rset.next()) {
				
				if (program == null) {
					int index = 1;
					int id = rset.getInt(index++);

					program = new ProgrSema(id, datum);
				}
				int index = 2;
				// emisija
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				int vrTrajanjaMin = rset.getInt(index++);
				int rejting = rset.getInt(index++);
				
				Emisija emisija = new Emisija(id, naziv, vrTrajanjaMin, rejting);
	
				// termin
				int redniBroj = rset.getInt(index++);
				Date pocetak = rset.getDate(index++);
				
				
				Termin termin = new Termin(program, redniBroj, emisija, pocetak);
				
				program.getSviTermini().add(termin);
				
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return program;
	}

	public static ProgrSema getByID(int semaID) throws Exception {
		ProgrSema program = null;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT datum FROM programskasema WHERE id=" + semaID;

			stmt = ConnectionManager.getConnection().prepareStatement(sql);

			rset = stmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				Date datum = rset.getDate(index++);
				
				program = new ProgrSema(semaID, datum);
				
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return program;
	}
		
	}


