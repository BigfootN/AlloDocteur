/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.mysql.jdbc.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(UtilisateurDao.class);

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		log.debug("Entree de la methode");
		PreparedStatement stmt;
		Connection conn;
		ResultSet resSet;
		List<Utilisateur> ret;
		Utilisateur curUser;

		conn = null;
		stmt = null;
		resSet = null;

		ret = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Utilisateur");
			resSet = stmt.executeQuery();

			while (resSet.next()) {
				curUser = resultSetToUser(resSet);
				curUser.setAdresses(getAddrUser(curUser.getIdUtilisateur()));
				ret.add(curUser);
			}
		} catch (Exception e) {
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, resSet);
		}

		if (ret.size() == 0)
			ret = null;

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		PreparedStatement stmt;
		Connection conn;
		Utilisateur ret;
		ResultSet resSet;

		stmt = null;
		conn = null;
		resSet = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE idUtilisateur = ?");

			stmt.setInt(1, idUtilisateur);
			resSet = stmt.executeQuery();
			resSet.next();
			ret = resultSetToUser(resSet);
		} catch (Exception e) {
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, resSet);
		}

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		PreparedStatement stmt;
		Connection conn;
		ResultSet resSet;
		List<Utilisateur> ret;
		Utilisateur curUser;

		stmt = null;
		resSet = null;
		conn = null;

		ret = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE prenom = ?");

			stmt.setString(1, prenom);
			resSet = stmt.executeQuery();

			while (resSet.next()) {
				curUser = resultSetToUser(resSet);
				curUser.setAdresses(getAddrUser(curUser.getIdUtilisateur()));
				ret.add(curUser);
			}
		} catch (Exception e) {
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, resSet);
		}

		if (ret.size() == 0)
			return null;

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		PreparedStatement stmt;
		Connection conn;
		ResultSet resSet;
		List<Utilisateur> ret;
		Utilisateur curUser;

		stmt = null;
		resSet = null;
		conn = null;

		ret = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE nom = ?");

			stmt.setString(1, nom);
			resSet = stmt.executeQuery();
			while (resSet.next()) {
				curUser = resultSetToUser(resSet);
				curUser.setAdresses(getAddrUser(curUser.getIdUtilisateur()));
				ret.add(curUser);
			}
		} catch (Exception e) {
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, resSet);
		}

		if (ret.size() == 0)
			return null;

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
		PreparedStatement stmt;
		Connection conn;
		ResultSet resSet;
		List<Utilisateur> ret;
		Utilisateur curUser;

		stmt = null;
		conn = null;
		resSet = null;

		ret = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(
					"SELECT Utilisateur.* FROM Utilisateur INNER JOIN Adresse ON (Adresse.idUtilisateur = Utilisateur.idUtilisateur AND Adresse.codePostal = ?)");

			stmt.setString(1, codePostal);

			resSet = stmt.executeQuery();
			while (resSet.next()) {
				curUser = resultSetToUser(resSet);
				curUser.setAdresses(getAddrUser(curUser.getIdUtilisateur()));
				ret.add(curUser);
			}
		} catch (Exception e) {
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, resSet);
		}

		if (ret.size() == 0)
			ret = null;

		return ret;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		Integer idInsertedUser;

		user.setDateCreation(new Date());
		user.setDateModification(new Date());

		try {
			idInsertedUser = insertUserIntoTable(user);
			if (user.getAdresses() != null)
				insertAdressesIntoTable(user.getAdresses(), idInsertedUser);
		} catch (Exception e) {
			return null;
		}

		user.setIdUtilisateur(idInsertedUser);

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		Connection conn;
		PreparedStatement stmt;
		int newVersion;
		List<Adresse> addrList;

		newVersion = findUtilisateurById(user.getIdUtilisateur()).getVersion() + 1;
		addrList = findUtilisateurById(user.getIdUtilisateur()).getAdresses();

		user.setVersion(newVersion);
		user.setDateModification(new Date());
		user.setAdresses(addrList);

		stmt = null;
		conn = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(
					"UPDATE base_quest_allo_docteur.Utilisateur SET civilite = ?, prenom = ?, nom = ?, identifiant = ?, motPasse = ?, dateNaissance = ?, dateCreation = ?, dateModification = ?, actif = ?, marquerEffacer = ?, version = ? WHERE idUtilisateur = ?");

			stmt.setString(1, user.getCivilite());
			stmt.setString(2, user.getPrenom());
			stmt.setString(3, user.getNom());
			stmt.setString(4, user.getIdentifiant());
			stmt.setString(5, user.getMotPasse());
			stmt.setTimestamp(6, new Timestamp(user.getDateNaissance().getTime()));
			stmt.setTimestamp(7, new Timestamp(user.getDateCreation().getTime()));
			stmt.setTimestamp(8, new Timestamp(user.getDateModification().getTime()));
			stmt.setBoolean(9, user.isActif());
			stmt.setBoolean(10, user.isMarquerEffacer());
			stmt.setInt(11, user.getVersion());
			stmt.setInt(12, user.getIdUtilisateur());

			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, null);
		}

		return user;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		PreparedStatement stmt;
		Connection conn;

		conn = null;
		stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM Utilisateur WHERE idUtilisateur = ?");

			stmt.setInt(1, user.getIdUtilisateur());
			stmt.executeUpdate();
		} catch (Exception e) {
			return false;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, null);
		}

		return true;
	}

	@Override
	public boolean deleteUtilisateur(int id) {
		PreparedStatement stmt;
		Connection conn;
		ResultSet resSet;

		conn = null;
		stmt = null;
		resSet = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM Utilisateur WHERE idUtilisateur = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (Exception e) {
			return false;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, null);
		}
		return true;
	}

	@Override
	public Utilisateur authenticate(String identifiant, String pwd) {
		PreparedStatement stmt;
		Connection conn;
		ResultSet resSet;
		Utilisateur ret;

		stmt = null;
		conn = null;
		resSet = null;
		ret = null;

		try {
			conn = ConnectionHelper.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE identifiant = ? AND pwd = ?");

			stmt.setString(1, identifiant);
			stmt.setString(2, pwd);

			resSet = stmt.executeQuery();

			if (resSet.next())
				ret = resultSetToUser(resSet);

		} catch (Exception e) {
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, resSet);
		}

		return ret;
	}

	private Utilisateur resultSetToUser(ResultSet resSet) throws Exception {
		Utilisateur ret;
		Integer id;
		String title;
		String name;
		String lastName;
		String identifier;
		String pwd;
		Date birthDate;
		Date creationDate;
		Date modificationDate;
		boolean active;
		boolean erase;
		Integer version;

		id = resSet.getInt(1);
		title = resSet.getString(2);
		name = resSet.getString(3);
		lastName = resSet.getString(4);
		identifier = resSet.getString(5);
		pwd = resSet.getString(6);
		birthDate = new Date(resSet.getTimestamp(7).getTime());
		creationDate = new Date(resSet.getTimestamp(8).getTime());
		modificationDate = new Date(resSet.getTimestamp(9).getTime());
		active = resSet.getBoolean(10);
		erase = resSet.getBoolean(11);
		version = resSet.getInt(12);

		ret = new Utilisateur(id, title, name, lastName, identifier, pwd, birthDate, creationDate, modificationDate,
				active, erase, version);

		return ret;
	}

	private Connection getConnection() throws Exception {
		return MySqlSingleton.getInstance().getDataSource().getConnection();
	}

	private Integer insertUserIntoTable(Utilisateur user) throws Exception {
		Connection conn;
		PreparedStatement stmt;
		ResultSet key;

		conn = getConnection();
		stmt = conn.prepareStatement(
				"INSERT INTO Utilisateur(civilite, prenom, nom, identifiant, motPasse, dateNaissance, dateCreation, dateModification, actif, marquerEffacer, version) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, user.getCivilite());
		stmt.setString(2, user.getPrenom());
		stmt.setString(3, user.getNom());
		stmt.setString(4, user.getIdentifiant());
		stmt.setString(5, user.getMotPasse());
		stmt.setTimestamp(6, new Timestamp(user.getDateNaissance().getTime()));
		stmt.setTimestamp(7, new Timestamp(user.getDateCreation().getTime()));
		stmt.setTimestamp(8, new Timestamp(user.getDateModification().getTime()));
		stmt.setBoolean(9, user.isActif());
		stmt.setBoolean(10, user.isMarquerEffacer());
		stmt.setInt(11, user.getVersion());

		stmt.executeUpdate();
		key = stmt.getGeneratedKeys();
		key.next();

		return key.getInt(1);
	}

	private void insertAdressesIntoTable(List<Adresse> addrs, Integer userId) throws Exception {
		Iterator<Adresse> it;

		it = addrs.iterator();
		while (it.hasNext()) {
			insertAddressIntoTable(it.next(), userId);
		}
	}

	private void insertAddressIntoTable(Adresse addr, Integer userId) throws Exception {
		Connection conn;
		PreparedStatement stmt;

		conn = getConnection();
		stmt = conn.prepareStatement(
				"INSERT INTO Adresse(idUtilisateur, rue, codePostal, ville, pays, principale, version) VALUES (?,?,?,?,?,?,?)");

		stmt.setInt(1, userId);
		stmt.setString(2, addr.getRue());
		stmt.setString(3, addr.getCodePostal());
		stmt.setString(4, addr.getVille());
		stmt.setString(5, addr.getPays());
		stmt.setBoolean(6, addr.isPrincipale());
		stmt.setInt(7, addr.getVersion());

		stmt.executeUpdate();
	}

	private List<Adresse> getAddrUser(int userId) {
		List<Adresse> ret;
		Connection conn;
		PreparedStatement stmt;
		ResultSet resSet;

		resSet = null;
		conn = null;
		stmt = null;

		ret = new ArrayList<>();

		try {
			conn = ConnectionHelper.getConnection();
			stmt = conn.prepareStatement("SELECT Adresse.* FROM Adresse WHERE idUtilisateur = ?");

			stmt.setInt(1, userId);

			resSet = stmt.executeQuery();
			while (resSet.next()) {
				ret.add(resultSetToAdresse(resSet));
			}
		} catch (Exception e) {
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(conn, stmt, resSet);
		}

		if (ret.isEmpty())
			ret = null;

		return ret;
	}

	private Adresse resultSetToAdresse(ResultSet resSet) throws Exception {
		Adresse ret;
		int idAddr;
		int idUser;
		String street;
		String city;
		String postalCode;
		String country;
		boolean main;
		int version;

		idAddr = resSet.getInt(1);
		idUser = resSet.getInt(2);
		street = resSet.getString(3);
		postalCode = resSet.getString(4);
		city = resSet.getString(5);
		country = resSet.getString(6);
		main = resSet.getBoolean(7);
		version = resSet.getInt(8);

		ret = new Adresse(idAddr, street, postalCode, city, country, main, version, idUser);

		return ret;
	}

}