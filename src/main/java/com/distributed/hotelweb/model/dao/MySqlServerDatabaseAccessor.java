/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.hotelweb.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nathaniel Davis
 */
public class MySqlServerDatabaseAccessor implements DatabaseAccessorStrategy {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public MySqlServerDatabaseAccessor() {
    }
    
    private void openConnection() 
            throws ClassNotFoundException, SQLException, 
            IOException {
        
        conn = DatabaseFactory.getConnection();
    }
    
    private void closeConnection() throws SQLException {
        stmt.close();
        conn.close();
    }
    
    @Override
    public List<Map<String,Object>> getAllRecords(String tableName, int maxRecords) 
            throws ClassNotFoundException, SQLException, IOException, NullPointerException {
             
        openConnection();

        List<Map<String,Object>> records = new ArrayList<>();
        stmt = conn.createStatement();
        String sql = "select * from " + tableName + " limit " + maxRecords;
        rs = stmt.executeQuery(sql);
        
        ResultSetMetaData metaData = rs.getMetaData();	
        final int fields = metaData.getColumnCount();

        while( rs.next() ) {
                Map<String,Object> record = new HashMap<>();
                for( int i=1; i <= fields; i++ ) {
                    record.put( metaData.getColumnName(i), rs.getObject(i) );
                } // end for
                records.add(record);
        } // end while
        
        closeConnection();
        
        return records;
    }
    
    @Override
    public List<Map<String,Object>> getSelectedRecords(String tableName, String condition, int maxRecords) 
            throws ClassNotFoundException, SQLException, IOException, NullPointerException {
        List<Map<String,Object>> records = new ArrayList<>();
        try{
            openConnection();

            stmt = conn.createStatement();
            String sql = "select * from " + tableName + " where " + condition + " limit " + maxRecords;
            rs = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();	
            final int fields = metaData.getColumnCount();

            while( rs.next() ) {
                    Map<String,Object> record = new HashMap<>();
                    for( int i=1; i <= fields; i++ ) {
                        record.put( metaData.getColumnName(i), rs.getObject(i) );
                    } // end for
                    records.add(record);
            } // end while
        }
        finally{
        closeConnection();
        
        }
        return records;
    }
    
    @Override
    public void insertRow(String tableName, String columns, String values)
            throws ClassNotFoundException, SQLException, IOException, NullPointerException
    {
        try{
            openConnection();

            stmt = conn.createStatement();

            String[] columnsArr = columns.trim().split(",");
            String[] valuesArr = values.trim().split(",");
            if(columnsArr.length != valuesArr.length)
            {
                throw new IllegalArgumentException("Number of columns and values do not match!");
            }

            String sql = "Insert into " + tableName + "(" + columns + ") values(";

            for(int i = 0; i < columnsArr.length; i++)
            {
                 sql = sql + "'" + valuesArr[i].trim() + "'" + (i < columnsArr.length - 1 ? "," : "");
            }
            
            sql = sql + ")";
            
            stmt.executeUpdate(sql);
        }
        finally{
            closeConnection();
        }
    }

    @Override
    public void updateRow(String tableName, String columns, String values, String conditions) 
            throws ClassNotFoundException, SQLException, IOException, NullPointerException 
    {
        try
        {
            openConnection();

            stmt = conn.createStatement();

            //generate statement the hard way
            String sql = "UPDATE " + tableName;

            String[] columnsArr = columns.trim().split(",");
            String[] valuesArr = values.trim().split(",");
            if(columnsArr.length != valuesArr.length)
            {
                throw new IllegalArgumentException("Number of columns and values do not match!");
            }

            sql = sql + " SET ";

            for(int i = 0; i < columnsArr.length; i++)
            {
                 sql = sql + columnsArr[i].trim() + " = '" + valuesArr[i].trim() + "'" + (i < columnsArr.length - 1 ? "," : "");
            }

            sql = sql + " WHERE " + conditions;

            System.out.println(sql);

            stmt.executeUpdate(sql);
        }
        finally
        {
            closeConnection();
        }
    }
    
    @Override
    public void deleteRow(String tableName, String conditions) throws ClassNotFoundException, SQLException, IOException
    {
        try
        {
            openConnection();
            
            stmt = conn.createStatement();
            
            String sql = "DELETE FROM " + tableName;
            sql = sql + " WHERE " + conditions;
            
            stmt.executeUpdate(sql);
        }
        finally
        {
            closeConnection();
        }
    }
}
