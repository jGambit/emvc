package com.github.jgambit.emvc.gui.iface;

import com.github.jgambit.emvc.controller.iface.ModulViewControllerIF;


public interface ModulViewPanelIF<T extends ModulViewControllerIF<?, ?, ?>, P extends ModulBasePanelIF<?>> {

	public void setController(T viewController);
	public P getSubPanel();
	
}
