/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.obtenerdirectorios;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Jhopon
 */
@WebServlet(name = "ObtenerDirectorios", urlPatterns = {"/ObtenerDirectorios"})
public class ObtenerDirectorios extends HttpServlet {
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
   }
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      String rutaObtenida = request.getParameter("ruta");
      
      //Capturar Error
      if(rutaObtenida == null || rutaObtenida.isEmpty()){
         response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         response.getWriter().write("Se necesita una ruta!!");
         return;
      }
      //Si no hay error
      Path ruta = Paths.get(rutaObtenida);
      StringBuilder directorios = new StringBuilder();
      
      try(DirectoryStream<Path> stream = Files.newDirectoryStream(ruta)) {
         for(Path directorio : stream){
            if(Files.isDirectory(directorio)){
               directorios.append(directorio.getFileName()).append("\n");
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(directorios.toString());
      //processRequest(request, response);
   }

   
   @Override
   public String getServletInfo() {
      return "Short description";
   }// </editor-fold>

}
