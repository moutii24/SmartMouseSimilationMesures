package Application;

import javax.xml.ws.Endpoint;

import Services.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		GenerateMesures data = new GenerateMesures();
		
		data.setCbegin(150);
		data.setCend(300);
		data.setTbegin(36);
		data.setTend(40);
		
		data.start();
		/*GenerateCondThread conT = new GenerateCondThread();
		
		
		conT.setBegin(150);
		conT.setEnd(300);
		
		tmpT.setBegin(36);
		tmpT.setEnd(40);
		
		conT.start();
		tmpT.start();*/
		
		//Endpoint.publish("http://localhost:8089/", new GenerateMesures());
		//Endpoint.publish("http://localhost:8084/", new GenerateTempThread());
		
		//System.out.println("Service deployed");
		

	}

}
