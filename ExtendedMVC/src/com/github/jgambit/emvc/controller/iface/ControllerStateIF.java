package com.github.jgambit.emvc.controller.iface;

public interface ControllerStateIF<O> {

	public boolean isUpdatingForm();

	public void setUpdatingForm();
	
	public void unsetUpdatingForm();

	public boolean isCleared();

	public void setCleared(boolean cleared);

	public O getCurrentObject();

	public void setCurrentObject(O currentObject);

}