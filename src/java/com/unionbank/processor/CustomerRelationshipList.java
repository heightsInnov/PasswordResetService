/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbank.processor;

/**
 *
 * @author wwakanni
 */
public class CustomerRelationshipList implements java.io.Serializable {

    private String cod_acct_no;
    private String customerid;
    private String relationship;
    private String branchcode;
    private String productcode;


    public CustomerRelationshipList(String cod_acct_no, String customerid,
            String relationship, String branchcode, String productcode) {
    this.cod_acct_no = cod_acct_no;
    this.customerid = customerid;
    this.relationship = relationship;
    this.branchcode = branchcode;
    this.productcode = productcode;
    }

    public CustomerRelationshipList() {
    }

    /**
     * @return the cod_acct_no
     */
    public String getCod_acct_no() {
        return cod_acct_no;
    }

    /**
     * @param cod_acct_no the cod_acct_no to set
     */
    public void setCod_acct_no(String cod_acct_no) {
        this.cod_acct_no = cod_acct_no;
    }

    /**
     * @return the customerid
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * @param customerid the customerid to set
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    /**
     * @return the relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * @return the branchcode
     */
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode the branchcode to set
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the productcode
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * @param productcode the productcode to set
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    
}
