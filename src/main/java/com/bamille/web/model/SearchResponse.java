package com.bamille.web.model;

import java.util.List;

import com.bamille.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class SearchResponse {
	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	List<TeamMember> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<TeamMember> getResult() {
		return result;
	}

	public void setResult(List<TeamMember> result) {
		this.result = result;
	}
}
