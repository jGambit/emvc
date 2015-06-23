package com.github.jgambit.emvc.controller;

import java.nio.channels.IllegalSelectorException;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.jgambit.emvc.controller.iface.ControllerStateIF;

public class ControllerState<O> implements ControllerStateIF<O> {
	
	private final AtomicInteger updatingForm = new AtomicInteger();
	private boolean cleared;
	private O currentObject;
	
	@Override
	public boolean isUpdatingForm() {
		return updatingForm.get() > 0;
	}
	
	@Override
	public void setUpdatingForm() {
		updatingForm.incrementAndGet();
	}
	
	public void unsetUpdatingForm() {
		if (updatingForm.decrementAndGet() < 0) {
			throw new IllegalSelectorException();
		}
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
		result.append(isUpdatingForm() ? "U" : "u");
		result.append(cleared ? "C" : "c");
		result.append(currentObject != null ? "O" : "o");
		return result.toString();
	}
	
}