package no.hvl.dat100.prosjekt;

public class GPSComputer {
	
	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;
	
	// beregn total distances (i meter)
	public double totalDistance() {

	
 double distance =0;

		
		for ( int i =0; i< times.length -1; i++){
			distance= GPSUtils.distance(latitudes[i], longitudes[i], latitudes[i+1], longitudes[i+1])+ distance;
							
		} 
		

	

		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		for ( int i =0; i< times.length -1; i++){
			
			if( elevations[i+1] - elevations[i] >0) { 
			
			elevation = elevations[i+1] - elevations[i] + elevation;
						
			}else {
			
			}
		} 
		return elevation;
	}

	
	public int totalTime() {
		
		int totaltime = times[times.length-1] - times[0];
		
		
		return totaltime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double [times.length-1];
		int secs;
		for ( int i=0; i < times.length -1; i++) {
			secs= times[i+1] - times[i];
			speeds[i]=GPSUtils.speed(secs, latitudes[i], longitudes[i], latitudes[i+1], longitudes[i+1]);
		}
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {
		
		double maxspeed = 0;
		
	for ( int i = 0; i<times.length -1; i++) {
		if ( maxspeed < speeds()[i]){
			maxspeed = speeds()[i];
		}else;
	}
		
		return maxspeed;
	}
	
	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {

		double average = (totalDistance()/totalTime())*3.6;
		
	
		
		
	
		return average;
	}
	// conversion factor kph (km/t) to miles per hour (mph)
	public static double MS = 0.62;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		if (speedmph<10){
			met = 4;
			
		}else if ( speedmph <12) {
			met = 6;
			
		}else if ( speedmph <14) {
			met = 8;
			
		}else if ( speedmph <15) {
			met = 10;
			
		}else if ( speedmph <20) {
			met = 12;
			
		}else if ( speedmph >20) {
			met = 16;
			
		}
		
		kcal = met * weight * ((double)secs/3600);
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		double sum= 0.0;
		
		double[] speeds = speeds(); 
		
		for ( int i=0; i < times.length -1; i ++) {
			totalkcal = sum +kcal (weight, times[i],speeds[i]);
	
		}
		
		return totalkcal;
	}
	
	public static double WEIGHT = 80.0;
	
	// skriv ut statistikk for turen
	public void print() {
		
		// TODO
		int tid= totalTime();
		double distance = totalDistance();
		double eleveation= totalElevation();
		double maxspeed=maxSpeed();
		double averageSpeed= averageSpeed();		
		double totalKcal = totalKcal(WEIGHT);
		
		System.out.format("Total time      : %02d:%02d:%02d%n", (tid/3600), ((tid%3600)/60), (tid%60));
		System.out.format("Total distance  : %.2f km%n", distance/1000);
		System.out.format("Total elevation : %.2f m%n", eleveation);
		System.out.format("Max speed       : %.2f km%n", maxspeed);
		System.out.format("Average speed   : %.2f km%n", averageSpeed);
		System.out.format("Energy          : %.2f kcal%n", totalKcal/2.583858);// la inn variable 
		System.out.println();
	
				
		
		
		// OPPGAVE - SLUT
	}
	
	// ekstraoppgaver
	public void climbs() {
		
	}
	
	public void maxClimb() {
		
	}
}
