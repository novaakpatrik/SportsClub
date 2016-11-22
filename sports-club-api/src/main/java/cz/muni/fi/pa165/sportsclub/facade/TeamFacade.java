package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.team.TeamCreateDto;
import cz.muni.fi.pa165.sportsclub.dto.team.TeamDto;

import java.util.List;

/**
 * Created by norbert on 19.11.16.
 */
public interface TeamFacade {

    /**
     * Creates a new team.
     *
     * @param dto TeamDto with the team data
     */
    void createTeam(TeamCreateDto dto);

    /**
     * Deletes a team with the given id.
     *
     * @param id Id of the team to delete
     */
    void deleteTeam(long id);

    /**
     * Updates the team data.
     *
     * @param dto TeamDto with the updated data
     */
    void updateTeam(TeamDto dto);

    /**
     * Returns a list of all teams.
     *
     * @return List of all teams
     */
    List<TeamDto> getAllTeams();

    /**
     * Returns a team with the specified id.
     *
     * @param id Id of the team
     * @return Team with the given id
     */
    TeamDto getTeam(long id);
}