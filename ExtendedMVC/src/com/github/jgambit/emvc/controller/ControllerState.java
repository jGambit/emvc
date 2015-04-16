package com.github.jgambit.emvc.controller;

import com.github.jgambit.emvc.controller.iface.ControllerStateIF;

public class ControllerState<O> implements ControllerStateIF<O> {
	
	private boolean updatingForm;
	private boolean cleared;
	private O currentObject;
	
	@Override
	public boolean isUpdatingForm() {
		return updatingForm;
	}
	
	@Override
	public void setUpdatingForm(boolean updatingForm) {
		this.updatingForm = updatingForm;
	}
	
	@Override
	public boolean isCleared() {
		return cleared;
	}
	
	@Override
	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	
	@Override
	public O getCurrentObject() {
		return currentObject;
	}
	
	@Override
	public void setCurrentObject(O currentObject) {
		this.currentObject = currentObject;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(updatingForm ? "U" : "u");
		result.append(cleared ? "C" : "c");
		result.append(currentObject != null ? "O" : "o");
		return result.toString();
	}
	
}