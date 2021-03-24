
package com.example.starbucksmachine;
public interface State {
 
	public void waitingForOrderState();
	public void getOrder();
	public void clearOrder();
	public void completeOrder();
	public void placeOrder();

}
