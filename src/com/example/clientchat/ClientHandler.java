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
	private String data, identificador, usuario, IP, puerto;
	
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
		run(json);
	}
	
	public void enviarMensaje(String mns, int id, String destinatario) throws JSONException
	{
		JSONObject json = new JSONObject();
		JSONObject mensaje = new JSONObject();
		mensaje.put("horaFecha", Calendar.YEAR + "," + Calendar.DATE + "," + Calendar.DAY_OF_MONTH + "," + Calendar.HOUR_OF_DAY + "," + Calendar.MINUTE+"," + Calendar.SECOND + "," + Calendar.MILLISECOND);
		mensaje.put("mensaje", mns.toString());
		mensaje.put("destinatario", new String(destinatario));
		json.put("accion", new String("enviar"));
		json.put("identificador", id);
		json.accumulate("informacionMsj", mensaje);
		run(json);
	}
	public void listarUsuarios() throws JSONException
	{
		try{
			JSONObject json = new JSONObject();
			json.put("accion", new String("listar"));
			data = run(json);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
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
	public String getData() //Los datos que se reciben del server
	{
		return data;
	}
	public void setIdentificador(String identificador)
	{
		this.identificador = identificador;
	}
	public String getIdentificador()
	{
		return identificador;
	}
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}
	public String getUsuario()
	{
		return usuario;
	}
	public void setIP(String IP)
	{
		this.IP = IP;
	}
	public String getIP()
	{
		return IP;
	}
	public void setPuerto(String puerto)
	{
		this.puerto = puerto;
	}
	public String getPuerto()
	{
		return puerto;
	}
	
	
	
	
}
