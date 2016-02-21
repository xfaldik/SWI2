package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BadgeService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.GymService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.seminar.pkmnleague.service.facade.GymFacadeImpl;
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
public class GymFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    BadgeService badgeServiceMock;

    @Mock
    TrainerService trainerServiceMock;

    @Mock
    GymService gymServiceMock;

    @Mock
    private BeanMappingService beanMappingServiceMock;

    @InjectMocks
    GymFacade gymFacade = new GymFacadeImpl();

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
    public void testFindAllGymsFacade() throws Exception {
        GymDTO dto1 = new GymDTO();
        GymDTO dto2 = new GymDTO();

        doReturn(toList(new Gym[]{senecGym, bielGym})).when(gymServiceMock).findAll();
        doReturn(toList(new GymDTO[]{dto1, dto2})).when(beanMappingServiceMock)
                .mapTo(Matchers.anyListOf(Gym.class), (Class<?>) Matchers.any(Class.class));

        Collection<GymDTO> gymDTOs = gymFacade.getAllGyms();
        Assert.assertEquals(gymDTOs.size(), 2);

        verify(gymServiceMock).findAll();
        verifyNoMoreInteractions(gymServiceMock);
    }

    @Test
    public void testGetGymByIdFacade() throws Exception {
        GymDTO dto = new GymDTO();
        dto.setId(1);

        doReturn(senecGym).when(gymServiceMock).findById(1);
        doReturn(dto).when(beanMappingServiceMock).mapTo(Matchers.any(Gym.class),
                (Class<?>) Matchers.any(Class.class));

        GymDTO gymDTO = gymFacade.getGymWithId(1);
        Assert.assertEquals(gymDTO.getId(), 1);

        verify(gymServiceMock).findById(1);
        verify(beanMappingServiceMock).mapTo(senecGym, GymDTO.class);
        verifyNoMoreInteractions(gymServiceMock);
    }

}
