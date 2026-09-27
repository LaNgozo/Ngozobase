import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * DatabaseConnection class handles MS Access database connectivity
 * Uses UcanAccess JDBC driver for cross-platform compatibility
 * 
 * @author Mukelwe
 */
public class DatabaseConnection {
    
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    private static Connection connection = null;
    
    // Database configuration
    private static final String DB_URL_PREFIX = "jdbc:ucanaccess://";
    private static final String DB_NAME = "ngozobase.accdb";  // Change to your MS Access file name
    private static final String DB_PATH = "./" + DB_NAME;    // Database file path (relative to project root)
    
    /**
     * Establishes connection to MS Access database
     * @return Connection object or null if connection fails
     */
    public static Connection getConnection() {
        try {
            // Check if connection already exists and is valid
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            
            // Load UcanAccess JDBC driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            // Create database URL
            String url = DB_URL_PREFIX + DB_PATH;
            
            // Establish connection
            connection = DriverManager.getConnection(url);
            logger.log(Level.INFO, "Database connection established successfully");
            
            return connection;
            
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "UcanAccess JDBC Driver not found. " +
                "Make sure the driver is in your classpath.", ex);
            return null;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Failed to connect to database: " + DB_PATH, ex);
            return null;
        }
    }
    
    /**
     * Closes the database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.log(Level.INFO, "Database connection closed");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error closing database connection", ex);
        }
    }
    
    /**
     * Executes a SELECT query and returns ResultSet
     * @param sql SQL query string
     * @return ResultSet or null if query fails
     */
    public static ResultSet executeQuery(String sql) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                Statement statement = conn.createStatement();
                return statement.executeQuery(sql);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error executing query: " + sql, ex);
        }
        return null;
    }
    
    /**
     * Executes INSERT, UPDATE, or DELETE queries
     * @param sql SQL update query
     * @return number of rows affected
     */
    public static int executeUpdate(String sql) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                Statement statement = conn.createStatement();
                return statement.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error executing update: " + sql, ex);
        }
        return -1;
    }
    
    /**
     * Executes a prepared statement (safer for parameterized queries)
     * @param sql SQL query with placeholders (?)
     * @param params Query parameters
     * @return ResultSet or null if query fails
     */
    public static ResultSet executePreparedQuery(String sql, Object[] params) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                // Set parameters
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                
                return pstmt.executeQuery();
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error executing prepared query: " + sql, ex);
        }
        return null;
    }
    
    /**
     * Executes a prepared update statement
     * @param sql SQL update query with placeholders (?)
     * @param params Query parameters
     * @return number of rows affected
     */
    public static int executePreparedUpdate(String sql, Object[] params) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                // Set parameters
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                
                return pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error executing prepared update: " + sql, ex);
        }
        return -1;
    }
    
    /**
     * Tests the database connection
     * Run this to verify database is accessible
     */
    public static void testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("✓ Database connection successful!");
                closeConnection();
            } else {
                System.out.println("✗ Database connection failed!");
            }
        } catch (Exception ex) {
            System.out.println("✗ Connection test error: " + ex.getMessage());
        }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        testConnection();
    }
}
