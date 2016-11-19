package cz.muni.fi.pa165.sportsclub.dto.team;

import cz.muni.fi.pa165.sportsclub.dto.ageGroup.AgeGroupDto;
import cz.muni.fi.pa165.sportsclub.dto.membership.MembershipDto;
import cz.muni.fi.pa165.sportsclub.dto.teamManager.TeamManagerDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

/**
 * Created by norbert on 19.11.16.
 */
public class TeamDto {

    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    private Set<MembershipDto> memberships;

    private TeamManagerDto teamManager;

    private AgeGroupDto ageGroup;

    public TeamDto() {

    }

    public TeamDto(TeamDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.memberships = Collections.unmodifiableSet(dto.getMemberships());
        this.teamManager = dto.getTeamManager();
        this.ageGroup = dto.getAgeGroup();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MembershipDto> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<MembershipDto> memberships) {
        this.memberships = memberships;
    }

    public TeamManagerDto getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(TeamManagerDto teamManager) {
        this.teamManager = teamManager;
    }

    public AgeGroupDto getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroupDto ageGroup) {
        this.ageGroup = ageGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || !(o instanceof TeamDto)) return false;

        TeamDto teamDto = (TeamDto) o;

        return getName().equals(teamDto.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memberships=" + memberships +
                ", teamManager=" + teamManager +
                ", ageGroup=" + ageGroup +
                '}';
    }
}
