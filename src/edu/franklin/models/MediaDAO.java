package edu.franklin.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;

import edu.franklin.beans.GenericItemCount;
import edu.franklin.beans.Genre;
import edu.franklin.beans.MediaItem;
import edu.franklin.beans.MediaType;
import edu.franklin.beans.PurchaseInfo;
import edu.franklin.beans.PurchaseInfoMediaItem;

public class MediaDAO extends HttpServlet {

	/**
     *
     */
	private static final long serialVersionUID = 1784237977125250944L;

	/**
	 * this is the format for the table creation
	 *
	 * create table MovieManager (
	 *
	 */

	// private ArrayList<MovieInfo> movieList;
	// String URL = "jdbc:mysql://localhost:3306";
	// String dbName = "root";
	// String dbPassword = "1Detail!";
	String dbURL = "";
	String dbName = "";
	String dbPassword = "";

	public String getURL() {
		return this.dbURL;
	}

	public void setURL(String uRL) {
		this.dbURL = uRL;
	}

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPassword() {
		return this.dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	private Connection con;

	// constructor
	public MediaDAO(String URL, String user_name, String password) {
		// this.movieList = new ArrayList<MovieInfo>();
		this.dbURL = URL;
		this.dbName = user_name;
		this.dbPassword = password;

		this.getConnection();
	}

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			System.out.println("URL is:" + this.dbURL);

			System.out.println("dbName is:" + this.dbName);

			System.out.println("dbPassword is:" + this.dbPassword);

			this.con = DriverManager.getConnection(this.getURL(), this.getDbName(), this.getDbPassword());
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		System.out.println("connection ending");

		return this.con;
	}

	public boolean verifyAuthentication(String userIn, String pwdIn) {
		boolean verified = false;

		try {
			String sql = "SELECT * FROM MediaManager.UserDetails WHERE USERNAME = \"" + userIn + "\" AND PASSWORD = \"" + pwdIn + "\"";

			System.out.println("sql:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				verified = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return verified;
	}

	public ArrayList<GenericItemCount> returnGenreItemsCount() {
		ArrayList<GenericItemCount> itemCounts = new ArrayList<>();

		try {
			String sql = "SELECT g.ID, g.GenreDescription, count(mi.ID) 'count' FROM MediaManager.Genre g LEFT OUTER JOIN MediaManager.MediaItem mi ON mi.GenreID = g.ID GROUP BY GenreDescription, ID ORDER BY GenreDescription";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				GenericItemCount itemCount = new GenericItemCount();

				itemCount.setID(rs.getInt("ID"));
				itemCount.setDescription(rs.getString("GenreDescription"));
				itemCount.setCount(rs.getInt("count"));

				itemCounts.add(itemCount);

				System.out.println("added itemCount:" + itemCount.getDescription());
			}

			System.out.println("itemCounts:" + itemCounts.size());
		} catch (Exception e) {
			System.out.println(e);
		}

		return itemCounts;
	}

	public ArrayList<GenericItemCount> returnMediaTypeCount() {
		ArrayList<GenericItemCount> itemCounts = new ArrayList<>();

		try {
			String sql = "SELECT mt.ID, mt.MediaTypeDescription, count(mi.ID) 'count' FROM MediaManager.MediaType mt LEFT OUTER JOIN MediaManager.MediaItem mi ON mi.MediaTypeID = mt.ID GROUP BY MediaTypeDescription, ID ORDER BY MediaTypeDescription";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				GenericItemCount itemCount = new GenericItemCount();

				itemCount.setID(rs.getInt("ID"));
				itemCount.setDescription(rs.getString("MediaTypeDescription"));
				itemCount.setCount(rs.getInt("count"));

				itemCounts.add(itemCount);

				System.out.println("added itemCount:" + itemCount.getDescription());
			}

			System.out.println("itemCounts:" + itemCounts.size());
		} catch (Exception e) {
			System.out.println(e);
		}

		return itemCounts;
	}

