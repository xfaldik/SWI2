package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BadgeService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.GymService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.seminar.pkmnleague.service.facade.TrainerFacadeImpl;
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
public class TrainerFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    BadgeService badgeServiceMock;

    @Mock
    TrainerService trainerServiceMock;

    @Mock
    GymService gymServiceMock;

    @Mock
    private BeanMappingService beanMappingServiceMock;

    @InjectMocks
    TrainerFacade trainerFacade = new TrainerFacadeImpl();

    private Trainer trainer;
    private Trainer trainerLeader;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        trainer = new Trainer("Janko", "Hrasko", "Sedi", new Date(Calendar.DATE));
        trainerLeader = new Trainer("Janko", "Vodca", "Modry", new Date(Calendar.DATE));
    }

    @Test
    public void testFindAllTrainersFacade() throws Exception {
        TrainerDTO dto1 = new TrainerDTO();
        TrainerDTO dto2 = new TrainerDTO();

        doReturn(toList(new Trainer[]{trainer, trainerLeader})).when(trainerServiceMock).findAll();
        doReturn(toList(new TrainerDTO[]{dto1, dto2})).when(beanMappingServiceMock)
                .mapTo(Matchers.anyListOf(Trainer.class), (Class<?>) Matchers.any(Class.class));

        Collection<TrainerDTO> trainerDTOs = trainerFacade.getAllTrainers();
        Assert.assertEquals(trainerDTOs.size(), 2);

        verify(trainerServiceMock).findAll();
        verifyNoMoreInteractions(trainerServiceMock);
    }

    @Test
    public void testGetTrainerByIdFacade() throws Exception {
        TrainerDTO dto = new TrainerDTO();
        dto.setId(1);

        doReturn(trainer).when(trainerServiceMock).findById(1);
        doReturn(dto).when(beanMappingServiceMock).mapTo(Matchers.any(Trainer.class),
                (Class<?>) Matchers.any(Class.class));

        TrainerDTO trainerDTO = trainerFacade.getTrainerWithId(1);
        Assert.assertEquals(trainerDTO.getId(), 1);

        verify(trainerServiceMock).findById(1);
        verify(beanMappingServiceMock).mapTo(trainer, TrainerDTO.class);
        verifyNoMoreInteractions(trainerServiceMock);
    }

}
