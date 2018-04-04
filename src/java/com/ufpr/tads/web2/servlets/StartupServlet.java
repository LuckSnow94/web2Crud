/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.ConfigBean;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author luck
 */
@WebServlet(name = "StartupServlet", urlPatterns = {"/StartupServlet"}, loadOnStartup = 1)
public class StartupServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        ConfigBean conf = new ConfigBean();
        conf.setEmail("luckstriker1994@gmail.com");
        ServletContext ctx = config.getServletContext();
        ctx.setAttribute("configuracao", conf);
    }
    
}
