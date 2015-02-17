/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.hotelweb.controller;

import com.distributed.hotelweb.model.dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author viewt_000
 */
public class CreateController extends HttpServlet {

    private static final String CREATE_PATH = "/View/create.html";
    private static final String COMPLETE_PATH = "/List";
    private static final String ERROR_PATH = "/error.jsp";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(response.encodeRedirectURL(CREATE_PATH));
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination = COMPLETE_PATH;
        
        
        try{
            HotelDaoStrategy hotelDAO = new HotelDao();
            HotelModel insertHotel = new HotelModel();
            insertHotel.setHotelName(request.getParameter("name"));
            insertHotel.setStreetAddress(request.getParameter("address"));
            insertHotel.setCity(request.getParameter("city"));
            insertHotel.setState(request.getParameter("state"));
            insertHotel.setPostalCode(request.getParameter("postalCode"));
            insertHotel.setNotes(request.getParameter("notes"));            
            
            hotelDAO.createNewHotel(insertHotel.getHotelName(), 
                    insertHotel.getStreetAddress(), insertHotel.getCity(),
                    insertHotel.getState(), insertHotel.getPostalCode(), insertHotel.getNotes());
        }
        catch(Exception e)
        {
            destination = ERROR_PATH;
            request.setAttribute("msg", e.getMessage());
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
