package de.tudarmstadt.kom.carsimulator;

import de.tudarmstadt.kom.carsimulator.xml.route.KoordinatesWaypoint;
import de.tudarmstadt.kom.carsimulator.xml.route.Route;

interface SimulationEngineAPI {
	KoordinatesWaypoint getCurrentPosition();
	int getCurrentSpeed();
	void startSimulation();
	void pauseSimulation();
	void endSimulation();
	boolean setRoute(in Route pRoute);
	boolean isPaused();
	boolean isStarted();
	int getActiveElement();
	double getValue(in String pPID);
	int getCurrentSpeedLimit();
}