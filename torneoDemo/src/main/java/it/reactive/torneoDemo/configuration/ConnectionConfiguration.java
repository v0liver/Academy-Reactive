package it.reactive.torneoDemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@Scope("prototype")
public class ConnectionConfiguration {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    private Connection con;

    @PostConstruct
    public void init() throws Exception {
        con = DriverManager.getConnection(url, user, password);
        System.out.println(con);
        con.setAutoCommit(false);
    }

    public Connection getConnection() {
        return con;
    }
}