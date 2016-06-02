package edu.franklin.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.franklin.beans.MediaItem;

public class MediaItemDAO extends HttpServlet {

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
	public ArrayList<MediaItem> returnAllMediaItems(String mediaItemName) {
		// http://viralpatel.net/blogs/database-connection-pooling-tomcat-eclipse-db/
		// - approach used, probably legacy method
		// https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html
		// - alt approach not used, probably new method

		ArrayList<MediaItem> mediaList = new ArrayList<>();

		Connection con = null;

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			DataSource datasource = (DataSource) envContext.lookup("jdbc/MediaManagerFinalDB");

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

			// AbandonedConnectionCleanupThread.shutdown();
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

		this.dumpMediaItems(mediaList);

		return mediaList;
	}

	public void dumpMediaItems(ArrayList mediaItems) {
		//dump as JSON
		ObjectMapper jsonmapper = new ObjectMapper();

		String JSON = null;
		
		try {
			JSON = jsonmapper.writeValueAsString(mediaItems);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(JSON);

	}
}