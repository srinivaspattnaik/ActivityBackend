package com.stackroute.activitystream.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table
public class Circle
{
	
	//circle id should be auto generated.  User will provide it.
	@Id
	@NotNull(message="Circle Id can not be Empty")
	@Size(min=8, max=15)

	private String circle_id;
	@NotNull(message="Circle name can not be Empty")
	private String circle_name;
	
	private String circle_owner;
	
	//description can be empty
	@NotNull(message="Description can not be Empty")
	private String description;
	
	@Column(updatable=false)
	private Date creation_date;

	public String getCircle_id() {
		return circle_id;
	}

	public void setCircle_id(String circle_id) {
		this.circle_id = circle_id;
	}

	public String getCircle_name() {
		return circle_name;
	}

	public void setCircle_name(String circle_name) {
		this.circle_name = circle_name;
	}

	public String getCircle_owner() {
		return circle_owner;
	}

	public void setCircle_owner(String circle_owner) {
		this.circle_owner = circle_owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	
	
}
