/**AsyncTask sirve para no utilizar objetos Thread ni objetos Handler. 
 * Es mas sencillo de leer.
 */

package com.example.clientchat;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class ClientTask extends AsyncTask<JSONObject, Void , Void>{
	//Recibe un String que es la accion en sintaxis JSON
	final String serverAddress = "10.1.10.1";
	final int serverPort = 13373;
	
	@Override
	protected Void doInBackground(JSONObject... action)
	{
		Socket socket = null;
		try{
			socket = new Socket(serverAddress, serverPort);
			/* Salida */
			InputStream inputStream = socket.getInputStream();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			out.write(action[0].toString()); //Se manda el objeto JSON 
			out.flush();
			socket.close();
			
		} catch(UnknownHostException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
