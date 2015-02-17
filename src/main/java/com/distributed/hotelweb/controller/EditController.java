/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.hotelweb.controller;

import com.distributed.hotelweb.model.dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author viewt_000
 */
@WebServlet(name = "EditController", urlPatterns = {"/Edit"})
public class EditController extends HttpServlet {

    private static final String EDIT_PATH = "/View/edit.jsp";
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
            out.println("<title>Servlet EditController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditController at " + request.getContextPath() + "</h1>");
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
        
        String destination = EDIT_PATH;
        
        try
        {
            HotelDao hotelDao = new HotelDao();
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            HotelModel hotel = hotelDao.getSingleHotel(id).get(0);
            
            request.setAttribute("hotel", hotel);
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
            HotelModel editHotel = new HotelModel();
            editHotel.setHotelID(Integer.parseInt(request.getParameter("id")));
            editHotel.setHotelName(request.getParameter("name"));
            editHotel.setStreetAddress(request.getParameter("address"));
            editHotel.setCity(request.getParameter("city"));
            editHotel.setState(request.getParameter("state"));
            editHotel.setPostalCode(request.getParameter("postalCode"));
            editHotel.setNotes(request.getParameter("notes"));            
            
            hotelDAO.updateHotel(editHotel.getHotelID(), editHotel.getHotelName(), 
                    editHotel.getStreetAddress(), editHotel.getCity(),
                    editHotel.getState(), editHotel.getPostalCode(), editHotel.getNotes());
        }
        catch(Exception e)
        {
            destination = ERROR_PATH;
            request.setAttribute("msg", e.getMessage());
            RequestDispatcher view = request.getRequestDispatcher(destination);
            view.forward(request, response);
        }
        response.sendRedirect(response.encodeRedirectURL(destination));
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
