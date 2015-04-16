package com.github.jgambit.emvc.process.iface;

public interface GuiModulProcessIF {
	
	public void startExceptionDialog(Throwable th);
	public void blockView();
	public void unblockView();
	
}
