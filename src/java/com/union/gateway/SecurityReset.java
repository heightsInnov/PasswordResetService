/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.union.gateway;

import com.union.bof.dao.ResetDao;
import com.union.bof.dao.SecretDao;
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
	private SecretDao sec;
	private ResetDao ResetDetails;
	
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
	
	@WebMethod(operationName = "saveRegistration")
	public boolean saveReg(@WebParam(name = "StaffInfo") SecretDao staffInfo) {
		sec = staffInfo;
		SecurityProcessor serviceProcessor = new SecurityProcessor();
		return serviceProcessor.saveReg(sec);
	}
	
	@WebMethod(operationName = "saveReset")
	public boolean saveReset(@WebParam(name = "ResetDetails") ResetDao resetDetails) {
		ResetDetails = resetDetails;
		SecurityProcessor serviceProcessor = new SecurityProcessor();
		return serviceProcessor.saveReset(ResetDetails);
	}
}