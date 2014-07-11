package com.example.clientchat;
import java.io.IOException;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
public class UsersView extends Activity{
	ClientHandler objClient;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_users);
		objClient = new ClientHandler();
		try {
			objClient.listarUsuarios();
			//String var = objClient.listaUsuarios();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
