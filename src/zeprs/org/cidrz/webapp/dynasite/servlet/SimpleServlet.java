/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 20, 2006
 *         Time: 6:25:01 PM
 */
public class SimpleServlet extends HttpServlet  {
    //ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
       response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");

        String title = "The title";
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        // img stuff not req'd for source code html showing
    // all links relative!

        // XXX
        // making these absolute till we work out the
        // addition of a PathInfo issue

        out.println("<a href=\"../reqinfo.html\">");
        out.println("<img src=\"../images/code.gif\" height=24 " +
                    "width=24 align=right border=0 alt=\"view code\"></a>");
        out.println("<a href=\"../index.html\">");
        out.println("<img src=\"../images/return.gif\" height=24 " +
                    "width=24 align=right border=0 alt=\"return\"></a>");

        out.println("<h3>" + title + "</h3>");
        out.println("<table border=0><tr><td>");
        out.println("method");
        out.println("</td><td>");
        out.println(request.getMethod());
        out.println("</td></tr><tr><td>");
        out.println("requesturi");
        out.println("</td><td>");
        //out.println(HTMLFilter.filter(request.getRequestURI()));
        out.println("</td></tr><tr><td>");
        out.println("protocol");
        out.println("</td><td>");
        out.println(request.getProtocol());
        out.println("</td></tr><tr><td>");
        out.println("requestinfo.label.pathinfo");
        out.println("</td><td>");
        //out.println(HTMLFilter.filter(request.getPathInfo()));
        out.println("</td></tr><tr><td>");
        out.println("requestinfo.label.remoteaddr");

     String cipherSuite=
         (String)request.getAttribute("javax.servlet.request.cipher_suite");
        out.println("</td><td>");
        out.println(request.getRemoteAddr());
        out.println("</table>");

     if(cipherSuite!=null){
         out.println("</td></tr><tr><td>");
         out.println("SSLCipherSuite:");
         out.println("</td>");
         out.println("<td>");
         out.println(request.getAttribute("javax.servlet.request.cipher_suite"));
        out.println("</td>");
     }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}
