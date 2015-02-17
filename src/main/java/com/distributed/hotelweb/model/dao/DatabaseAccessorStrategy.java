/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.hotelweb.model.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interface for database access, provides a method to get a number records from a single table
 * @author Nathaniel Davis
 */
public interface DatabaseAccessorStrategy {

    /**
     * Returns up to a given number of records from a database table
     * @param tableName The table to retrieve records from
     * @param maxRecords The maximum number of records to retrieve
     * @return A List containing Map objects representing each row retrieved from the database
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws NullPointerException 
     */
    public abstract List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) 
            throws ClassNotFoundException, SQLException, IOException, NullPointerException;
    public abstract List<Map<String, Object>> getSelectedRecords(String tableName, String condition, int maxRecords) 
            throws ClassNotFoundException, SQLException, IOException, NullPointerException;
    public abstract void insertRow(String tableName, String columns, String values)
            throws ClassNotFoundException, SQLException, IOException, NullPointerException;
    public abstract void updateRow(String tableName, String columns, String values, String conditions)
            throws ClassNotFoundException, SQLException, IOException, NullPointerException;
    public abstract void deleteRow(String tableName, String conditions)
            throws ClassNotFoundException, SQLException, IOException, NullPointerException;
    
}
