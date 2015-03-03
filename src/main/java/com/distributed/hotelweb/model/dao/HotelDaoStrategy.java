/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.hotelweb.model.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nathaniel Davis
 */
public interface HotelDaoStrategy {

    public abstract List<HotelModel> getAllHotels() throws ClassNotFoundException, SQLException, IOException;
    public abstract List<HotelModel> getSingleHotel(int hotelID) throws ClassNotFoundException, SQLException, IOException;
    public abstract List<HotelModel> getHotels(String Condition) throws ClassNotFoundException, SQLException, IOException;
    public abstract void createNewHotel(String hotelName, String streetAddress, String city, String state, String postalCode, String notes) throws ClassNotFoundException, SQLException, IOException;
    public abstract void updateHotel(int hotelID, String hotelName, String streetAddress, String city, String state, String postalCode, String notes) 
            throws ClassNotFoundException, SQLException, IOException;
    public abstract void deleteHotel(int id) throws ClassNotFoundException, SQLException, IOException;
}
