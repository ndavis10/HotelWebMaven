/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.hotelweb.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This ExternalSpecFactory decouples the specifications for building
 * Reader and Writer objects from the Factory class. Instead, the specs are
 * read from an external text file that has key=value pairs providing the
 * specifications. Once read in, the factory class can build objects using
 * Java Reflection techniques.
 * 
 * IMPORTANT: to run this program you must place the config.properties file
 * in the appropriate directory and then modify the file path below to
 * use that directory.
 *
 * @author jlombardo
 */
public final class DatabaseFactory {
    private DatabaseFactory() {}

    public static Connection getConnection() 
            throws ClassNotFoundException, SQLException, 
            FileNotFoundException, IOException {
        
        Connection conn = null;

        // First read config setting in properties file
//        File file = new File("/temp/config.properties");
        File file = new File("../conf/Catalina/localhost/dbconfig.properties");
        System.out.println(file.getAbsolutePath());
        Properties props = new Properties();
        FileInputStream inFile;
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();
        
            // Next use Java reflection to create instance
            String driverClass = props.getProperty("driver.class");
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            
            Class.forName (driverClass);
            conn = DriverManager.getConnection(url, username, password);
            
        
        return conn;
    }


    public static DatabaseAccessorStrategy getDatabaseAccessorStrategy() 
            throws ClassNotFoundException, SQLException, 
            FileNotFoundException, IOException, InstantiationException, IllegalAccessException {

        // First read config setting in properties file
//        File file = new File("/temp/config.properties");
        File file = new File("../conf/Catalina/localhost/dbconfig.properties");
        System.out.println(file.getAbsolutePath());
        Properties props = new Properties();
        FileInputStream inFile;
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();
        
            // Next use Java reflection to create instance
            String dbClass = props.getProperty("db.accessor");
            Class clazz = Class.forName(dbClass);

        return (DatabaseAccessorStrategy)clazz.newInstance();
    }
   
    /* public static TicketDaoStrategy getTicketDaoStrategy() 
            throws ClassNotFoundException, SQLException, 
            FileNotFoundException, IOException, InstantiationException, IllegalAccessException {

        // First read config setting in properties file
//        File file = new File("/temp/config.properties");
        File file = new File("src/dbconfig.properties");
        Properties props = new Properties();
        FileInputStream inFile;
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();
        
            // Next use Java reflection to create instance
            String dbClass = props.getProperty("db.ticket.dao");
            Class clazz = Class.forName(dbClass);

        return (TicketDaoStrategy)clazz.newInstance();
    } */
}
