package com.revature.app;

// POJO: plain old Java object
// this is a class that generally just represents data
// without any special behaviors - just fields
public class Pitch {
	int pitch_id;
	int person_id;
	int genre_id;
	int status_id;
	String tentative_title;
	String comp_date;
	String description;
	String blurb;
	int length_id;
	
	@Override
	public String toString() {
		return "Pitch [pitch_id=" + pitch_id + ", person_id=" + person_id + ", genre_id=" + genre_id + ", status_id=" + status_id + ", tentative_title="
				+ tentative_title + ", comp_date=" +comp_date +" , description=" +description+", blurb="+blurb+", length_id="+length_id+"]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length_id;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + pitch_id;
		result = prime * result + ((tentative_title == null) ? 0 : tentative_title.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pitch other = (Pitch) obj;
		if (length_id != other.length_id)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (pitch_id != other.pitch_id)
			return false;
		if (tentative_title == null) {
			if (other.tentative_title != null)
				return false;
		} else if (!tentative_title.equals(other.tentative_title))
			return false;
		if (blurb == null) {
			if (other.blurb != null)
				return false;
		} else if (!blurb.equals(other.blurb))
			return false;
		return true;
	}
}
