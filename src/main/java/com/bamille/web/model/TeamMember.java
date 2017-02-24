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

	public TeamMember(String firstName, String lastName, String presentation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.presentation = presentation;
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

	
//	@Override
//	public String toString() {
//		return "TeamMember [firstName=" + firstName + ", lastName=" + lastName + "]";
//	}
}
