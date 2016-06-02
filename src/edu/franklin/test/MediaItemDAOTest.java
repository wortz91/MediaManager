package edu.franklin.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.franklin.beans.MediaItem;
import edu.franklin.models.MediaItemDAO;

/*
 * https://blogs.oracle.com/randystuph/entry/injecting_jndi_datasources_for_junit
 */
public class MediaItemDAOTest {
	ArrayList<MediaItem> allItems = null;
	ArrayList<MediaItem> allItemsTest = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			// rcarver - setup the jndi context and the datasource
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
			InitialContext ic = new InitialContext();

			ic.createSubcontext("java:");
			ic.createSubcontext("java:/comp");
			ic.createSubcontext("java:/comp/env");
			ic.createSubcontext("java:/comp/env/jdbc");

			// Construct DataSource
			Properties properties = new Properties();
			properties.setProperty("url", "jdbc:mysql://192.168.0.10:3306/MediaManager");
			properties.setProperty("maxActive", "10");
			properties.setProperty("maxIdle", "8");
			properties.setProperty("minIdle", "10");
			properties.setProperty("maxWait", "10");
			properties.setProperty("testOnBorrow", "true");
			properties.setProperty("username", "MediaManager");
			properties.setProperty("password", "mediamanager");
			properties.setProperty("validationQuery", "SELECT 1");
			properties.setProperty("removeAbandoned", "true");
			properties.setProperty("removeAbandonedTimeout", "1");
			properties.setProperty("logAbandoned", "true");

			DataSource ds = BasicDataSourceFactory.createDataSource(properties);

			ic.bind("java:/comp/env/jdbc/MediaManagerFinalDB", ds);
		} catch (NamingException ex) {
			Logger.getLogger(MediaItemDAOTest.class.getName()).log(Level.SEVERE, null, ex);
			//System.out.println(MediaItemDAOTest.class.getName() + ":" + ex.toString());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MediaItemDAO mid = new MediaItemDAO();

		allItems = mid.returnAllMediaItems("RUSH");
		allItemsTest = this.testMediaItemsArrayList();
		
		System.out.println("allItems has:" + allItems.size() + " items.");
		System.out.println("allItemsTest has:" + allItemsTest.size() + " items.");

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReturnAllMediaItemsSize() {
		// fail("Not yet implemented");
		assertEquals(allItemsTest.size(), allItems.size());
	}
	
	@Test
	public void testReturnAllMediaItemsClass() {
		// fail("Not yet implemented");
		assertEquals(this.testMediaItemsArrayList().getClass(), allItems.getClass());
	}
	
	@Test
	public void testReturnAllMediaItemsArray() {
		// fail("Not yet implemented");
		assertEquals(this.testMediaItemsArrayList().get(0).compareTo(allItems.get(0)), 0);
	}
	
	public ArrayList<MediaItem> testMediaItemsArrayList() {
		ArrayList<MediaItem> testMediaItems = new ArrayList<MediaItem>();
		
		//[{"genreID":4,"mediaTypeID":4,"name":"RUSH","year":1974,"comments":"First album","currentValue":4.99,"genreDescription":null,"mediaTypeDescription":null,"id":3}]

		MediaItem mi = new MediaItem();
		mi.setMediaTypeID(4);
		mi.setName("RUSH");
		mi.setYear(1974);
		mi.setComments("First album");
		mi.setCurrentValue(4.99);;
		mi.setGenreDescription(null);
		mi.setMediaTypeDescription(null);
		mi.setID(3);
		
		testMediaItems.add(mi);
		
		return testMediaItems;
	}
}
