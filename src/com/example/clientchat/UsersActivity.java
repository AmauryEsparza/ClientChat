package com.example.clientchat;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
public class UsersActivity extends Activity {
	TextView textViewResponse;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_view);
		textViewResponse = (TextView) findViewById(R.id.textViewResult);
		ClientHandler objClient = new ClientHandler();
		String serverResponse;
		try {
			objClient.actualizarUsuario("0", "online","Amaury","192.169.1.79", "13373");
			serverResponse = objClient.getData();
			//Procesar los datos recibidos
			
			objClient.listarUsuarios(); //Request
			serverResponse = objClient.getData(); //Response
			Log.d("Continuo", "Continuo");
			JSONArray jsonArray = new JSONArray(serverResponse);
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				textViewResponse.append(jsonObject.getString("informacion"));			
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("UsersActivity", "JsonException");
		}
		
		
		
	}
}
