package cn.chenjw.codedemo.designpattern.singleton;

public class Singleton2 {
	 private static Singleton2 instance;   
	  
	    private Singleton2() {   
	    }   
	  
	    public static Singleton2 getIstance() {   
	        if (instance == null) {   
	            synchronized (Singleton2.class) {   
	                if (instance == null) {   
	                    instance = new Singleton2();   
	                }   
	            }   
	        }   
	        return instance;   
	    }   
}
