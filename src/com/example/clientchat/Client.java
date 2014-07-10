package com.example.clientchat;

public class Client {
	
	private String action;
	ClientTask clientTask;
	public Client()
	{
		
	}
	public Client(String action)
	{
		this.action = action;
	}
	
	public void setAction(String action)
	{
		this.action = action;
	}
	public void Run()
	{
		clientTask = new ClientTask();
		clientTask.execute(action);
	}
	
	
}
