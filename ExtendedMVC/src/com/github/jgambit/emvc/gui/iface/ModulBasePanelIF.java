package com.github.jgambit.emvc.gui.iface;

import com.github.jgambit.emvc.controller.iface.ModulControllerIF;

public interface ModulBasePanelIF<T extends ModulControllerIF<?, ?>> {
	
	public void setController(T controller);

}
