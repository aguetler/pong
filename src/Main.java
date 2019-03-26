import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		try {
		    Socket echoSocket = new Socket("localhost", 7000);
		    BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		    
		    String[] logs = new String[4];
		    Scanner sc = new Scanner(System.in);
		    String[] ballDetails = new String[5];
		    String[] myPaddle = new String[3];
		    double myPaddlePos = 0.0;
		    
		    while(true) {
		    	for (int i=0; i<4; i++) {
		    		logs[i] = in.readLine();
	    			// System.out.println(logs[i]);
	    			// ball = 2
	    			if (i == 0) {
	    				myPaddle = logs[i].split(" ");
	    				myPaddlePos = Double.parseDouble(myPaddle[2]);
	    				System.out.println(logs[0]);
	    			}
	    			if (i == 2) {
	    				ballDetails = logs[i].split(" ");
	    				System.out.println(logs[2]);
	    			}
		    	}
		    	movePaddle(sc, out, ballDetails, myPaddlePos);		    	
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void movePaddle(Scanner sc, PrintWriter out, String[] ballData, double myPaddlePos) {
		System.out.println("Ball x-Position: " + ballData[1]);
		System.out.println("Ball y-Position: " + ballData[2]);
		System.out.println("Ball x-Speed: " + ballData[3]);
		System.out.println("Ball y-Speed: " + ballData[4]);
		double xPos = Double.parseDouble(ballData[1]);
		double yPos = Double.parseDouble(ballData[2]);
		double xSpeed = Double.parseDouble(ballData[3]);
		double ySpeed = Double.parseDouble(ballData[4]);
    	// int moveTo = Integer.parseInt(sc.nextLine());
				
		String moveTo = "0";
		
		double distance = Math.abs(myPaddlePos - yPos); 
		if (distance < 50) {
			moveTo = "0";
			out.println("move "+ moveTo);	
			return;
		}
		
		if (xSpeed < 0) {
			moveTo = "0";
		} else {
			if (ySpeed <= 0.1 && ySpeed >= -0.1) {
				moveTo = "0";
			} else if (ySpeed > 0.0) {
				moveTo = "18";
			} else {
				moveTo = "-18";
			}
		}
    	out.println("move "+ moveTo);		
	}

}
