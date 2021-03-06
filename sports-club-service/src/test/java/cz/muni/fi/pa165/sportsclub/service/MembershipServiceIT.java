package cz.muni.fi.pa165.sportsclub.service;

import static org.testng.Assert.*;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsclub.EntityFactoryService;
import cz.muni.fi.pa165.sportsclub.SpringContextConfiguration;
import cz.muni.fi.pa165.sportsclub.dao.MembershipDao;
import cz.muni.fi.pa165.sportsclub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsclub.dao.TeamDao;
import cz.muni.fi.pa165.sportsclub.entity.Membership;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Jakub Smolar
 */
@ContextConfiguration(classes = SpringContextConfiguration.class)
public class MembershipServiceIT extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private MembershipDao membershipDao;

    @Inject
    private TeamDao teamDao;

    @Inject
    private PlayerDao playerDao;

    @Inject
    private MembershipService membershipService;

    private EntityFactoryService entityFactoryService = new EntityFactoryService();

    private Membership membership;

    @BeforeMethod
    public void setUp(){
        membership = entityFactoryService.createPersistedMembership(membershipDao, teamDao, playerDao);
    }

    @Test
    public void createMembershipIT(){
        assertNotNull(membershipDao.findById(membership.getId()));
    }

    @Test
    public void updateMembershipIT(){
        membership.setJerseyNumber(99);
        Membership result = membershipService.updateMembership(membership);
        assertNotNull(result);
        assertTrue(99 == membershipDao.findById(membership.getId()).getJerseyNumber());
    }

    @Test
    public void removeTeamIT() {
        membershipService.removeMembership(membership);
        assertNull(membershipDao.findById(membership.getId()));
    }

    @Test
    public void findByIdIT() {
        assertNotNull(membershipService.findById(membership.getId()));
    }

    @Test
    void getAllIT() {
        Membership membership1 = entityFactoryService.
            createPersistedMembership("membership1", membershipDao, teamDao, playerDao);

        List<Membership> result = membershipDao.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(membership));
        assertTrue(result.contains(membership1));
    }
}
