package com.nawaz.eurekaServer.EurekaServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mozilla.universalchardet.UniversalDetector;

public class mainfile {

	public static void main(String[] args) throws IOException {
		

	      FileInputStream fis = null;
	      InputStreamReader isr = null;
	      String s;
	      byte[] buf = new byte[4096];
	      try {
	         fis = new FileInputStream("test2."
	         		+ "txt");
	         isr = new InputStreamReader(fis);
	         UniversalDetector detector = new UniversalDetector(null);

	      // (2)
	          int nread;
	          while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
	             detector.handleData(buf, 0, nread);
	       }
	      // (3)
	        detector.dataEnd();

	      // (4)
	        String encoding = detector.getDetectedCharset();
	        if (encoding != null) {
	             System.out.println("Detected encoding = " + encoding);
	        } else {
	              System.out.println("No encoding detected.");
	        }
	     
	      } catch (Exception e) {
	         // print error
	         System.out.print("The stream is already closed");
	      } finally {
	         // closes the stream and releases resources associated
	         if(fis!=null)
	            fis.close();
	         if(isr!=null)
	            isr.close();
	      }   
	   }
		
	}

