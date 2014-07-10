package com.example.clientchat;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	Button buttonEnviar;
	Client objClient;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
		buttonEnviar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v)
			{
				//Alguna accion que se encuentra en el ActionHandler
				//Se manda en la clase Client
			}
		});
		
	}

}
