package no.hvl.dat100.prosjekt;

import static java.lang.Math.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {
		
		String timestr= "";
		String TIMESEP=":";
		int hh= secs/3600;
		int mm= (secs%3600)/60;
		int ss= (secs%3600)%60;
		
		
		Integer.toString(hh);
		Integer.toString(mm);
		Integer.toString(ss);
		
		timestr= String.format("%02d",hh)+ TIMESEP + String.format("%02d", mm)+ TIMESEP + String.format ("%02d",ss);
		
	
		
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	
	public static double findMin(double[] da) {
		
		double min = da[0]; 
		
			for ( double d: da ) {
			if (d < min ) {
			min = d;
		
	}
}
		// OPPGAVE - SLUT
		return min;
	}

	
	
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		
		
		  final int R = 6371000; 

		    double latDistance = Math.toRadians(latitude2 - latitude1);
		    double lonDistance = Math.toRadians(longitude2 - longitude1);
		    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c * 1; 
		    

		return distance;
	}
	

	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		
	    

		   double speed = (((distance(latitude1, longitude1, latitude2, longitude2))/secs) * 3.6);
		   
		  

		return speed;
	}
	
	private static int TEXTWIDTH = 10;
		
	
	public static String printDouble(double d) {
		
		String str = "";
		
	str = String.format("%.2f", d);
	for ( int i = str.length()+1; i <=TEXTWIDTH; i++) {
		
		str = " "+str;
		str = str.replace(",", ".");
	}
		return str;
	}
}
