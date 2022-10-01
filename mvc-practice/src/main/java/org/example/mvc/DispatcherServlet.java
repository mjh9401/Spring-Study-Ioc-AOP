package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.RequestMappingHandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void init() throws ServletException {
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("[DispatcherServlet] service started");

        try {
            Controller handler = requestMappingHandlerMapping.findHandler(req.getRequestURI());
            String viewName = handler.handleRequest(req,resp);
            logger.info("view name {}",viewName);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
            requestDispatcher.forward(req,resp);

        } catch (Exception e) {
            logger.error("exception occured: [{}]",e.getMessage(),e);
            throw new ServletException(e);
        }
    }
}
