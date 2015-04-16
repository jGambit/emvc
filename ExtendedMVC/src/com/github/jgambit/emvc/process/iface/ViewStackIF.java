package com.github.jgambit.emvc.process.iface;

import java.awt.Component;

public interface ViewStackIF {

	public void pushView(Component view);

	public Component popView();

	public Component getCurrentView();

	public void blockView();

	public void unlbockView();

}