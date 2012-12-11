package com.xebia.akka;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import static akka.actor.Actors.*;
import akka.actor.UntypedActor;

public class AkkaActorExample {

	public static class MemoryActor extends UntypedActor {
		final Map<String, Date> seen = new HashMap<String, Date>();

		public void onReceive(Object messageObject) throws Exception {
			String message = messageObject.toString();
			if (message.equals("DUMP")) {
				getSender().tell(seen.toString());
				System.out.println("Received Message to Dump "+seen);
			} else {
				Date date = new Date();
				seen.put(message, date);
				System.out.println("Message " + message + " recorded at " + date);
			}
		}

		@Override
		public void preStart() {
			System.out.println( "Started actor with Name "+toString());
		}

		@Override
		public void postStop() {
			System.out.println( "Stopped actor with Name "+toString());
		}
		
		
	}

	public static void main(String[] args) throws InterruptedException {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef memoryActorRef = system.actorOf(new Props(MemoryActor.class),"myActor");
		memoryActorRef.tell("Hello");
		Thread.sleep(1000);
		memoryActorRef.tell("DUMP");
		Thread.sleep(1000);
		System.out.println("Shutting down Actor....");
		system.stop(memoryActorRef);
		System.out.println("Terminated : "+memoryActorRef.isTerminated());
		system.awaitTermination();
	}

}
