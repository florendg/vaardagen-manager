package dev.vultureweb.vaardagen.manager.persistence;

import jakarta.ejb.Stateless;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

@Stateless
public class ConnectionProviderBean {
   private static final System.Logger LOG = System.getLogger(ConnectionProviderBean.class.getName());

   public Connection connect() throws Exception {
      InitialContext initialContext = new InitialContext();
      DataSource dataSource = (DataSource) initialContext.lookup("java:/PostgresDS");
      Connection connection = dataSource.getConnection();
      LOG.log(System.Logger.Level.INFO, "Connection established");
      return connection;
   }
}
