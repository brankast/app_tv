package programskaSema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import programskaSema.model.Emisija;


public class EmisijaDAO {
	
	public static List<Emisija> getAll() throws Exception {
		List<Emisija> emisije = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, naziv, vremeTrajanjaMin, rejting FROM emisija";

			stmt = ConnectionManager.getConnection().createStatement();

			rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				int vremeTrajanjaMin = rset.getInt(index++);
				int rejting = rset.getInt(index++);
				

				Emisija emisija = new Emisija(id, naziv, vremeTrajanjaMin, rejting);
				emisije.add(emisija);
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		
		return emisije;
	}

	public static Emisija getByID(int id) throws Exception {
		Emisija emisija = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT naziv, vremeTrajanjaMin, rejting FROM emisija WHERE id=" + id;

			stmt = ConnectionManager.getConnection().createStatement();
			
			
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index = 1;
				
				String naziv = rset.getString(index++);
				int VremeTrajanjaMin = rset.getInt(index++);
				int rejting = rset.getInt(index++);
				
				emisija = new Emisija(id, naziv, VremeTrajanjaMin, rejting);
				
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return emisija;
	}
	
	
	
public static boolean ucitajRejting(Emisija emisija) throws Exception {
		
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE emisija SET rejting = ? WHERE id = ?";
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setInt(index++, emisija.getRejting());
			stmt.setInt(index++, emisija.getId());
			
			return stmt.executeUpdate() == 1;
			
		}finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
	}
		
	}


