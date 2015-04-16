package com.github.jgambit.emvc.process;

import java.awt.Component;
import java.awt.Cursor;
import java.util.Stack;

import com.github.jgambit.emvc.process.iface.ViewStackIF;

public class ModulProcessViewStack implements ViewStackIF {

	private final Stack<Component> viewStack;
	
	public ModulProcessViewStack() {
		viewStack = new Stack<>();
	}
	
	@Override
	public void pushView(Component view) {
		viewStack.push(view);
	}
	
	@Override
	public Component popView() {
		return viewStack.pop();
	}
	
	@Override
	public Component getCurrentView() {
		return viewStack.peek();
	}
	
	@Override
	public synchronized void blockView() {
		getCurrentView().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	@Override
	public synchronized void unlbockView() {
		getCurrentView().setCursor(Cursor.getDefaultCursor());
	}
	
}
