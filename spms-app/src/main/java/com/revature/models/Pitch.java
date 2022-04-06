package com.revature.models;

// Java Bean:
// 1. fully encapsulated (private fields + getters/setters)
// 2. has a no-args constructor
// 3. implements Serializable (this one is outdated so I'm not including it below)
public class Pitch {
	private int pitch_id;
	private int person_id;
	private int genre_id;
	private int status_id;
	private String tentative_title;
	private String comp_date;
	private String description;
	private String blurb;
	private int length_id;
	
	public Pitch() {
		pitch_id = 0;
		person_id = 0;
		genre_id = 0;
	    status_id = 0;
		tentative_title= " ";;
		comp_date = " ";
		description= " ";
		blurb = " ";
		length_id = 0;
	}
	

	
	public int getPitch_id() {
		return pitch_id;
	}



	public void setPitch_id(int pitch_id) {
		this.pitch_id = pitch_id;
	}



	public int getPerson_id() {
		return person_id;
	}



	public void setPerson_id(int person_id) {
		this.person_id = person_id;
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



	public int getLength_id() {
		return length_id;
	}



	public void setLength_id(int length_id) {
		this.length_id = length_id;
	}



	@Override
	public String toString() {
		return "Pitch [pitch_id=" + pitch_id + ", person_id=" + person_id + ", genre_id=" + genre_id + ", status_id=" + status_id + ", tentative_title="
				+ tentative_title + ", comp_date=" +comp_date+", description="+ description+ ", blurb="+blurb+", length_id="+length_id+"]" ;
		
		
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + age;
//		result = prime * result + ((description == null) ? 0 : description.hashCode());
//		result = prime * result + id;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((species == null) ? 0 : species.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Pitch other = (Pitch) obj;
//		if (age != other.age)
//			return false;
//		if (description == null) {
//			if (other.description != null)
//				return false;
//		} else if (!description.equals(other.description))
//			return false;
//		if (id != other.id)
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (species == null) {
//			if (other.species != null)
//				return false;
//		} else if (!species.equals(other.species))
//			return false;
//		return true;
//	}

}
