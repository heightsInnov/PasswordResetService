/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.union.gateway;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author aojinadu
 */
@WebService()
public class SecurityReset {
	private String Username;
	private String Display;
	private String vOTP;
	@WebMethod(operationName = "generatePasswordResetOTP")
	public boolean passwordResetOTP(@WebParam(name = "username") String username, @WebParam(name = "displayname") String displayname) {
		Username = username;
		Display = displayname;
		SecurityProcessor serviceProcessor = new SecurityProcessor();
		return serviceProcessor.saveAndSend(Username, Display);
	}
	
	@WebMethod(operationName = "validateResetOTP")
	public boolean validateOTP(@WebParam(name = "username") String username, @WebParam(name = "oneTimePass") String OTP) {
		Username = username;
		vOTP = OTP;
		SecurityProcessor serviceProcessor = new SecurityProcessor();
		return serviceProcessor.validate(Username, vOTP);
	}
}
