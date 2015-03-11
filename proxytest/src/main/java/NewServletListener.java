
import java.util.Enumeration;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author joel_harris
 */
public class NewServletListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {
 @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("attributeAdded");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("attributeRemoved");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("attributeReplaced");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated");
        // se.getSession().getId()
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("attributeAdded");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("attributeRemoved");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("attributeReplaced");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("------------------------requestDestroyed keyhole------------------------------");
         HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
           System.out.println("UserPrinciple: "+request.getUserPrincipal());
          System.out.println("displatcherType: "+request.getDispatcherType());
        // sre.getServletContext().
         System.out.println("requestURL: " + request.getRequestURL());
        Enumeration params = sre.getServletRequest().getParameterNames();
        while (params.hasMoreElements()) {
            String key = params.nextElement().toString();
            System.out.println(key + ": " + sre.getServletRequest().getParameter(key));
        }

        System.out.println(sre.getServletContext().getContextPath());
        Enumeration attrs = sre.getServletRequest().getAttributeNames();
        while (attrs.hasMoreElements()) {
            String attributeName = attrs.nextElement().toString();
            //  System.out.println(attributeName);

            System.out.println(attributeName + " : " + sre.getServletRequest().getAttribute(attributeName));
        }

       
        System.out.println("auth type: " + request.getAuthType());
        System.out.println("remote User: "+request.getRemoteUser());
        System.out.println("----------------headers----------------------");
        Enumeration headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String key = headers.nextElement().toString();
            System.out.println(key + " : " + request.getHeader(key));
        }
        System.out.println("Is session valid: " + request.isRequestedSessionIdValid());
        System.out.println("+++++++++++++++++ end request destroyed+++++++++++++++++++++");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("---------------request initialized keyhole -------------------------");
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        System.out.println("protocol: "+request.getProtocol());
        
        System.out.println("UserPrinciple: "+request.getUserPrincipal());
        System.out.println("displatcherType: "+request.getDispatcherType());
//        String pathAttribute = request.getSession().getAttribute("Path").toString();
//        System.out.println("Sessio pathAttribute: "+pathAttribute);
        System.out.println("Request Method: " + request.getMethod());
//
//        List<Cookie> cookies = Arrays.asList(request.getCookies());
//        System.out.println("--------------Cookies--------------------");
//        for(Cookie c : cookies){
//            System.out.println("comment: "+c.getComment());  
//            System.out.println("Domain: "+c.getDomain());
//            System.out.println("Name: "+c.getName());
//            System.out.println("Path: "+c.getPath());
//            System.out.println("Value: "+c.getValue());
//            System.out.println("Secure: "+c.getSecure());
//            System.out.println("Version: "+c.getVersion());
//        }
//        System.out.println("-------------------------------------------");
        Enumeration params = sre.getServletRequest().getParameterNames();
        while (params.hasMoreElements()) {
            System.out.println("param");
            String key = params.nextElement().toString();
            System.out.println(key + ": " + sre.getServletRequest().getParameter(key));
           // sre.getServletContext().setInitParameter("authorization", "FORM");
        }

        System.out.println(sre.getServletContext().getContextPath());
        Enumeration attrs = sre.getServletRequest().getAttributeNames();
        while (attrs.hasMoreElements()) {
            String attributeName = attrs.nextElement().toString();
            //  System.out.println(attributeName);
            Object attributeObject = sre.getServletRequest().getAttribute(attributeName);

            System.out.println(attributeName + " : " + sre.getServletRequest().getAttribute(attributeName));
        }

        System.out.println("auth type: " + request.getAuthType());
        System.out.println("----------------headers----------------------");
        Enumeration headers = request.getHeaderNames();
 
        while (headers.hasMoreElements()) {
            String key = headers.nextElement().toString();
           // request.getHeader("authrorization");
       
            System.out.println(key + " : " + request.getHeader(key));
        }
 //      HttpServletRequestWrapper requestWrapper = (HttpServletRequestWrapper) request;
       
    //   requestWrapper.
        System.out.println("Is session valid: " + request.isRequestedSessionIdValid());
        System.out.println("requestURL: " + request.getRequestURL());
        System.out.println("+++++++++++++++++ end request initialized keyhole+++++++++++++++++++++");

    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("----------------attributeAdded keyholed---------------------");
        System.out.println("new attribute name:" + srae.getName());
        System.out.println("value: " + srae.getValue());

        System.out.println("+++++++++++end attributeAdded+++++++++++++++");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("attributeRemoved");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("attributeReplaced keyhole");
    }
}
