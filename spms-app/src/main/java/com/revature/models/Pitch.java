package com.revature.models;

import java.util.Objects;

public class Pitch {
	
	private int pitch_id;
	private int user_id;
	private int genre_id;
	private int status_id;
	private int length_id;
	private String tentative_title;
	private String comp_date;
	private String description; 
	private String blurb; 

	
	
	public Pitch() {
		
	 pitch_id = 0;
	 user_id = 0 ;
	genre_id = 0 ;
	status_id = 0;
	length_id = 0;
    tentative_title="";
     comp_date="";
	description=""; 
	blurb=""; 
		
	}

	public int getPitch_id() {
		return pitch_id;
	}

	public void setPitch_id(int pitch_id) {
		this.pitch_id = pitch_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getLength_id() {
		return length_id;
	}

	public void setLength_id(int length_id) {
		this.length_id = length_id;
	}

	public String getTentative_title() {
		return tentative_title;
	}

	public void setTentative_title(String tentative_title) {
		this.tentative_title = tentative_title;
	}

	public String getComp_date() {
		return comp_date;
	}

	public void setComp_date(String comp_date) {
		this.comp_date = comp_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

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
