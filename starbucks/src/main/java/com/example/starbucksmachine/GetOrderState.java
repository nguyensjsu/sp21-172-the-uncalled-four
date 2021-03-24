
package com.example.starbucksmachine;

import java.util.Random;

public class GetOrderState implements State {
	StarbucksMachine starbucksmachine;
 
	public GetOrderState(StarbucksMachine starbucksmachine) {
		this.starbucksmachine = starbucksmachine;
	}
	public void waitingForOrderState(){
		System.out.println("Override");
	}
	public void getOrder(){
		System.out.println("Override");
	}
	public void clearOrder(){
		System.out.println("Override");
	}
	public void completeOrder(){
		System.out.println("Override");
	}
	public void placeOrder(){
		System.out.println("Override");
	}

}
