package com.example.clientchat;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class ClientHandler {
	
	ClientTask clientTask;
	String data;
	public ClientHandler()
	{
		
	}
	public void enviarMensaje()
	{
		JSONObject json = new JSONObject();
		//Crear el objeto JSON y llamar a run()
	}
	public void listarUsuarios() throws JSONException, IOException
	{
		JSONObject json = new JSONObject();
		json.put("accion", new String("listar"));
		run(json);
		
	}
	public void run(JSONObject json)
	{
		clientTask = new ClientTask();
		clientTask.execute(json);
		//data = clientTask.execute(json);	
	}
	public String getData() //Los datos que se reciben del server
	{
		return data;
	}
	
	
	
	
}
