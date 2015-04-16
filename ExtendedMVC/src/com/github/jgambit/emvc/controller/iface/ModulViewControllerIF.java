package com.github.jgambit.emvc.controller.iface;

import com.github.jgambit.emvc.process.iface.GuiModulProcessIF;

public interface ModulViewControllerIF<P extends GuiModulProcessIF, O, C extends ModulControllerIF<P, O>> {

	public C getContentController();

}