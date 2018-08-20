
package com.unionbankng.notifications.email;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recipientType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="recipientType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TO"/&gt;
 *     &lt;enumeration value="CC"/&gt;
 *     &lt;enumeration value="BCC"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "recipientType")
@XmlEnum
public enum RecipientType {

    TO,
    CC,
    BCC;

    public String value() {
        return name();
    }

    public static RecipientType fromValue(String v) {
        return valueOf(v);
    }

}
