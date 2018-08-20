/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbank.processor;

/**
 *
 * @author wwakanni
 */
public class StateList implements java.io.Serializable {

    private String stateCode;
    private String stateName;

    public StateList(String stateCode, String stateName) {
    this.stateCode = stateCode;
    this.stateName = stateName;
    }

    public StateList() {
    }

    /**
     * @return the stateCode
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * @param stateCode the stateCode to set
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
