
package com.unionbankng.notifications.email;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EmailService", targetNamespace = "http://email.notifications.unionbankng.com/", wsdlLocation = "http://10.65.0.123:9080/EmailGateway/EmailService?wsdl")
public class EmailService_Service
    extends Service
{

    private final static URL EMAILSERVICE_WSDL_LOCATION;
    private final static WebServiceException EMAILSERVICE_EXCEPTION;
    private final static QName EMAILSERVICE_QNAME = new QName("http://email.notifications.unionbankng.com/", "EmailService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.65.0.123:9080/EmailGateway/EmailService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EMAILSERVICE_WSDL_LOCATION = url;
        EMAILSERVICE_EXCEPTION = e;
    }

    public EmailService_Service() {
        super(__getWsdlLocation(), EMAILSERVICE_QNAME);
    }

    public EmailService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), EMAILSERVICE_QNAME, features);
    }

    public EmailService_Service(URL wsdlLocation) {
        super(wsdlLocation, EMAILSERVICE_QNAME);
    }

    public EmailService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EMAILSERVICE_QNAME, features);
    }

    public EmailService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EmailService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EmailService
     */
    @WebEndpoint(name = "EmailServicePort")
    public EmailService getEmailServicePort() {
        return super.getPort(new QName("http://email.notifications.unionbankng.com/", "EmailServicePort"), EmailService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EmailService
     */
    @WebEndpoint(name = "EmailServicePort")
    public EmailService getEmailServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://email.notifications.unionbankng.com/", "EmailServicePort"), EmailService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EMAILSERVICE_EXCEPTION!= null) {
            throw EMAILSERVICE_EXCEPTION;
        }
        return EMAILSERVICE_WSDL_LOCATION;
    }

}
