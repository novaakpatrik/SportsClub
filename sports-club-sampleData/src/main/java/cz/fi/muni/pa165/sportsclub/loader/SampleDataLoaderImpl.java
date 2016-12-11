package cz.fi.muni.pa165.sportsclub.loader;

import cz.muni.fi.pa165.sportsclub.entity.Membership;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.entity.TeamManager;
import cz.muni.fi.pa165.sportsclub.enumeration.AgeGroup;
import cz.muni.fi.pa165.sportsclub.service.MembershipService;
import cz.muni.fi.pa165.sportsclub.service.PlayerService;
import cz.muni.fi.pa165.sportsclub.service.TeamManagerService;
import cz.muni.fi.pa165.sportsclub.service.TeamService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Fabian Norbert
 */

@Component
@Transactional
public class SampleDataLoaderImpl implements SampleDataLoader {

    @Inject
    private TeamService teamService;

    @Inject
    private PlayerService playerService;

    @Inject
    private TeamManagerService teamManagerService;

    @Inject
    private MembershipService membershipService;

    @Override
    public void loadData() throws IOException, ParseException {
        //TEAMS
        Team team1 = new Team().setName("Team1").setAgeGroup(AgeGroup.JUNIOR);
        Team team2 = new Team().setName("Team2").setAgeGroup(AgeGroup.SENIOR);

        //TEAM MANAGERS
        TeamManager teamManager1 = new TeamManager().setName("Chuck Norris").setAddress("World")
                .setContact("chuck.norris@norris.com");
        TeamManager teamManager2 = new TeamManager().setName("Norbert Fabian").setAddress("Brno, Botanicka 15")
                .setContact("MyPersonalEmail@gmail.com");

        //PLAYERS
        Player player1 = new Player().setFirstName("Norbert").setLastName("Fabian").setHeight(190).setWeight(110)
                .setDateOfBirth(new Date());
        Player player2 = new Player().setFirstName("David").setLastName("Foser").setHeight(183).setWeight(98)
                .setDateOfBirth(new Date());

        //MEMBERSHIP
        Membership membership1 = new Membership().setTeam(team1).setPlayer(player1).setJerseyNumber(1);
        Membership membership2 = new Membership().setTeam(team1).setPlayer(player2).setJerseyNumber(7);

        //PERSISTING
        teamManagerService.createTeamManager(teamManager1);
        teamManagerService.createTeamManager(teamManager2);
        teamService.createTeam(team1);
        teamService.createTeam(team2);
        playerService.createPlayer(player1);
        playerService.createPlayer(player2);
        membershipService.createMembership(membership1);
        membershipService.createMembership(membership2);

        //ASSIGNING
        team1.setTeamManager(teamManager1);
        team2.setTeamManager(teamManager2);
        team1.addMembership(membership1);
        team1.addMembership(membership2);
        teamManager1.addTeam(team1);
        teamManager2.addTeam(team2);
        player1.addMembership(membership1);
        player2.addMembership(membership2);
    }

    /**
     * Creates Date instance from the given string
     *
     * @param date
     * @return
     * @throws ParseException
     */
    private Date createDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }

}
