package com.example.clientchat;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
public class UsersActivity extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);		
		ClientHandler handler = new ClientHandler();
		try {
			handler.listarUsuarios(); //Request		
		} catch (JSONException e) {
			Log.d("UsersActivity", "JsonException");
			e.printStackTrace();
		}
		ListAdapter adaptador = new SimpleAdapter(
				UsersActivity.this, handler.getArrayListUsuarios(), R.layout.list_item,
				new String[] {"usuario", "status"}, new int[]{R.id.user, R.id.status});
		setListAdapter(adaptador);
		
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), ChatActivity.class );
				startActivity(intent);
				
			}
		});
		
		
		
		
	}
}
