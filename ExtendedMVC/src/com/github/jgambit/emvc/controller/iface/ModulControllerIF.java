package com.github.jgambit.emvc.controller.iface;

import com.github.jgambit.emvc.process.iface.GuiModulProcessIF;

public interface ModulControllerIF<P extends GuiModulProcessIF, O> {

	/**
	 * Leert alle Models und setzt sie auf den Ursprungszustand zurück.
	 */
	public void clearForm();

	/**
	 * Befüllt alle Models des Controllers mit Daten aus dem gegebenen Konfigurationsobjekt.
	 * Dabei wird der Controller automatisch vorher mittels {@link #clearForm()} geleert. 
	 * @param config Konfigurationsobjekt mit Daten für die Models
	 */
	public void fillForm(O config);

	/**
	 * Aktiviert bzw. deaktiviert die Models. 
	 * @param permit
	 */
	public void permitForm(boolean permit);

	/**
	 * @return Gibt an, ob sich der Controller gerade aktualisiert.
	 */
	public boolean isUpdatingForm();

	/**
	 * @return Liefert den Process mit dem der Controller erstellt wurde
	 */
	public P getModulProcess();

}