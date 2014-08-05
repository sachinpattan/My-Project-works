package de.tudarmstadt.kom.carsimulator.xml.route;

import android.os.Parcel;


public class StringWaypoint implements Waypoint {
	private String _location;
	public static final Creator<StringWaypoint> CREATOR = new Creator<StringWaypoint>() {
		@Override
		public StringWaypoint[] newArray(int size) {
			return new StringWaypoint[size];
		}

		@Override
		public StringWaypoint createFromParcel(Parcel source) {
			return new StringWaypoint(source);
		}
	};

	public StringWaypoint(String pLocation) {
		_location = pLocation;
	}

	public StringWaypoint(Parcel pSource) {
		_location = pSource.readString();
	}

	public String getLocation() {
		return _location;
	}

	public void setLocation(String pLocation) {
		_location = pLocation;
	}

	@Override
	public String getGoogleConformRepresentation() {
		String result = new String(_location);
		if (result.contains("�")) {
			result = result.replace("�", "ae");
		}
		if (result.contains("�")) {
			result = result.replace("�", "oe");
		}
		if (result.contains("�")) {
			result = result.replace("�", "ue");
		}
		if (result.contains("�")) {
			result = result.replace("�", "ss");
		}
		if (result.contains(" ")) {
			result = result.replace(" ", "%21");
		}
		return result;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel pDest, int pFlags) {
		pDest.writeString(_location);
	}

}
