/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.context.controller;

import com.context.model.Pelicula;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FABAME
 */
@WebServlet(urlPatterns = {"/guardarPelicula", "/guardarPelicula.do"})
public class ServletGuardarPelicula extends HttpServlet {

    public ServletGuardarPelicula() {

    }

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
        PrintWriter out = response.getWriter();

        try {
            /* TODO output your page here. You may use following sample code. */
            String nombre = request.getParameter("nombre");
            String director = request.getParameter("director");

            // Validaciones.
            // Verificamos los parámetros.
            List<String> listadoMensajesError = new LinkedList<>();
            if (nombre.length() == 0) {
                listadoMensajesError.add("Debe ingresar el nombre.");
            }

            if (director.length() == 0) {
                listadoMensajesError.add("Debe ingresar el director.");
            }

            if (!listadoMensajesError.isEmpty()) {
                request.setAttribute("listadoMensajesError", listadoMensajesError);
                RequestDispatcher view = request.getRequestDispatcher("/formAgregarPelicula.view");
                view.forward(request, response);
                return;
            }

            // Si no hay errores.
            Pelicula pelicula = new Pelicula(nombre, director);
            ServletContext contexto = this.getServletContext();
            List<Pelicula> listadoPeliculas = (List<Pelicula>) contexto.getAttribute("listadoPeliculas");
            listadoPeliculas.add(pelicula);
            RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
            view.forward(request, response);
            return;

        } finally {
            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
