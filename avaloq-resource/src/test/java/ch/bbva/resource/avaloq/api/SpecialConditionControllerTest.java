package ch.ggf.resource.avaloq.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.Container;
import ch.ggf.common.domain.Container.ContainerType;
import ch.ggf.common.domain.specialcondition.Group;
import ch.ggf.resource.avaloq.service.AvaloqService;

@WebMvcTest(controllers = { SpecialConditionController.class })
public class SpecialConditionControllerTest extends AbstractDocumentationTest {

  @MockBean
  private AvaloqService service;

  @Override
  @Before
  public void setUp() {
    super.setUp();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @WithMockUser
  public void getGroup() throws Exception {
    Mockito.when(service.getGroupWithSpecialConditions("GR_EUR_11")).thenReturn(group("GR_EUR_11", "GR_EUR_11"));

    getMockMvc().perform(get("/avaloq/sc/group/{code}", "GR_EUR_11").accept(MediaType.APPLICATION_JSON))

        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("GR_EUR_11"))
        .andExpect(jsonPath("$.name").value("GR_EUR_11"))

        .andDo(document("get-group"));
    //
    // .andDo(document("get-group-with-special-conditions",
    // RequestDocumentation.pathParameters(RequestDocumentation.parameterWithName("code").description("The
    // group code to get")), PayloadDocumentation.requestFields(descriptors)))
  }

  @Test
  @WithMockUser
  public void getBP() throws Exception {
    Mockito.when(service.getBPWithSpecialConditions("20753107")).thenReturn(bp());

    getMockMvc().perform(get("/avaloq/sc/bp/{nr}", "20753107").accept(MediaType.APPLICATION_JSON))

        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bpNr").value("20753107"))
        .andExpect(jsonPath("$.currency").value("CHF"))
        .andExpect(jsonPath("$.alias").value("myBP"))

        .andDo(document("get-bp"));
  }

  @Test
  @WithMockUser
  public void getContainer() throws Exception {
    Mockito.when(service.getContainerWithSpecialConditions("20753107.1001")).thenReturn(container());

    getMockMvc().perform(get("/avaloq/sc/container/{nr}", "20753107.1001").accept(MediaType.APPLICATION_JSON))

        .andExpect(status().isOk())
        .andExpect(jsonPath("$.containerNr").value("20753107.1001"))
        .andExpect(jsonPath("$.currency").value("CHF"))
        .andExpect(jsonPath("$.alias").value("myContainer"))
        .andExpect(jsonPath("$.type").value(ContainerType.CUSTODY))

        .andDo(document("get-container"));
  }

  // @RequestMapping("/sc/bp/{nr}/{classification}")
  // public SpecialCondition getBPCondition(@PathVariable("nr") String nr,
  // @PathVariable("classification") String classification) {
  // return avaloqService.getBPCondition(nr, classification);
  // }
  //
  // @RequestMapping("/sc/container/{nr}/{classification}")
  // public SpecialCondition getContainerCondition(@PathVariable("nr") String nr,
  // @PathVariable("classification") String classification) {
  // return avaloqService.getContainerCondition(nr, classification);
  // }

  private Group group(String code, String name) {
    Group group = new Group();
    group.setCode(code);
    group.setName(name);
    return group;
  }

  // TODO generar los objetos mock a partir de json, cuando los servicios
  // funcionen
  private BP bp() {
    BP bp = new BP();
    bp.setBpNr("20753107");
    bp.setCurrency("CHF");
    bp.setAlias("myBP");
    return bp;
  }

  private Container container() {
    Container container = new Container();
    container.setContainerNr("20753107.1001");
    container.setCurrency("CHF");
    container.setAlias("myContainer");
    container.setType(ContainerType.CUSTODY);
    return container;
  }

}
