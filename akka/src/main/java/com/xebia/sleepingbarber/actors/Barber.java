package com.xebia.sleepingbarber.actors;

import akka.actor.UntypedActor;

public class Barber extends UntypedActor{
	
	
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof Customer){
			Customer customer=(Customer)message;
		}
		
	}

	private void helpCustomer(Customer customer) {
	}

}
