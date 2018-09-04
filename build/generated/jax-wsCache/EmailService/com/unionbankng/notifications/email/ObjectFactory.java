
package com.unionbankng.notifications.email;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.unionbankng.notifications.email package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendEmailSyncResponse_QNAME = new QName("http://email.notifications.unionbankng.com/", "sendEmailSyncResponse");
    private final static QName _SendEmail_QNAME = new QName("http://email.notifications.unionbankng.com/", "sendEmail");
    private final static QName _SendEmailResponse_QNAME = new QName("http://email.notifications.unionbankng.com/", "sendEmailResponse");
    private final static QName _SendEmailSync_QNAME = new QName("http://email.notifications.unionbankng.com/", "sendEmailSync");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.unionbankng.notifications.email
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendEmailSyncResponse }
     * 
     */
    public SendEmailSyncResponse createSendEmailSyncResponse() {
        return new SendEmailSyncResponse();
    }

    /**
     * Create an instance of {@link SendEmail }
     * 
     */
    public SendEmail createSendEmail() {
        return new SendEmail();
    }

    /**
     * Create an instance of {@link SendEmailResponse }
     * 
     */
    public SendEmailResponse createSendEmailResponse() {
        return new SendEmailResponse();
    }

    /**
     * Create an instance of {@link SendEmailSync }
     * 
     */
    public SendEmailSync createSendEmailSync() {
        return new SendEmailSync();
    }

    /**
     * Create an instance of {@link ResponseDto }
     * 
     */
    public ResponseDto createResponseDto() {
        return new ResponseDto();
    }

    /**
     * Create an instance of {@link EmailAttachmentDto }
     * 
     */
    public EmailAttachmentDto createEmailAttachmentDto() {
        return new EmailAttachmentDto();
    }

    /**
     * Create an instance of {@link EmailBodyDto }
     * 
     */
    public EmailBodyDto createEmailBodyDto() {
        return new EmailBodyDto();
    }

    /**
     * Create an instance of {@link EmailAddressDto }
     * 
     */
    public EmailAddressDto createEmailAddressDto() {
        return new EmailAddressDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailSyncResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://email.notifications.unionbankng.com/", name = "sendEmailSyncResponse")
    public JAXBElement<SendEmailSyncResponse> createSendEmailSyncResponse(SendEmailSyncResponse value) {
        return new JAXBElement<SendEmailSyncResponse>(_SendEmailSyncResponse_QNAME, SendEmailSyncResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://email.notifications.unionbankng.com/", name = "sendEmail")
    public JAXBElement<SendEmail> createSendEmail(SendEmail value) {
        return new JAXBElement<SendEmail>(_SendEmail_QNAME, SendEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://email.notifications.unionbankng.com/", name = "sendEmailResponse")
    public JAXBElement<SendEmailResponse> createSendEmailResponse(SendEmailResponse value) {
        return new JAXBElement<SendEmailResponse>(_SendEmailResponse_QNAME, SendEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailSync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://email.notifications.unionbankng.com/", name = "sendEmailSync")
    public JAXBElement<SendEmailSync> createSendEmailSync(SendEmailSync value) {
        return new JAXBElement<SendEmailSync>(_SendEmailSync_QNAME, SendEmailSync.class, null, value);
    }

}