	public ArrayList<GenericItemCount> returnMediaTypeItemsCount() {
		ArrayList<GenericItemCount> itemCounts = new ArrayList<>();

		try {
			String sql = "SELECT mt.ID, mt.MediaTypeDescription, count(mi.ID) 'count' FROM MediaManager.MediaType mt LEFT OUTER JOIN MediaManager.MediaItem mi ON mi.MediaTypeID = mt.ID GROUP BY MediaTypeDescription, ID ORDER BY MediaTypeDescription";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				GenericItemCount itemCount = new GenericItemCount();

				itemCount.setID(rs.getInt("ID"));
				itemCount.setDescription(rs.getString("MediaTypeDescription"));
				itemCount.setCount(rs.getInt("count"));

				itemCounts.add(itemCount);

				System.out.println("added itemCount:" + itemCount.getDescription());
			}

			System.out.println("itemCounts:" + itemCounts.size());
		} catch (Exception e) {
			System.out.println(e);
		}

		return itemCounts;

	}

	public ArrayList<GenericItemCount> returnGenreCount() {
		ArrayList<GenericItemCount> itemCounts = new ArrayList<>();

		try {
			String sql = "SELECT g.ID, g.GenreDescription, count(mi.ID) 'count' FROM MediaManager.Genre g LEFT OUTER JOIN MediaManager.MediaItem mi ON mi.GenreID = mt.ID GROUP BY MediaTypeDescription, ID ORDER BY GenreDescription";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				GenericItemCount itemCount = new GenericItemCount();

				itemCount.setID(rs.getInt("ID"));
				itemCount.setDescription(rs.getString("GenreDescription"));
				itemCount.setCount(rs.getInt("count"));

				itemCounts.add(itemCount);

				System.out.println("added itemCount:" + itemCount.getDescription());
			}

			System.out.println("itemCounts:" + itemCounts.size());
		} catch (Exception e) {
			System.out.println(e);
		}

		return itemCounts;
	}

	public ArrayList<PurchaseInfoMediaItem> returnPurchaseInfoMediaItemsCount() {
		ArrayList<PurchaseInfoMediaItem> itemCounts = new ArrayList<>();

		try {
			String sql = "SELECT * " + "FROM MediaManager.MediaItem mi " + "LEFT OUTER JOIN MediaManager.purchaseinfoMediaItem pimi ON pimi.ID = mi.ID";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				PurchaseInfoMediaItem mpii = new PurchaseInfoMediaItem();
				MediaItem mi = new MediaItem();

				mi.setID(rs.getInt("ID"));
				mi.setGenreID(rs.getInt("GenreID"));
				mi.setMediaTypeID(rs.getInt("MediaTypeID"));
				mi.setName(rs.getString("Name"));
				mi.setYear(rs.getInt("Year"));
				mi.setComments(rs.getString("Comments"));
				mi.setCurrentValue(rs.getDouble("CurrentValue"));

				mpii.setPurchasePrice(rs.getDouble("PurchasePrice"));
				mpii.setPurchaseDate(rs.getDate("PurchaseDate"));
				mpii.setPurchaseInfoID(rs.getInt("PurchaseInfoID"));
				mpii.setMediaItemID(rs.getInt("MediaItemID"));

				mpii.setMediaItem(mi);

				itemCounts.add(mpii);
			}

			System.out.println("itemCounts:" + itemCounts.size());
		} catch (Exception e) {
			System.out.println(e);
		}

		return itemCounts;

	}

	public ArrayList<GenericItemCount> returnPurchaseInfoItemsCounts() {
		ArrayList<GenericItemCount> itemCounts = new ArrayList<>();

		try {
			String sql = "SELECT pi.id, pi.PurchaseLocation,	count(pi.id) 'count'FROM MediaManager.PurchaseInfo pi LEFT OUTER JOIN MediaManager.PurchaseInfoMediaItem pim ON pim.PurchaseInfoID = pi.ID GROUP BY PurchaseLocation ORDER BY PurchaseLocation";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				GenericItemCount itemCount = new GenericItemCount();

				itemCount.setID(rs.getInt("ID"));
				itemCount.setDescription(rs.getString("PurchaseLocation"));
				itemCount.setCount(rs.getInt("count"));

				itemCounts.add(itemCount);

				System.out.println("added itemCount:" + itemCount.getDescription());
			}

			System.out.println("itemCounts:" + itemCounts.size());
		} catch (Exception e) {
			System.out.println(e);
		}

		return itemCounts;
	}

	public int getMediaTypeIdByName(String type) {
		int id = -1;
		try {
			String sql = "SELECT * FROM MediaManager.MediaType WHERE MediaTypeDescription like '%" + type + "%'";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				id = rs.getInt("ID");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return id;
	}

	public int getGenreIdByName(String Genre) {
		int id = -1;
		try {
			String sql = "SELECT * FROM MediaManager.Genre WHERE GenreDescription like '%" + Genre + "%'";

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				id = rs.getInt("ID");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return id;

	}

	public ArrayList<MediaItem> returnAllMediaItems(String mediaItemName) {
		ArrayList<MediaItem> mediaList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.MediaItem WHERE name like '%" + mediaItemName + "%'";

			System.out.println("sql:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				MediaItem mediaItem = new MediaItem();

				mediaItem.setID(rs.getInt("ID"));
				mediaItem.setName(rs.getString("Name"));
				mediaItem.setGenreID(rs.getInt("GenreID"));
				mediaItem.setMediaTypeID(rs.getInt("MediaTypeID"));
				mediaItem.setYear(rs.getInt("Year"));
				mediaItem.setComments(rs.getString("Comments"));
				mediaItem.setCurrentValue(rs.getDouble("CurrentValue"));

				mediaList.add(mediaItem);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return mediaList;
	}

	public ArrayList<MediaItem> returnAllMediaItemsPool(String mediaItemName) {
		//http://viralpatel.net/blogs/database-connection-pooling-tomcat-eclipse-db/ - approach used, probably legacy method
		//https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html - alt approach not used, probably new method
		
		ArrayList<MediaItem> mediaList = new ArrayList<>();

		try {
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			
			DataSource datasource = (DataSource) envContext.lookup("jdbc/MediaManagerFinalDB");

			Connection con = null;

			con = datasource.getConnection();

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM MediaManager.MediaItem WHERE name like '%" + mediaItemName + "%'");

			while (rs.next()) {
				MediaItem mediaItem = new MediaItem();

				mediaItem.setID(rs.getInt("ID"));
				mediaItem.setName(rs.getString("Name"));
				mediaItem.setGenreID(rs.getInt("GenreID"));
				mediaItem.setMediaTypeID(rs.getInt("MediaTypeID"));
				mediaItem.setYear(rs.getInt("Year"));
				mediaItem.setComments(rs.getString("Comments"));
				mediaItem.setCurrentValue(rs.getDouble("CurrentValue"));

				mediaList.add(mediaItem);
			}

			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
		}

		return mediaList;
	}

	public ArrayList<MediaItem> returnMediaItemDetail(int ID) {
		ArrayList<MediaItem> mediaList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.MediaItem mi " + "LEFT OUTER JOIN MediaManager.Genre g ON g.ID = mi.GenreID " + "LEFT OUTER JOIN MediaManager.MediaType mt ON mt.ID = mi.MediaTypeID " + "WHERE mi.ID = " + ID;

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				MediaItem mediaItem = new MediaItem();

				mediaItem.setID(rs.getInt("ID"));
				mediaItem.setName(rs.getString("Name"));
				mediaItem.setGenreID(rs.getInt("GenreID"));
				mediaItem.setMediaTypeID(rs.getInt("MediaTypeID"));
				mediaItem.setYear(rs.getInt("Year"));
				mediaItem.setComments(rs.getString("Comments"));
				mediaItem.setCurrentValue(rs.getDouble("CurrentValue"));
				mediaItem.setGenreDescription(rs.getString("GenreDescription"));
				mediaItem.setMediaTypeDescription(rs.getString("MediaTypeDescription"));

				mediaList.add(mediaItem);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return mediaList;
	}

	public int deleteMediaItemDetail(int ID) {

		try {
			Statement s = this.con.createStatement();

			String sql = "DELETE FROM MediaManager.PurchaseInfoMediaItem WHERE MediaItemID = " + ID;

			s.executeUpdate(sql);

			sql = "DELETE FROM MediaManager.MediaItem WHERE ID = " + ID;

			return s.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public int updateMediaItemDetail(MediaItem mi) {
		try {
			Statement s = this.con.createStatement();

			String sql = "UPDATE MediaManager.MediaItem SET  " + " Name = '" + mi.getName() + "', " + " GenreID = '" + mi.getGenreID() + "', " + " MediaTypeID = '" + mi.getMediaTypeID() + "', " + " Year = '" + mi.getYear() + "', " + " Comments = '"
					+ mi.getComments() + "', " + " CurrentValue = '" + mi.getCurrentValue() + "' " + "WHERE ID = " + mi.getID();

			System.out.println("GenreID:" + mi.getGenreID());

			s.executeUpdate(sql);

			System.out.println("GenreID:" + mi.getGenreID());

		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public int insertMediaItemDetail(MediaItem mi) {
		try {
			Statement s = this.con.createStatement();

			String sql = "INSERT INTO MediaManager.MediaItem SET  " + " GenreID = '" + mi.getGenreID() + "', " + " MediaTypeID = '" + mi.getMediaTypeID() + "', " + " Name = '" + mi.getName() + "', " + " Year = '" + mi.getYear() + "', " + " Comments = '"
					+ mi.getComments() + "', " + " CurrentValue = '" + mi.getCurrentValue() + "' ";
			s.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public ArrayList<Genre> returnAllGenreItems(String GenreItemName) {
		ArrayList<Genre> GenreList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.Genre WHERE GenreDescription like '%" + GenreItemName + "%'";

			System.out.println("sql:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Genre GenreItem = new Genre();

				GenreItem.setGenreID(rs.getInt("ID"));
				System.out.println("Genre:" + GenreItem.getGenreID());

				GenreItem.setGenreDescription(rs.getString("GenreDescription"));
				System.out.println("GenreDescription:" + GenreItem.getGenreDescription());

				GenreList.add(GenreItem);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return GenreList;
	}

	public ArrayList<Genre> returnGenreItem(int ID) {
		ArrayList<Genre> GenreList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.Genre " + "WHERE ID = " + ID;

			System.out.println("sql:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Genre mediaItem = new Genre();

				mediaItem.setGenreID(rs.getInt("ID"));
				System.out.println("Genre:" + mediaItem.getGenreID());
				mediaItem.setGenreDescription(rs.getString("GenreDescription"));
				System.out.println("GenreDescription:" + mediaItem.getGenreDescription());

				GenreList.add(mediaItem);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return GenreList;
	}

	public int deleteGenreItemDetail(int ID) {

		try {
			Statement s = this.con.createStatement();

			String sql = "UPDATE MediaManager.MediaItem SET GenreID = " + (ID - 1) + " WHERE GenreID = " + ID;

			s.executeUpdate(sql);

			sql = "DELETE FROM MediaManager.Genre WHERE ID = " + ID;

			return s.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public int updateGenreItemDetail(Genre g) {
		try {
			Statement s = this.con.createStatement();

			String sql = "UPDATE MediaManager.Genre SET  " + " GenreDescription = '" + g.getGenreDescription() + "' " + "WHERE ID = " + g.getGenreID();

			System.out.println("GenreID:" + g.getGenreID());

			s.executeUpdate(sql);

			System.out.println("GenreID:" + g.getGenreID());

		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public int insertGenreItemDetail(Genre g) {
		try {
			Statement s = this.con.createStatement();

			String sql = "INSERT INTO MediaManager.Genre SET  " + " GenreDescription = '" + g.getGenreDescription() + "' ";

			System.out.println("sql:" + sql);

			s.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public ArrayList<MediaType> returnAllMediaTypes(String mediaItemName) {
		ArrayList<MediaType> mediaTypeList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.MediaType WHERE MediaTypeDescription like '%" + mediaItemName + "%'";

			System.out.println("sql returnAll:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				MediaType mediaType = new MediaType();

				mediaType.setMediaID(rs.getInt("ID"));
				System.out.println("mediaType:" + mediaType.getMediaID());

				mediaType.setMediaTypeDescription(rs.getString("mediaTypeDescription"));
				System.out.println("mediaTypeDescription:" + mediaType.getMediaTypeDescription());

				mediaTypeList.add(mediaType);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return mediaTypeList;
	}

	public ArrayList<MediaType> returnMediaType(int ID) {
		ArrayList<MediaType> mediaTypeList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.MediaType " + "WHERE ID = " + ID;

			System.out.println("sql returnIndividual:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				MediaType mediaType = new MediaType();

				mediaType.setMediaID(rs.getInt("ID"));
				System.out.println("mediaID:" + mediaType.getMediaID());

				mediaType.setMediaTypeDescription(rs.getString("MediaTypeDescription"));
				System.out.println("GenreDescription:" + mediaType.getMediaTypeDescription());

				mediaTypeList.add(mediaType);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return mediaTypeList;
	}

	public int deleteMediaType(int ID) {
		int result = -1;

		try {

			Statement s = this.con.createStatement();

			System.out.println("before first deleteMediaType");

			String sql = "DELETE FROM MediaManager.MediaItem WHERE MediaTypeID = " + ID;

			System.out.println("sql delete:" + sql);

			result = s.executeUpdate(sql);

			System.out.println("after first deleteMediaType");

			System.out.println("before second deleteMediaType");

			sql = "DELETE FROM MediaManager.MediaType WHERE ID = " + ID;

			System.out.println("sql delete:" + sql);

			result = s.executeUpdate(sql);

			System.out.println("after second deleteMediaType");

			return result;

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	public int updateMediaType(MediaType mt) {
		try {
			Statement s = this.con.createStatement();

			String sql = "UPDATE MediaManager.MediaType SET  " + " MediaTypeDescription = '" + mt.getMediaTypeDescription() + "' " + "WHERE ID = " + mt.getMediaID();

			System.out.println("sql update:" + sql);

			System.out.println("MediaID:" + mt.getMediaID());

			s.executeUpdate(sql);

			System.out.println("MediaID:" + mt.getMediaID());

		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public int insertMediaType(MediaType mt) {
		try {
			Statement s = this.con.createStatement();

			String sql = "INSERT INTO MediaManager.MediaType SET  " + " MediaTypeDescription = '" + mt.getMediaTypeDescription() + "' ";

			System.out.println("sql insert:" + sql);

			s.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public ArrayList<PurchaseInfo> returnAllPurchaseInfo(String purchaseInfoItemName) {
		ArrayList<PurchaseInfo> purchaseInfoList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.PurchaseInfo WHERE PurchaseLocation like '%" + purchaseInfoItemName + "%'";

			System.out.println("sql returnAll Purchase:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				PurchaseInfo purchaseType = new PurchaseInfo();

				purchaseType.setPurchaseID(rs.getInt("ID"));
				System.out.println("purchaseType:" + purchaseType.getPurchaseID());

				purchaseType.setPurchaseLocation(rs.getString("PurchaseLocation"));
				System.out.println("PurchaseLocation:" + purchaseType.getPurchaseLocation());

				purchaseInfoList.add(purchaseType);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return purchaseInfoList;
	}

	public ArrayList<PurchaseInfo> returnPurchaseInfo(int ID) {
		ArrayList<PurchaseInfo> purchaseInfoList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MediaManager.PurchaseInfo " + "WHERE ID = " + ID;

			System.out.println("sql returnIndividual:" + sql);

			Statement s = this.con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				PurchaseInfo mediaType = new PurchaseInfo();

				mediaType.setPurchaseID(rs.getInt("ID"));
				System.out.println("PurchaseID:" + mediaType.getPurchaseID());

				mediaType.setPurchaseLocation(rs.getString("PurchaseLocation"));
				System.out.println("PurchaseLocation:" + mediaType.getPurchaseLocation());

				purchaseInfoList.add(mediaType);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return purchaseInfoList;
	}

	public int deletePurchaseInfo(int ID) {

		try {
			Statement s = this.con.createStatement();

			String sql = "DELETE FROM MediaManager.PurchaseInfoMediaItem WHERE PurchaseInfoID = " + ID;

			s.executeUpdate(sql);

			sql = "DELETE FROM MediaManager.PurchaseInfo WHERE ID = " + ID;

			s.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public int updatePurchaseInfo(PurchaseInfo pi) {
		try {
			Statement s = this.con.createStatement();

			String sql = "UPDATE MediaManager.PurchaseInfo SET  " + " PurchaseLocation = '" + pi.getPurchaseLocation() + "' " + "WHERE ID = " + pi.getPurchaseID();

			System.out.println("sql update:" + sql);

			System.out.println("MediaID:" + pi.getPurchaseID());

			s.executeUpdate(sql);

			System.out.println("MediaID:" + pi.getPurchaseID());

		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}

	public int insertPurchaseInfo(PurchaseInfo pi) {
		try {
			Statement s = this.con.createStatement();

			String sql = "INSERT INTO MediaManager.PurchaseInfo SET  " + " PurchaseLocation = '" + pi.getPurchaseLocation() + "' ";

			System.out.println("sql insert:" + sql);

			s.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}

		return -1;
	}
}