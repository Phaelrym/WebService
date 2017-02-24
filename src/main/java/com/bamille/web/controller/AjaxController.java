package com.bamille.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bamille.web.model.SearchResponse;
import com.bamille.web.jsonview.Views;
import com.bamille.web.model.SearchRequest;
import com.bamille.web.model.TeamMember;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class AjaxController {

	List<TeamMember> teamMembers;

	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getSearchTeamMember")
	public SearchResponse getSearchTeamMember(@RequestBody SearchRequest searchRequest) {

		SearchResponse result = new SearchResponse();

		if (isValidSearch(searchRequest)) {
			List<TeamMember> resultSearchTeamMember = findTeamMembers(searchRequest);
			if (!CollectionUtils.isEmpty(resultSearchTeamMember)) {
				result.setCode("200");
				result.setMsg("");
				result.setResult(resultSearchTeamMember);
			} else {
				result.setCode("204");
				result.setMsg("Aucun membre de l'équipe ne correspond à la recherche");
			}
		} else {
			result.setCode("400");
			result.setMsg("Aucun critère de recherche n'est renseigné");
		}

		return result;

	}

	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getSearchFullTeam")
	public SearchResponse getSearchFullTeam() {

		SearchResponse result = new SearchResponse();

		List<TeamMember> resultSearchFullTeam = findFullTeam();
		if (!CollectionUtils.isEmpty(resultSearchFullTeam)) {
			result.setCode("200");
			result.setMsg("");
			result.setResult(resultSearchFullTeam);
		} else {
			result.setCode("204");
			result.setMsg("Il n'existe aucun membre dans cette équipe");
		}

		return result;

	}

	private List<TeamMember> findFullTeam() {
		List<TeamMember> result = this.teamMembers;
		return result;
	}

	private List<TeamMember> findTeamMembers(SearchRequest searchRequest) {
		List<TeamMember> result = new ArrayList<TeamMember>();
		for (TeamMember teamMember : this.teamMembers) {
			if (teamMember.getFirstName().equalsIgnoreCase(searchRequest.getFirstName())
					|| teamMember.getLastName().equalsIgnoreCase(searchRequest.getLastName())) {
				result.add(teamMember);
			}
		}
		return result;
	}

	private boolean isValidSearch(SearchRequest searchRequest) {
		return searchRequest != null && (!StringUtils.isEmpty(searchRequest.getFirstName())
				|| !StringUtils.isEmpty(searchRequest.getLastName()));
	}

	@PostConstruct
	private void initData() {
		teamMembers = new ArrayList<TeamMember>();

		TeamMember teamMember1 = new TeamMember("firstName1", "lastName1", "the presentation of firstname1");
		TeamMember teamMember2 = new TeamMember("firstName2", "lastName2", "the presentation of firstname2");
		TeamMember teamMember3 = new TeamMember("firstName3", "lastName3", "the presentation of firstname3");
		teamMembers.add(teamMember1);
		teamMembers.add(teamMember2);
		teamMembers.add(teamMember3);

	}
}
