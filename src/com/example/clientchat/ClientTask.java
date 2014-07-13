/**AsyncTask sirve para no utilizar objetos Thread ni objetos Handler. 
 * Es mas sencillo de leer.
 */

package com.example.clientchat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ClientTask extends AsyncTask<JSONObject, Void , Void>{
	final String serverAddress = "192.168.1.79";
	final int serverPort = 13373;
	JSONArray jsonArray;
	StringBuilder sb;
	@Override
	protected Void doInBackground(JSONObject... action)
	{
		String next;
		Socket socket = null;
		sb = new StringBuilder();
		try{
			socket = new Socket(serverAddress, serverPort);
			/* Request */
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Log.d("JSONObject",""+action[0]+'\0');
			out.write(action[0].toString()+'\0');
			//out.flush();
			
			out.close();
			/* Response */
			//socket = new Socket(serverAddress, serverPort);
			while(in.readLine() != "\0")
			{
				next = in.readLine();
				Log.d("RESPONSE", " "+next);
				sb.append(next + "\n");	
				
			}
			in.close();
			socket.close();	
		} catch(UnknownHostException e){
			Log.d("UnknownHost", "UnknowHost");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("IOException", "IOException");
			e.printStackTrace();		
		}
		return null;
		
	}
	public String getStringJSON()
	{
		return sb.toString();
	}
	
}
