package com.github.jgambit.emvc.process;

import java.awt.Component;
import java.awt.Cursor;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.jgambit.emvc.process.iface.ViewStackIF;

public class ModulProcessViewStack implements ViewStackIF {

	private final Stack<Component> viewStack;
	private final AtomicInteger blockCount;
	
	public ModulProcessViewStack() {
		viewStack = new Stack<>();
		blockCount = new AtomicInteger();
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
		blockCount.incrementAndGet();
		getCurrentView().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	@Override
	public synchronized void unlbockView() {
		if (blockCount.decrementAndGet() < 0) {
			throw new IllegalStateException("ViewStack: unblock > block!");
		}
		getCurrentView().setCursor(Cursor.getDefaultCursor());
	}
	
}
