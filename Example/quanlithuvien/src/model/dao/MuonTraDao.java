package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Members;

public class MuonTraDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();

	public MuonTraDao() {

	}

	public List<String> getAllMaMuonTraByMaBanDoc(String idMember) {
		String sql = "SELECT * FROM muontra WHERE mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listMaMuonTra = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idMember);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listMaMuonTra.add(resultSet.getString("mamuontra"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listMaMuonTra;
		}
	}

	public boolean removeMaMuonTraByMaBanDoc(String idMember) {
		String sql = "UPDATE muontra SET deleted = ? WHERE  mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setString(2, idMember);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		CTMuonTraDao ctMuonTraDao = new CTMuonTraDao();
		List<String> listMaMuonTraDao = getAllMaMuonTraByMaBanDoc(idMember);
		for (String maMuonTraDao : listMaMuonTraDao) {
			ctMuonTraDao.removeMaCTMuonTraByMaMuonTra(maMuonTraDao);
		}
		return status > 0 ? true : false;
	}

	public boolean borrowBooks(String idBanDoc, String idSach, String ngayMuon) {
		String sql = "INSERT INTO muontra (mabandoc, masach, ngaymuon, ngaytra, datra, deleted ) VALUES(?, ?, ?, ?, ?, ?)";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idBanDoc);
			statement.setString(2, idSach);
			statement.setString(3, ngayMuon);
			statement.setString(4, null);
			statement.setInt(5, 0);
			statement.setInt(6, 0);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean returnBooks(String idBanDoc, String idSach, String ngayTra) {
		String sql = "UPDATE muontra SET ngaytra = ?, datra = ?  WHERE  mabandoc = ? and  masach = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, ngayTra);
			statement.setInt(2, 1);
			statement.setString(3, idBanDoc);
			statement.setString(4, idSach);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

}
