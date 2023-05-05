package com.certificate;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import io.restassured.config.SSLConfig;

public class Certificate {

	  public static SSLConfig getSslConfig(String password,String certificate) {
	  //  String password = "MuleGeneric01User";
	    KeyStore keyStore = null;
	    try {
	      String path = System.getProperty("user.dir")+ File.separator +"Certificates"+ File.separator +certificate;
	      FileInputStream fis = new FileInputStream(path);
	      keyStore = KeyStore.getInstance("JKS");
	      keyStore.load(
	          fis,
	          password.toCharArray());

	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    if (keyStore != null) {
	      org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = null;
	      try {
	        clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(keyStore, password);
	      } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	      } catch (KeyManagementException e) {
	        e.printStackTrace();
	      } catch (KeyStoreException e) {
	        e.printStackTrace();
	      } catch (UnrecoverableKeyException e) {
	        e.printStackTrace();
	      }
	      return new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
	    }
	    else return  null;
	  }
}
