package com.github.jgambit.emvc.process;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Window;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.jgambit.emvc.process.iface.GuiModulProcessIF;
import com.github.jgambit.emvc.process.iface.ViewStackIF;


public abstract class GuiModulProcess extends HeadlessModulProcess implements GuiModulProcessIF {
	
	private final ViewStackIF viewStack;
	
	public GuiModulProcess(ViewStackIF parentViewStack) {
		super();
		viewStack = parentViewStack == null ? new ModulProcessViewStack() : parentViewStack;
	}
	
	@Override
	public void startExceptionDialog(Throwable th) {
		JOptionPane.showMessageDialog(getCurrentViewComponent(), th.getMessage(), "Der Vorgang konnte nicht ausgeführt werden.", JOptionPane.ERROR_MESSAGE);
		_log.log(Level.SEVERE, th.getMessage());
	}
	
	protected void showView(JFrame view, boolean modal) {
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showWindow(view, modal);
	}
	
	protected void showDialog(JDialog dialog, boolean modal) {
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModalityType(modal ? ModalityType.APPLICATION_MODAL: ModalityType.MODELESS);
		showWindow(dialog, modal);
	}

	void showWindow(Window window, boolean modal) {
		window.setAlwaysOnTop(modal);
		window.setAutoRequestFocus(true);
		window.setVisible(true);
	}
	
	protected Component getCurrentViewComponent() {
		return getViewStack().getCurrentView();
	}
	
	@Override
	public void blockView() {
		getViewStack().blockView();
	}
	
	@Override
	public synchronized void unblockView() {
		getViewStack().unlbockView();
	}

	ViewStackIF getViewStack() {
		return viewStack;
	}
	
	public void pushView(Component toPush) {
		getViewStack().pushView(toPush);
	}
	
	public Component popView() {
		return getViewStack().popView();
	}
	
}
