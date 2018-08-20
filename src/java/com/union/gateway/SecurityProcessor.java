/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.union.gateway;

import com.unionbank.utilities.Encryptor;
import com.unionbankng.notifications.email.EmailAddressDto;
import com.unionbankng.notifications.email.EmailBodyDto;
import com.unionbankng.notifications.email.EmailService_Service;
import com.unionbankng.notifications.email.EmailService;
import com.unionbankng.notifications.email.RecipientType;
import com.unionbankng.notifications.email.ResponseDto;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;
import oracle.jdbc.OracleTypes;
import java.io.File;

/**
 *
 * @author aojinadu
 */
public class SecurityProcessor {

	final static UUID uuid = UUID.randomUUID();
	private String user;
	private String pass;
	private String driver;
	private String url;
	Encryptor en = new Encryptor("unionban");
	private EmailService port = null;
	private EmailService_Service service = null;
	private EmailBodyDto emailreq;
	EmailAddressDto sender;
	EmailAddressDto recipient;

	public EmailService getPort() {
		return port;
	}

	public void setPort(EmailService port) {
		this.port = port;
	}

	public EmailService_Service getService() {
		return service;
	}

	public void setService(EmailService_Service service) {
		this.service = service;
	}

	public EmailBodyDto getEmailreq() {
		return emailreq;
	}

	public void setEmailreq(EmailBodyDto emailreq) {
		this.emailreq = emailreq;
	}

	public static class SecurityDao {

		private String username;
		private String secret1;
		private String secret2;
		private String secret3;
		private String answer1;
		private String answer2;
		private String answer3;
		private String staffid;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getSecret1() {
			return secret1;
		}

		public void setSecret1(String secret1) {
			this.secret1 = secret1;
		}

		public String getSecret2() {
			return secret2;
		}

		public void setSecret2(String secret2) {
			this.secret2 = secret2;
		}

		public String getSecret3() {
			return secret3;
		}

		public void setSecret3(String secret3) {
			this.secret3 = secret3;
		}

		public String getAnswer1() {
			return answer1;
		}

		public void setAnswer1(String answer1) {
			this.answer1 = answer1;
		}

		public String getAnswer2() {
			return answer2;
		}

		public void setAnswer2(String answer2) {
			this.answer2 = answer2;
		}

		public String getAnswer3() {
			return answer3;
		}

		public void setAnswer3(String answer3) {
			this.answer3 = answer3;
		}

		public String getStaffid() {
			return staffid;
		}

		public void setStaffid(String staffid) {
			this.staffid = staffid;
		}
	}
	
