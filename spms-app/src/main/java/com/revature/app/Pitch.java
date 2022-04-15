package com.revature.app;

import java.util.Objects;

//POJO
public class Pitch {
	int pitch_id; 
	int user_id;
	int genre_id;
	int status_id;
	int length_id;
	String tentative_title;
	String comp_date;
	String description;
	String blurb;
	
	
	@Override
	public String toString() {
		return "Pitch [pitch_id=" + pitch_id + ", user_id=" + user_id + ", genre_id=" + genre_id + ", status_id="
				+ status_id + ", length_id=" + length_id + ", tentative_title=" + tentative_title + ", comp_date="
				+ comp_date + ", description=" + description + ", blurb=" + blurb + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(blurb, comp_date, description, genre_id, length_id, pitch_id, status_id, tentative_title,
				user_id);
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
		return Objects.equals(blurb, other.blurb) && Objects.equals(comp_date, other.comp_date)
				&& Objects.equals(description, other.description) && genre_id == other.genre_id
				&& length_id == other.length_id && pitch_id == other.pitch_id && status_id == other.status_id
				&& Objects.equals(tentative_title, other.tentative_title) && user_id == other.user_id;
	}
	
	
	
	
	

}
