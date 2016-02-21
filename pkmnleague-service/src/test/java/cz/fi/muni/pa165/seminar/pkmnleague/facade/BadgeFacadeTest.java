package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BadgeService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.GymService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.seminar.pkmnleague.service.facade.BadgeFacadeImpl;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


/**
 * @author dhanak @domhanak on 1/24/16.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BadgeFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    BadgeService badgeServiceMock;

    @Mock
    TrainerService trainerServiceMock;

    @Mock
    GymService gymServiceMock;

    @Mock
    private BeanMappingService beanMappingServiceMock;

    @InjectMocks
    BadgeFacade badgeFacade = new BadgeFacadeImpl();

    private Badge badge1;
    private Badge badge2;

    private Trainer trainer;
    private Trainer trainerLeader;

    private Gym senecGym;
    private Gym bielGym;


    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        trainer = new Trainer("Janko", "Hrasko", "Sedi", new Date(Calendar.DATE));
        trainerLeader = new Trainer("Janko", "Vodca", "Modry", new Date(Calendar.DATE));

        senecGym = new Gym("Senec", PokemonType.DRAGON, trainerLeader);
        bielGym = new Gym("Biel", PokemonType.WATER, trainer);

        badge1 = new Badge(trainer, senecGym);
        badge2 = new Badge(trainerLeader, bielGym);

    }

    @Test
    public void testFindAllBadgesFacade() throws Exception {
        BadgeDTO dto1 = new BadgeDTO();
        BadgeDTO dto2 = new BadgeDTO();

        doReturn(toList(new Badge[]{badge1, badge2})).when(badgeServiceMock).findAll();
        doReturn(toList(new BadgeDTO[]{dto1, dto2})).when(beanMappingServiceMock)
                .mapTo(Matchers.anyListOf(Badge.class), (Class<?>) Matchers.any(Class.class));

        Collection<BadgeDTO> badgeDTOs = badgeFacade.getAllBadges();
        Assert.assertEquals(badgeDTOs.size(), 2);

        verify(badgeServiceMock).findAll();
        verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void testGetBadgeByIdFacade() throws Exception {
        BadgeDTO dto = new BadgeDTO();
        dto.setId(1);

        doReturn(badge1).when(badgeServiceMock).findById(1);
        doReturn(dto).when(beanMappingServiceMock).mapTo(Matchers.any(Badge.class),
                (Class<?>) Matchers.any(Class.class));

        BadgeDTO badgeDTO = badgeFacade.getBadgeWithId(1);
        Assert.assertEquals(1, badgeDTO.getId());

        verify(badgeServiceMock).findById(1);
        verify(beanMappingServiceMock).mapTo(badge1, BadgeDTO.class);
        verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void testCreateBadgeFacade() throws Exception {
        BadgeCreateDTO dto = new BadgeCreateDTO();
        dto.setTrainerId(1);
        dto.setGymId(1);

        doReturn(trainer).when(trainerServiceMock).findById(1);
        doReturn(senecGym).when(gymServiceMock).findById(1);
        doReturn(badge1).when(beanMappingServiceMock).mapTo(Matchers.any(BadgeDTO.class),
                (Class<?>) Matchers.any(Class.class));

        badgeFacade.createBadge(dto);

        verify(badgeServiceMock).createBadge(badge1);
        verifyNoMoreInteractions(badgeServiceMock);
    }

}
