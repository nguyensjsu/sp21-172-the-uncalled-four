
package com.example.starbucksmachine;

public class ClearOrderState implements State {
    StarbucksMachine starbucksmachine;
 
    public ClearOrderState(StarbucksMachine starbucksmachine) {
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