	public static String getPropertiesValue(String key) {
		Properties prop = new Properties();
		InputStream input = null;
		String retValue = "";
		//  String config_path = System.getenv("WSCONFIG_HOME") + File.separator + "wsconfig.properties";
		String config_path = "C:\\deploy" + File.separator + "wsconfig.properties";

		//   String config_path = "/osbshare/wsconfig.properties";
		System.out.println("Config found on=====" + config_path);
		try {
			input = new FileInputStream(config_path);
			prop.load(input);
			retValue = prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
					prop.clear();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return retValue;
	}

	public void paramload() {
		this.user = getPropertiesValue("SDB_USER");
		this.pass = en.decryptStringEncoded(getPropertiesValue("SDB_PASS"));
		this.driver = getPropertiesValue("DB_DRIVER");
		this.url = getPropertiesValue("SDB_CONNECTION");
	}

	public static String prepareCode() throws Exception {
		String key = "";
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		salt.update(uuid.toString().getBytes("UTF-8"));
		String hex = DatatypeConverter.printHexBinary(salt.digest());

		System.out.println("HEx value is >>" + hex);
		return key;
	}

	public static String getSub(String str) {
		String res = "";
		try {
			int len = str.length();
			int rlen = new Random().nextInt(len);

			res = str.substring(rlen, len);
		} catch (Exception e) {
			System.out.println("Error Encountered!");
		}
		return res;
	}

	public static UUID prepCode(String name) throws Exception {

		String namespace = getSub(name);

		String source = String.valueOf(System.currentTimeMillis() + namespace + prepareCode());
		byte[] bytes = source.getBytes("UTF-8");
		UUID uuid = UUID.nameUUIDFromBytes(bytes);

		System.out.println("uuid >>" + uuid);

		return uuid;
	}

	public boolean saveAndSend(String username, String dispName) {
		paramload();
		Connection conn = null;
		CallableStatement cll = null;
		boolean isValid = false;
		System.out.println("Uname >>" + username);

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);

			UUID key = prepCode(username);
			String keyy = String.valueOf(key);
			System.out.println("Keyy >>" + keyy);

			String query = "{? = call PASSWORDRESETPKG.INSERTOTP(?,?)}";

			cll = conn.prepareCall(query);

			cll.registerOutParameter(1, OracleTypes.VARCHAR);
			cll.setString(2, username);
			cll.setString(3, keyy);

			if (cll.executeUpdate() != Statement.EXECUTE_FAILED) {
				String res = (String) cll.getString(1);
				isValid = Boolean.valueOf(res);
				System.out.println("valid or not >>" + isValid);

				if (isValid) {
					try {
						System.out.println("Entered email arena...");
						sender = new EmailAddressDto();
						sender.setDisplayName("PASSWORD RESET");
						sender.setEmail("passwordreset@unionbankng.com");

						recipient = new EmailAddressDto();
						recipient.setDisplayName(dispName);
						recipient.setEmail(username + "@unionbankng.com");
						recipient.setRecipientType(RecipientType.TO);

						List<EmailAddressDto> recip = new ArrayList<>();
						recip.add(recipient);

						emailreq = new EmailBodyDto();
						emailreq.setSender(sender);
						emailreq.setSubject("Password Reset Request");
						emailreq.setBody(composeMail(dispName, keyy, username));
						emailreq.getRecipients().addAll(recip);

						ResponseDto resp = sendMail(emailreq);
						System.out.println("Response from mail >>" + resp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					conn.rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConns(conn, cll);
		}
		return isValid;
	}

	private String composeMail(String display, String key, String username) {
		String body = "";

		body += "<html>\n"
				+ "\n"
				+ "<head>\n"
				+ "\n"
				+ "    <style>\n"
				+ "        /* Style Definitions */\n"
				+ "        p.MsoNormal, li.MsoNormal, div.MsoNormal {\n"
				+ "            mso-style-unhide: no;\n"
				+ "            mso-style-qformat: yes;\n"
				+ "            mso-style-parent: '';\n"
				+ "            margin: 0in;\n"
				+ "            margin-bottom: .0001pt;\n"
				+ "            mso-pagination: widow-orphan;\n"
				+ "            font-size: 11.0pt;\n"
				+ "            font-family: 'Calibri','sans-serif';\n"
				+ "            mso-fareast-font-family: Calibri;\n"
				+ "            mso-fareast-theme-font: minor-latin;\n"
				+ "            mso-bidi-font-family: 'Times New Roman';\n"
				+ "        }\n"
				+ "\n"
				+ "        a:link, span.MsoHyperlink {\n"
				+ "            mso-style-noshow: yes;\n"
				+ "            mso-style-priority: 99;\n"
				+ "            color: #0563C1;\n"
				+ "            text-decoration: underline;\n"
				+ "            text-underline: single;\n"
				+ "        }\n"
				+ "\n"
				+ "        a:visited, span.MsoHyperlinkFollowed {\n"
				+ "            mso-style-noshow: yes;\n"
				+ "            mso-style-priority: 99;\n"
				+ "            color: #954F72;\n"
				+ "            text-decoration: underline;\n"
				+ "            text-underline: single;\n"
				+ "        }\n"
				+ "\n"
				+ "        p.msonormal0, li.msonormal0, div.msonormal0 {\n"
				+ "            mso-style-name: msonormal;\n"
				+ "            mso-style-unhide: no;\n"
				+ "           mso-margin-top-alt: auto;\n"
				+ "            margin-right: 0in;\n"
				+ "            mso-margin-bottom-alt: auto;\n"
				+ "            margin-left: 0in;\n"
				+ "            mso-pagination: widow-orphan;\n"
				+ "           font-size: 12.0pt;\n"
				+ "            font-family: 'Times New Roman','serif';\n"
				+ "            mso-fareast-font-family: Calibri;\n"
				+ "            mso-fareast-theme-font: minor-latin;\n"
				+ "        }\n"
				+ "    </style>\n"
				+ "\n"
				+ "</head>\n"
				+ "\n"
				+ "<body>\n"
				+ "\n"
				+ "    <div>\n"
				+ "\n"
				+ "\n"
				+ "        <p class=MsoNormal>\n"
				+ "            <span style='color:black'></span>\n"
				+ "        </p>\n"
				+ "\n"
				+ "        <div align=center>\n"
				+ "\n"
				+ "            <table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=600\n"
				+ "                   style='width:6.25in;border-collapse:collapse;'>\n"
				+ "                <tr>\n"
				+ "                    <td style='background:#009FDF;padding:0in 0in 0in 0in'>\n"
				+ "                        <p class=MsoNormal style='line-height:105%'><span style='color:black'>&nbsp;</span><span style='font-size:12.0pt;line-height:105%;'></span></p>\n"
				+ "                        <table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=\"100%\">\n"
				+ "                            <tr>\n"
				+ "                                <td style='padding:11.25pt 11.25pt 22.5pt 0in'>\n"
				+ "                                    <p class=MsoNormal align=right style='text-align:right;line-height:105%'>\n"
				+ "                                        <span style='font-size:12.0pt;line-height:105%;'>\n"
				+ "\n"
				+ "\n"
				+ "                                            <img width=\"113\" height=\"47\" src=\"https://portalapp1:1112/sites/apps/Settlement_Processed/download.gif\" />\n"
				+ "                                            <!--<img width=\"113\" height=\"47\" src=\"cid:stallion\" />-->\n"
				+ "\n"
				+ "                                        </span>\n"
				+ "                                    </p>\n"
				+ "                                </td>\n"
				+ "                            </tr>\n"
				+ "                            <tr>\n"
				+ "                                <td style='padding:0in 0in 7.5pt 15.0pt'>\n"
				+ "                                    <p class=MsoNormal>\n"
				+ "                                        <b>\n"
				+ "                                            <span style='font-family:\"Arial\",\"sans-serif\";color:white'>\n"
				+ "                                                Password Reset\n"
				+ "                                            </span>\n"
				+ "                                        </b><b>\n"
				+ "                                            <span style='font-family:\"Arial\",\"sans-serif\"'></span>\n"
				+ "                                        </b>\n"
				+ "                                    </p>\n"
				+ "                                </td>\n"
				+ "                            </tr>\n"
				+ "                        </table>\n"
				+ "                    </td>\n"
				+ "                </tr>\n"
				+ "                <tr>\n"
				+ "                    <td style='background:#E5E5E5;padding:30.0pt 30.0pt 30.0pt 30.0pt'>\n"
				+ "                        <table class=MsoNormalTable border=0 cellspacing=3 cellpadding=0>\n"
				+ "                            <tr>\n"
				+ "                                <td style='padding:.75pt .75pt .75pt .75pt'>\n"
				+ "                                <p>Dear " + display + ",</p>\n"
				+ "                                <p>\n"
				+ "                                   Kindly use the below password to complete your registration process.\n"
				+ "                                </p>\n"
				+ "                                <p style=\"font-size: 24\"><strong>" + key + "</strong></p>\n"
				+ "                                <p>Or <a href=\"http://DEVOPS-22:8080/UserPasswordReset/validate.jsp?user=" + username + "&&validateOTP=" + key + "\">click here </a></p>\n"
				+ "                                <!--<p>Plan : Auto Comprehensive</p>              \n"
				+ "                               table border='0' cellpadding='0' cellspacing='0' width='100%'>\n"
				+ "                                <tr>\n"
				+ "                                    <td>\n"
				+ "                                        <p>Has been {ApprovalState} by your supervisor {SupervisorFullName}</p>\n"
				+ "                                        <p><a href=\"{Link}\">{Link}</a></p>\n"
				+ "                                        <p><br/></p>                   \n"
				+ "                                        <p><br/><br/>\n"
				+ "                                        Regards,</p>\n"
				+ "                                    </td>\n"
				+ "                                </tr>\n"
				+ "                                </table-->\n"
				+ "\n"
				+ "                                    <p class=MsoNormal style='text-align:justify'>\n"
				+ "                                        <span style='font-family:\n"
				+ "    \"Arial\",\"sans-serif\"'></span>\n"
				+ "                                    </p>\n"
				+ "                                    <p class=MsoNormal style='text-align:justify'>\n"
				+ "                                        <i>\n"
				+ "                                            <span style='font-family:\n"
				+ "    \"Arial\",\"sans-serif\"'>&nbsp;</span>\n"
				+ "                                        </i><span style='font-family:\"Arial\",\"sans-serif\"'></span>\n"
				+ "                                    </p>\n"
				+ "\n"
				+ "                                </td>\n"
				+ "\n"
				+ "                            </tr>\n"
				+ "                            <tr>\n"
				+ "                                <td style='padding:.75pt .75pt .75pt .75pt'>\n"
				+ "                                    <table class=MsoNormalTable border=0 cellspacing=3 cellpadding=0>\n"
				+ "                                        <tr>\n"
				+ "                                           <td style='padding:.75pt .75pt .75pt .75pt'>\n"
				+ "                                                <p class=MsoNormal style='text-align:justify;line-height:105%'>\n"
				+ "                                                    <a href=\"https://www.facebook.com/UnionBankng\">\n"
				+ "                                                        <span style='font-size:7.5pt;\n"
				+ "      line-height:105%;font-family:\"Arial\",\"sans-serif\";color:black;text-decoration:\n"
				+ "      none;text-underline:none'>\n"
				+ "\n"
				+ "                                                            <img border=0 width=7 height=12 src=\"cid:facebook\">\n"
				+ "\n"
				+ "                                                        </span><span style='font-size:7.5pt;line-height:105%;font-family:\"Arial\",\"sans-serif\";\n"
				+ "      color:black'>unionbankng</span>\n"
				+ "                                                    </a><span style='font-size:12.0pt;\n"
				+ "      line-height:105%;font-family:\"Times New Roman\",\"serif\"'></span>\n"
				+ "                                                </p>\n"
				+ "                                            </td>\n"
				+ "                                            <td style='padding:.75pt .75pt .75pt .75pt'>\n"
				+ "                                                <p class=MsoNormal style='text-align:justify;line-height:105%'>\n"
				+ "                                                    <a href=\"https://twitter.com/UNIONBANK_NG\">\n"
				+ "                                                        <span style='font-size:7.5pt;\n"
				+ "      line-height:105%;font-family:\"Arial\",\"sans-serif\";color:black;text-decoration:\n"
				+ "      none;text-underline:none'>\n"
				+ "\n"
				+ "                                                            <img border=0 width=12 height=10 src=\"cid:twitter\">\n"
				+ "\n"
				+ "                                                        </span><span style='font-size:7.5pt;line-height:105%;font-family:\"Arial\",\"sans-serif\";\n"
				+ "      color:black'>unionbank_ng</span>\n"
				+ "                                                    </a><span style='font-size:12.0pt;\n"
				+ "      line-height:105%;font-family:\"Times New Roman\",\"serif\"'><o:p></o:p></span>\n"
				+ "                                                </p>\n"
				+ "                                            </td>\n"
				+ "                                            <td style='padding:.75pt .75pt .75pt .75pt'>\n"
				+ "                                                <p class=MsoNormal style='text-align:justify;line-height:105%'>\n"
				+ "                                                    <a href=\"https://www.youtube.com/user/UnionBankNG\">\n"
				+ "                                                        <span style='font-size:\n"
				+ "      7.5pt;line-height:105%;font-family:\"Arial\",\"sans-serif\";color:black;\n"
				+ "      text-decoration:none;text-underline:none'>\n"
				+ "\n"
				+ "                                                            <img border=0 width=13 height=12 src=\"cid:youtube\">\n"
				+ "\n"
				+ "                                                        </span><span style='font-size:7.5pt;line-height:105%;font-family:\"Arial\",\"sans-serif\";\n"
				+ "      color:black'>unionbankng</span>\n"
				+ "                                                    </a><span style='font-size:12.0pt;\n"
				+ "      line-height:105%;font-family:\"Times New Roman\",\"serif\"'></span>\n"
				+ "                                                </p>\n"
				+ "                                            </td>\n"
				+ "                                            <td width=235 style='width:176.5pt;padding:.75pt .75pt .75pt .75pt'>\n"
				+ "                                                <p class=MsoNormal style='text-align:justify;line-height:105%'>\n"
				+ "                                                    <a href=\"https://instagram.com/unionbankng\">\n"
				+ "                                                        <span style='font-size:7.5pt;\n"
				+ "      line-height:105%;font-family:\"Arial\",\"sans-serif\";color:black;text-decoration:\n"
				+ "      none;text-underline:none'>\n"
				+ "\n"
				+ "                                                            <img border=0 width=11 height=12 src=\"cid:instagram\">\n"
				+ "\n"
				+ "                                                        </span><span style='font-size:7.5pt;line-height:105%;font-family:\"Arial\",\"sans-serif\";\n"
				+ "      color:black'>unionbankng</span>\n"
				+ "                                                    </a><span style='font-size:12.0pt;\n"
				+ "      line-height:105%;font-family:\"Times New Roman\",\"serif\"'></span>\n"
				+ "                                                </p>\n"
				+ "                                            </td>\n"
				+ "                                        </tr>\n"
				+ "                                    </table>\n"
				+ "                                </td>\n"
				+ "                            </tr>\n"
				+ "                        </table>\n"
				+ "                    </td>\n"
				+ "                </tr>\n"
				+ "                <tr>\n"
				+ "                    <td style='background:#009FDF;padding:22.5pt 22.5pt 22.5pt 22.5pt'></td>\n"
				+ "                </tr>\n"
				+ "            </table>\n"
				+ "\n"
				+ "        </div>\n"
				+ "\n"
				+ "       <p class=MsoNormal></p>\n"
				+ "\n"
				+ "        <p class=MsoNormal><span style='color:#1F497D'></span></p>\n"
				+ "\n"
				+ "    </div>\n"
				+ "\n"
				+ "</body>\n"
				+ "\n"
				+ "</html>";

		return body;
	}

	private ResponseDto sendMail(EmailBodyDto emailbody) {
		setService(new EmailService_Service());
		setPort(getService().getEmailServicePort());
		return getPort().sendEmail(emailbody);
	}

	public boolean validate(String username, String otp) {
		paramload();
		Connection conn = null;
		CallableStatement cll = null;
		boolean isValid = false;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);

			String query = "{call call PASSWORDRESETPKG.VALIDATE(?,?,?)}";
			cll = conn.prepareCall(query);
			cll.setString(1, otp);
			cll.setString(2, username);
			cll.registerOutParameter(3, OracleTypes.VARCHAR);

			if (cll.executeUpdate() != Statement.EXECUTE_FAILED) {
				String out = (String) cll.getString(1);
				isValid = Boolean.valueOf(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;
	}

	public static void closeConns(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//
//		SecurityProcessor sp = new SecurityProcessor();
//		try {
//			sp.saveAndSend("aojinadu", "AYOTOLA JINADU");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
