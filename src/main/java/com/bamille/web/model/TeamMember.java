package com.bamille.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.bamille.web.jsonview.Views;

public class TeamMember {
	@JsonView(Views.Public.class)
	String firstName;
	@JsonView(Views.Public.class)
	String lastName;
	@JsonView(Views.Public.class)
	String presentation;
	@JsonView(Views.Public.class)	
	String imageLink;
	
	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public TeamMember(String firstName, String lastName, String presentation, String imageLink) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.presentation = presentation;
		this.imageLink = imageLink;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

}
