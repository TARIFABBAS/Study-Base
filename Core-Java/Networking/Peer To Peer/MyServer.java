// Peer to Peer

// Server side program:

import java.io.*;
import java.net.*;

public class MyServer{
	
	ServerSocket ss;
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	
	public MyServer(){
		try{
			System.out.println("Server Connected");
			ss = new ServerSocket(10); 		// Server program requires ServerSocket class object... Setting Port Number 10
			s = ss.accept();		// It will wait until implicitly connection made between server and client and then return new socket object
			System.out.println("Client Connected");
			
			// Opening Input Out Stream from Server Side...
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			
			// calling server chat method that receive and send data...
			serverChat();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void main(String...a){
		new MyServer();
	}
	
	public void serverChat() throws IOException{
		String str, s1;
		
		do{
			str = dis.readUTF();		// It will fetch data from client OutputStream
			System.out.println("Client: " + str);		// printing client message...
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// reading data from keyboard
			s1 = br.readLine();		
			dos.writeUTF("Server: " + s1);			// write to output stream so client will receive it..
			dos.flush();
		}while(!str.equals("stop"));
	}
}