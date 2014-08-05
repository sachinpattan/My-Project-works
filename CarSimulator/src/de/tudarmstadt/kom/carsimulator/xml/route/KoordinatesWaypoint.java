package de.tudarmstadt.kom.carsimulator.xml.route;

import android.os.Parcel;
import android.os.Parcelable;


public class KoordinatesWaypoint implements Waypoint, Parcelable {
	private float _longitude;
	private float _latitude;
	
	public static final Creator<KoordinatesWaypoint> CREATOR = new Creator<KoordinatesWaypoint>() {
		@Override
		public KoordinatesWaypoint[] newArray(int size) {
			return new KoordinatesWaypoint[size];
		}
		
		@Override
		public KoordinatesWaypoint createFromParcel(Parcel source) {
			return new KoordinatesWaypoint(source);
		}
	};
	
	public KoordinatesWaypoint(float pLongitude, float pLatitude) {
		_longitude = pLongitude;
		_latitude = pLatitude;
	}

	public KoordinatesWaypoint(Parcel pSource) {
		_longitude = pSource.readFloat();
		_latitude = pSource.readFloat();
	}

	public float getLongitude() {
		return _longitude;
	}

	public void setLongitude(float pLongitude) {
		_longitude = pLongitude;
	}

	public float getLatitude() {
		return _latitude;
	}

	public void setLatitude(float pLatitude) {
		_latitude = pLatitude;
	}

	@Override
	public String getGoogleConformRepresentation() {
		return _longitude + "," + _latitude;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel pDest, int pFlags) {
		pDest.writeFloat(_longitude);
		pDest.writeFloat(_latitude);
	}

}
