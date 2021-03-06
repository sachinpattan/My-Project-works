//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.29 at 05:22:53 PM CEST 
//


package de.tudarmstadt.kom.carsimulator.xml.route;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Route implements Parcelable {
    protected List<Waypoint> waypoint;
    public static final Creator<Route> CREATOR = new Creator<Route>() {
		@Override
		public Route[] newArray(int size) {
			return new Route[size];
		}
		
		@Override
		public Route createFromParcel(Parcel source) {
			return new Route(source);
		}
	};
	
	public Route() {
	}
    
    public Route(Parcel pSource) {
    	pSource.readList(getWaypoint(), Waypoint.class.getClassLoader());
    }

	public List<Waypoint> getWaypoint() {
        if (waypoint == null) {
            waypoint = new ArrayList<Waypoint>();
        }
        return this.waypoint;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel pDest, int pFlags) {
		pDest.writeList(waypoint);
	}

}
