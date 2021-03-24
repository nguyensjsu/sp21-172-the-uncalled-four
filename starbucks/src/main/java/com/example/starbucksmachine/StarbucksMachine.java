package com.example.starbucksmachine;

public class StarbucksMachine {

	State clearOrderState;
	State completeOrderState;
	State placeOrderState;
	State getOrderState;
	State waitingForOrderState;

	State state = waitingForOrderState;
	public StarbucksMachine() {

		clearOrderState = new ClearOrderState(this);
		placeOrderState = new PlaceOrderState(this);
		completeOrderState = new CompleteOrderState(this);

		getOrderState = new GetOrderState(this);
		waitingForOrderState = new WaitingForOrderState(this);

		state = waitingForOrderState;
	}
	public void getOrder() {
		state.getOrder();
	}
	public void clearOrder() {
		state.clearOrder();
	}
	public void completeOrder() {
		state.completeOrder();
	}
	void setState(State state) {
		this.state = state;
	}
	void printReceipt() {
		System.out.println("Thanks from Starbucks");
	}
	public State getState() {
		return state;
	}
	public State waitingForOrderState() {
		return waitingForOrderState;
	}
	public State placeOrderState() {
		return placeOrderState;
	}
	public State getOrderState() {
		return getOrderState;
	}
	public State completeOrderState() {
		return completeOrderState;
	}
	public State clearOrderState() {
		return clearOrderState;
	}
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("Welcome to Starbucks Reserved");
		result.append("\n\n");
		result.append("\nStarbucks Reserved " + state + "\n");
		return result.toString();
	}
}