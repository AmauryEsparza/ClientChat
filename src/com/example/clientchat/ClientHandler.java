package com.example.clientchat;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ClientHandler {
	
	ClientTask clientTask;
	private static String data, identificador, usuario, IP, puerto, llavePrivada;
	private static ArrayList<HashMap<String, String>> arrayListUsuarios;
	private String listaMensajesString;
	
	public ClientHandler()
	{
		
	}
	public void actualizarUsuario(String identificador, String status, String usuario, String IP, String puerto) throws JSONException
	{
		data = "";
		JSONObject json = new JSONObject();
		JSONObject informacion = new JSONObject();
		json.put("accion", new String("actualizar"));
		json.put("identificador", identificador );
		informacion.put("status", status);
		informacion.put("usuario",usuario);
		informacion.put("IP", IP);
		informacion.put("puerto", puerto);
		json.accumulate("informacion", informacion);
		try {
			data = run(json);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Procesa el response
		JSONObject response = new JSONObject(data);
		if (response.getString("status").equals("ok"))
		{
			this.identificador = response.getString("identificador");
			this.llavePrivada = response.getString("llavePrivada");
			this.usuario = usuario;
			this.IP = IP;
			this.puerto = puerto;
		}	
	}
	
	public void enviarMensaje(String mns, String destinatario) throws JSONException
	{
		data = "";
		JSONObject json = new JSONObject();
		JSONObject mensaje = new JSONObject();
		mensaje.put("horaFecha", Calendar.YEAR + "," + Calendar.DATE + "," + Calendar.DAY_OF_MONTH + "," + Calendar.HOUR_OF_DAY + "," + Calendar.MINUTE+"," + Calendar.SECOND + "," + Calendar.MILLISECOND);
		mensaje.put("mensaje", mns);
		mensaje.put("destinatario", destinatario);
		json.put("accion", new String("enviar"));
		json.put("identificador", identificador);
		json.accumulate("informacionMsj", mensaje);
		try {
			data = run(json);
			JSONObject response = new JSONObject(data);
			if(response.getString("status").equals("ok"))
			{
				data = "";
				json = new JSONObject();
				json.put("accion", "listarMensajes");
				data = run(json);
				Log.d("ListarMensajes", data+" ");
				JSONObject objetoMensaje = new JSONObject(data);
				JSONObject enviadoMsj = objetoMensaje.getJSONObject("enviadoMsj");
				JSONArray listaMensajes = enviadoMsj.getJSONArray(identificador);
				for(int i = 0; i < listaMensajes.length(); i++)
				{
					Log.d("Despues del ciclo", "Despues del ciclo");
					JSONObject jsonTemp = listaMensajes.getJSONObject(i);
					Log.d("Despues del JSONObject", jsonTemp.getString("mensaje")+" ");
					listaMensajesString = (jsonTemp.getString("mensaje")+"\n");
					Log.d("MensajesString", listaMensajesString);
				}
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	public void recibirMensaje(String llavePrivada) throws JSONException
	{
		JSONObject mandarMensaje = new JSONObject();
		mandarMensaje.put("accion", "recibir");
		mandarMensaje.put("identificador", identificador);
		mandarMensaje.put("llavePrivada", llavePrivada);
		try {
			data = run(mandarMensaje);
			Log.d("RESPONSE RECIBIR MENSAJE", data.toString()+" ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void listarUsuarios() throws JSONException
	{
		try{
			data = "";
			JSONObject json = new JSONObject();
			JSONArray usuarios = new JSONArray();
			
			json.put("accion", new String("listar"));
			data = run(json);
			Log.d("RESPONSE listarUsuarios ClientHandler", data+" ");
			JSONObject response = new JSONObject(data);
			JSONObject informacion;
			JSONObject usuario;
			if(response.getString("status").equals("ok"))
			{
				arrayListUsuarios = new ArrayList<HashMap<String,String>>();
				informacion = response.getJSONObject("informacion");
				usuario = informacion.getJSONObject(identificador);
				String status = usuario.getString("status");
				String usuarioString = usuario.getString("usuario");
				HashMap<String, String> usuarioTemp = new HashMap<String,String>();
				usuarioTemp.put("status", status+" ");
				
				usuarioTemp.put("usuario", usuarioString+" ");
				arrayListUsuarios.add(usuarioTemp);
				/*for(int i = 0; i < usuarios.length(); i++)
				{
					JSONObject c = usuarios.getJSONObject(i);
					String status = c.getString("status");
					String usuario = c.getString("usuario");
					Log.d("Usuario "+i, usuario+" "+status);
					HashMap<String, String> usuarioTemp = new HashMap<String,String>();
					usuarioTemp.put("status", status);
					usuarioTemp.put("usuario", usuario);
					arrayListUsuarios.add(usuarioTemp);
				}*/
			}
		}catch (JSONException e) {
			e.printStackTrace();
			Log.d("ClientHandler", "JsonException");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private String run(JSONObject json) throws InterruptedException, ExecutionException
	{
		clientTask = new ClientTask();
		clientTask.execute(json);
		clientTask.get();
		
		if(clientTask.getStringJSON() != null){
			return clientTask.getStringJSON();
		}
		else{
			return "No se recibio nada";
		}
		 
	}
	public ArrayList<HashMap<String, String>> getArrayListUsuarios()
	{
		return arrayListUsuarios;
	}
	public String getListaMensajesString()
	{
		return listaMensajesString;
	}
		
	
}
