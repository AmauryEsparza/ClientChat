package com.example.clientchat;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ClipData.Item;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

public class ChatActivity extends Activity {
	
	Button buttonEnviar;
	EditText editMensaje;
	ClientHandler objClient;
	TextView textViewChat;
	String destinatario;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_view);
		
		destinatario = getIntent().getExtras().getString("DESTINATARIO_ID");
		Log.d("CHATACTIVITY DESTINATARIO", destinatario+" ");
		//String destinatario = getActivity().
		textViewChat = (TextView) findViewById(R.id.textViewChatField);
		editMensaje = (EditText) findViewById(R.id.editTextMensaje);
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
		objClient = new ClientHandler();		
		buttonEnviar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v)
			{
				try {
					objClient.enviarMensaje(editMensaje.getText().toString(), destinatario);
					textViewChat.setText(objClient.getListaMensajesString()+" ");
					objClient.recibirMensaje(destinatario);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	
	
	
	
}
