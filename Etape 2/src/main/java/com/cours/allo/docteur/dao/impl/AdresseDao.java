/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.cours.allo.docteur.dao.entities.Adresse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class AdresseDao implements IAdresseDao {

	private static final Log log = LogFactory.getLog(AdresseDao.class);

	@Override
	public List<Adresse> findAllAdresses() {

		log.debug("Entree de la methode");
		PreparedStatement stmt;
		Connection conn;
		ResultSet resSet;
		List<Adresse> ret;

		ret = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Adresse");
			resSet = stmt.executeQuery();

			while (resSet.next()) {
				ret.add(resultSetToAdresse(resSet));
			}
		} catch (Exception e) {
			return null;
		}

		if (ret.size() == 0)
			ret = null;

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Adresse findAdresseById(int idAdresse) {
		PreparedStatement stmt;
		Connection conn;
		Adresse ret;
		ResultSet resSet;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Adresse WHERE idAdresse = ?");

			stmt.setInt(1, idAdresse);
			resSet = stmt.executeQuery();
			resSet.next();
			ret = resultSetToAdresse(resSet);
		} catch (Exception e) {
			return null;
		}

		return ret;
	}

	@Override
	public List<Adresse> findAdressesByVille(String ville) {
		PreparedStatement stmt;
		Connection conn;
		List<Adresse> ret;
		ResultSet resSet;

		ret = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Adresse WHERE ville = ?");

			stmt.setString(1, ville);
			resSet = stmt.executeQuery();
			while (resSet.next()) {
				ret.add(resultSetToAdresse(resSet));
			}
		} catch (Exception e) {
			return null;
		}

		if (ret.size() == 0)
			return null;

		return ret;
	}

	@Override
	public List<Adresse> findAdressesByCodePostal(String codePostal) {
		PreparedStatement stmt;
		Connection conn;
		List<Adresse> ret;
		ResultSet resSet;

		ret = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Adresse WHERE codePostal = ?");

			stmt.setString(1, codePostal);
			resSet = stmt.executeQuery();
			while (resSet.next()) {
				ret.add(resultSetToAdresse(resSet));
			}
		} catch (Exception e) {
			return null;
		}

		if (ret.size() == 0)
			return null;

		return ret;
	}

	@Override
	public Adresse createAdresse(Adresse adresse) {
		PreparedStatement stmt;
		Connection conn;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(
				"INSERT INTO Adresse(idUtilisateur, rue, codePostal, ville, pays, principale) VALUES (?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, adresse.getIdUtilisateur());
			stmt.setString(2, adresse.getRue());
			stmt.setString(3, adresse.getCodePostal());
			stmt.setString(4, adresse.getVille());
			stmt.setString(5, adresse.getPays());
			stmt.setBoolean(6, adresse.isPrincipale());

			stmt.executeUpdate();

			ResultSet result = stmt.getGeneratedKeys();

			result.next();

			adresse.setIdAdresse(result.getInt(1));

		} catch (Exception e) {
			return null;
		}

		return adresse;
	}

	@Override
	public Adresse updateAdresse(Adresse adresse) {
		Connection conn;
		PreparedStatement stmt;

		adresse.setVersion(adresse.getVersion() + 1);

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(
				"UPDATE Adresse SET rue = ?, codePostal = ?, ville = ?, pays = ?, principale = ?, version = ? WHERE idAdresse = ?");

			stmt.setString(1, adresse.getRue());
			stmt.setString(2, adresse.getCodePostal());
			stmt.setString(3, adresse.getVille());
			stmt.setString(4, adresse.getPays());
			stmt.setBoolean(5, adresse.isPrincipale());
			stmt.setInt(6, adresse.getVersion());
			stmt.setInt(7, adresse.getIdAdresse());

			stmt.executeUpdate();
		} catch (Exception e) {
			return null;
		}
		log.debug(adresse.getCodePostal());
		return adresse;
	}

	@Override
	public boolean deleteAdresse(Adresse adresse) {
		PreparedStatement stmt;
		Connection conn;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM Adresse WHERE idAdresse = ?");

			stmt.setInt(1, adresse.getIdAdresse());

			stmt.executeUpdate();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private Connection getConnection() throws Exception {
		return MySqlSingleton.getInstance().getDataSource().getConnection();
	}

	private Adresse resultSetToAdresse(ResultSet resSet) throws Exception {
		Adresse ret;
		Integer id;
		String rue;
		String codePostal;
		String ville;
		String pays;
		boolean principale;
		Integer version;
		Integer idUtilisateur;

		id = resSet.getInt(1);
		idUtilisateur = resSet.getInt(2);
		rue = resSet.getString(3);
		codePostal = resSet.getString(4);
		ville = resSet.getString(5);
		pays = resSet.getString(6);
		principale = resSet.getBoolean(7);
		version = resSet.getInt(8);

		ret = new Adresse(id, rue, codePostal, ville, pays, principale, version, idUtilisateur);

		return ret;
	}

}