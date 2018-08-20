package com.unionbank.utilities;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger
{
  //static String logFile = "C:\\Logger\\Logs\\AccountFlex";
	static String logFile = "C:\\ProjectsJava\\AccountFlexServiceFcubs\\test\\AccountFlex";
  
  public static void processLog(String messageType, String direction, Object obj)
  {
    System.out.println("messageType <<>>" + messageType);
    SimpleDateFormat sformatter = new SimpleDateFormat("yyyy_MM_dd");
    
    SimpleDateFormat sformatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String current_date = sformatter.format(new Date());
    String current_date2 = sformatter2.format(new Date());
    try
    {
      XStream xstream = new XStream(new StaxDriver());
      String xmlString = xstream.toXML(obj);
      System.out.println("test built string <<>>" + xmlString);
      
      String xmlStringresr = xstream.toXML(obj);
      boolean bool = storeInFlatFile("AccountFlex <<>> " + messageType + " " + direction + " " + current_date2 + " " + xmlStringresr + "\n", logFile + "_" + current_date + ".xml");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {}
  
  public static boolean storeInFlatFile(String request, String fileName)
  {
    boolean isStored = false;
    try
    {
      FileWriter file = new FileWriter(fileName, true);
      file.write(request);
      
      file.flush();
      file.close();
      isStored = true;
    }
    catch (Exception ex)
    {
      System.out.println("NFPBankInterface :: NIPProcessor :: storeInFlatFile :: Error Occurred...");
      ex.printStackTrace();
    }
    return isStored;
  }
}
