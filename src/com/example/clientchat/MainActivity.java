package com.example.clientchat;
import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity {

	Button buttonIngresar;
	EditText editUsuario;
	ClientHandler handler = new ClientHandler();
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonIngresar = (Button) findViewById(R.id.buttonIngresar);
		editUsuario = (EditText) findViewById(R.id.editTextUsuario);
		
		buttonIngresar.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			{
				try {
					
					handler.actualizarUsuario("0", "online",editUsuario.getText().toString(),"192.169.1.79", "13373");
				
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}
}
