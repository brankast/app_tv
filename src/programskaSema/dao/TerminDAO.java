package programskaSema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import programskaSema.model.Emisija;
import programskaSema.model.ProgrSema;
import programskaSema.model.Termin;


public class TerminDAO {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	public static boolean add(Termin termin) throws Exception {
		
		PreparedStatement stmt = null;
		try {
			String sql =
					"INSERT INTO termin (semaID, redniBroj, emisijaID, pocetak) VALUES (?,?,?,?)";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setInt(index++, termin.getSema().getId());
			stmt.setInt(index++, termin.getRedniBroj());
			stmt.setInt(index++, termin.getEmisija().getId());
			stmt.setString(index++, termin.getPocetak().toString());
			
			stmt.executeUpdate();
			
		}finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return true;
		
	}

	public static List<Termin> getAllByPS(ProgrSema program)  throws Exception {
		List<Termin> termini = new ArrayList<>();;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT termin.redniBroj, termin.emisijaID, termin.pocetak FROM termin " +  
					"WHERE termin.semaID = ?";
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			stmt.setInt(1, program.getId());
			
			rset = stmt.executeQuery();
			
			while (rset.next()) {
					int index = 1;
					int redniBroj = rset.getInt(index++);
					int emisijaID = rset.getInt(index++);
					Time pocetak = rset.getTime(index++);

					Emisija emisija = EmisijaDAO.getByID(emisijaID);
					Termin termin = new Termin(program, redniBroj, emisija, pocetak);
					termini.add(termin);
				}
			
		
	} finally {
		try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
	}
		return termini;

}


}