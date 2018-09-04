
package com.unionbankng.notifications.email;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendEmail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendEmail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="emailReq" type="{http://email.notifications.unionbankng.com/}emailBodyDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendEmail", propOrder = {
    "emailReq"
})
public class SendEmail {

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
