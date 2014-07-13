package com.example.clientchat;

import org.json.JSONException;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
public class UsersActivity extends Activity {
	TextView textViewResponse;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_view);
		textViewResponse = (TextView) findViewById(R.id.textViewResult);
		ClientHandler handler = new ClientHandler();
		try {
			
			handler.listarUsuarios(); //Request
			//Los datos por get			
			
		} catch (JSONException e) {
			Log.d("UsersActivity", "JsonException");
			e.printStackTrace();
		}
		
		
		
	}
}
