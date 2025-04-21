package dev.vultureweb.vaardagen.manager.persistence;

import jakarta.ejb.Stateless;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Stateless
public class ConnectionProviderBean {
   private static final System.Logger LOG = System.getLogger(ConnectionProviderBean.class.getName());

   /**
    * Connect to the database
    * @return Connection to the database
    * @throws ConnectionProviderBeanException if the connection could not be established
    */
   public Connection connect()  throws ConnectionProviderBeanException {
      try {
         InitialContext initialContext = new InitialContext();
         DataSource dataSource = (DataSource) initialContext.lookup("java:/PostgresDS");
         Connection connection = dataSource.getConnection();
         LOG.log(System.Logger.Level.INFO, "Connection established");
         return connection;
      }  catch (NamingException | SQLException e) {
         LOG.log(System.Logger.Level.ERROR, "Could not connect to the database");
         throw new ConnectionProviderBeanException(e);
      }
   }
}
