
package com.unionbankng.notifications.email;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendEmailSync complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendEmailSync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="emailReq" type="{http://email.notifications.unionbankng.com/}emailBodyDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendEmailSync", propOrder = {
    "emailReq"
})
public class SendEmailSync {

    protected EmailBodyDto emailReq;

    /**
     * Gets the value of the emailReq property.
     * 
     * @return
     *     possible object is
     *     {@link EmailBodyDto }
     *     
     */
    public EmailBodyDto getEmailReq() {
        return emailReq;
    }

    /**
     * Sets the value of the emailReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailBodyDto }
     *     
     */
    public void setEmailReq(EmailBodyDto value) {
        this.emailReq = value;
    }

}
