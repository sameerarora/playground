package com.xebia.sleepingbarber.actors;

import com.xebia.sleepingbarber.domain.Haircut;

import akka.actor.UntypedActor;

public class Customer extends UntypedActor {
	
	private final int id;
	
	public Customer(int id){
		this.id=id;
	}
	
	
	private boolean shorn = false;

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof Haircut){
			shorn=true;
			System.out.println("Customer "+id+" got a Haircut");
		}
	}

}
