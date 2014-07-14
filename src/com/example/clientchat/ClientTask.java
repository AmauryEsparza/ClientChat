/**AsyncTask sirve para no utilizar objetos Thread ni objetos Handler. 
 * Es mas sencillo de leer.
 */

package com.example.clientchat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ClientTask extends AsyncTask<JSONObject, Void , Void>{
	final String serverAddress = "192.168.1.79";
	final int serverPort = 13373;
	JSONArray jsonArray;
	StringBuilder sb;
	String response;
	@Override
	protected Void doInBackground(JSONObject... action)
	{
		
		response = "";
		Socket socket = null;
		sb = new StringBuilder();
		try{
			socket = new Socket(serverAddress, serverPort);
			/* Request */
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			Log.d("JSONObject ClientTask",""+action[0]+'\0');
			out.write(action[0].toString()+'\0');
			out.flush();
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			response = in.readLine();
			Log.d("RESPONSE", response);
			
			out.close();
			in.close();
			socket.close();	
		} catch(UnknownHostException e){
			Log.d("UnknownHost", "UnknowHost");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("ClientTask IOException", "IOException");
			e.printStackTrace();		
		}
		
		return null;
	}
	
	public String getStringJSON()
	{
		Log.d("ClientTask getStringJSON", response+" ");
		return response;
	}
	
}
