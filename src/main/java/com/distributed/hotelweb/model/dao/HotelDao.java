/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.hotelweb.model.dao;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object (DA0)
 * @author Nathaniel Davis
 * @version 0.9b
 */
public class HotelDao implements HotelDaoStrategy {
    private DatabaseAccessorStrategy db;
    private static final String UPDATE_COLUMNS = "hotel_name, street_address, city, state, postal_code, notes";
    private static final String INSERT_COLUMNS = "hotel_name, street_address, city, state, postal_code, notes";
    private static final String CONDITIONS_START = "hotel_id = ";
    private static final String TABLE_NAME = "Hotels";

    public HotelDao() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, InstantiationException, IllegalAccessException {
        this.db = DatabaseFactory.getDatabaseAccessorStrategy();
    }
    
    @Override
    /**
     * Returns up to 50 hotels
     * @return a list of up to 50 hotels
     */
    public List<HotelModel> getAllHotels() throws ClassNotFoundException, SQLException, IOException {
        List<Map<String,Object>> rawRecords = db.getAllRecords(TABLE_NAME, 50);
        List<HotelModel> records = new ArrayList<>();
        
        for(Map<String,Object> map : rawRecords) {
            HotelModel hotel = new HotelModel();
            int hotelID = (Integer)map.get("hotel_id");
            hotel.setHotelID(hotelID);
            String hotelName = (String)map.get("hotel_name");
            hotel.setHotelName(hotelName);
            String streetAddress = (String)map.get("street_address");
            hotel.setStreetAddress(streetAddress);
            String city = (String)map.get("city");
            hotel.setCity(city);
            String state = (String)map.get("state");
            hotel.setState(state);
            String postalCode = (String)map.get("postal_code");
            hotel.setPostalCode(postalCode);
            String notes = (String)map.get("notes");
            hotel.setNotes(notes);
            records.add(hotel);
        }
        
        return records;
    }
    
    @Override
    /**
     * Returns up to 50 hotels
     * @return a list of up to 50 hotels
     */
    public List<HotelModel> getSingleHotel(int hotelID) throws ClassNotFoundException, SQLException, IOException {
        String condition = CONDITIONS_START + Integer.toString(hotelID);
        List<Map<String,Object>> rawRecords = db.getSelectedRecords(TABLE_NAME, condition, 50);
        List<HotelModel> records = new ArrayList<>();
        
        for(Map<String,Object> map : rawRecords) {
            HotelModel hotel = new HotelModel();
            int modelHotelID = (Integer)map.get("hotel_id");
            hotel.setHotelID(modelHotelID);
            String hotelName = (String)map.get("hotel_name");
            hotel.setHotelName(hotelName);
            String streetAddress = (String)map.get("street_address");
            hotel.setStreetAddress(streetAddress);
            String city = (String)map.get("city");
            hotel.setCity(city);
            String state = (String)map.get("state");
            hotel.setState(state);
            String postalCode = (String)map.get("postal_code");
            hotel.setPostalCode(postalCode);
            String notes = (String)map.get("notes");
            hotel.setNotes(notes);
            records.add(hotel);
        }
        
        return records;
    }
    
    @Override
    /**
     * Returns up to 50 hotels
     * @return a list of up to 50 hotels
     */
    public List<HotelModel> getHotels(String condition) throws ClassNotFoundException, SQLException, IOException {
        List<Map<String,Object>> rawRecords = db.getSelectedRecords(TABLE_NAME, condition, 50);
        List<HotelModel> records = new ArrayList<>();
        
        for(Map<String,Object> map : rawRecords) {
            HotelModel hotel = new HotelModel();
            int modelHotelID = (Integer)map.get("hotel_id");
            hotel.setHotelID(modelHotelID);
            String hotelName = (String)map.get("hotel_name");
            hotel.setHotelName(hotelName);
            String streetAddress = (String)map.get("street_address");
            hotel.setStreetAddress(streetAddress);
            String city = (String)map.get("city");
            hotel.setCity(city);
            String state = (String)map.get("state");
            hotel.setState(state);
            String postalCode = (String)map.get("postal_code");
            hotel.setPostalCode(postalCode);
            String notes = (String)map.get("notes");
            hotel.setNotes(notes);
            records.add(hotel);
        }
        
        return records;
    }
    
    @Override
    /**
     * Creates a new hotel in the database
     */
    public void createNewHotel(String hotelName, String streetAddress, String city, String state, String postalCode, String notes) throws ClassNotFoundException, SQLException, IOException
    {
        String values =  hotelName + ", " + streetAddress + ", " + city + ", " + state + ", " + postalCode + ", " + notes;
        db.insertRow(TABLE_NAME, INSERT_COLUMNS, values);
    }
    
    /**
     * Updates a hotel in the database
     * @param hotelID
     * @param hotelName
     * @param streetAddress
     * @param postalCode
     * @param state
     * @param city
     * @param notes
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    @Override
    public void updateHotel(int hotelID, String hotelName, String streetAddress, String city, String state, String postalCode, String notes) 
            throws ClassNotFoundException, SQLException, IOException
    {
        //generate conditions
        String values = hotelName + ", " + streetAddress + ", " + city + ", " + state + ", " + postalCode + ", " + notes;
        String condition = CONDITIONS_START + Integer.toString(hotelID);
        db.updateRow(TABLE_NAME, UPDATE_COLUMNS, values, condition);
    }

    @Override
    public void deleteHotel(int id) throws ClassNotFoundException, SQLException, IOException {
        String condition = CONDITIONS_START + Integer.toString(id);
        db.deleteRow(TABLE_NAME, condition);
    }
}

