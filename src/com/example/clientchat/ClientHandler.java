package com.example.clientchat;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ClientHandler {
	
	ClientTask clientTask;
	private String data, identificador, usuario, IP, puerto, llavePrivada;
	
	public ClientHandler()
	{
		
	}
	public void actualizarUsuario(String identificador, String status, String usuario, String IP, String puerto) throws JSONException
	{
		JSONObject json = new JSONObject();
		JSONObject informacion = new JSONObject();
		json.put("accion", new String("actualizar"));
		json.put("identificador", identificador );
		informacion.put("status", status);
		informacion.put("usuario",usuario);
		informacion.put("IP", IP);
		informacion.put("puerto", puerto);
		json.accumulate("informacion", informacion);
		data = run(json);
		//Procesa el response
		JSONObject response = new JSONObject(data);
		if (response.getString(status).equals("ok"))
		{
			identificador = response.getString("identificador");
			llavePrivada = response.getString("llavePrivada");
			this.usuario = usuario;
			this.IP = IP;
			this.puerto = puerto;
		}	
	}
	
	public void enviarMensaje(String mns, String destinatario) throws JSONException
	{
		JSONObject json = new JSONObject();
		JSONObject mensaje = new JSONObject();
		mensaje.put("horaFecha", Calendar.YEAR + "," + Calendar.DATE + "," + Calendar.DAY_OF_MONTH + "," + Calendar.HOUR_OF_DAY + "," + Calendar.MINUTE+"," + Calendar.SECOND + "," + Calendar.MILLISECOND);
		mensaje.put("mensaje", mns);
		mensaje.put("destinatario", destinatario);
		json.put("accion", new String("enviar"));
		json.put("identificador", identificador);
		json.accumulate("informacionMsj", mensaje);
		data = run(json);
	}
	
	public void listarUsuarios() throws JSONException
	{
		try{
			JSONObject json = new JSONObject();
			json.put("accion", new String("listar"));
			data = run(json);
			JSONObject response = new JSONObject(data);
			if(response.getString("status").equals("ok"))
			{
				//Coleccion
			}
		}catch (JSONException e) {
			e.printStackTrace();
			Log.d("ClientHandler", "JsonException");
		}
		
	}
	private String run(JSONObject json)
	{
		clientTask = new ClientTask();
		Log.d("run method", json.toString());
		clientTask.execute(json);
		if(clientTask.getStringJSON() != null){
			return clientTask.getStringJSON();
		}
		else{
			return "No se recibio nada";
		}
	}
	
	
	
	
	
	
}
