package springbootH2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.Employee;
import com.example.demo.EmployeeRepo;
import com.example.demo.SpringbootH2Application;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringbootH2Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

	 private JacksonTester<Employee> Je;
	private MockMvc mockMvc;
	 @Autowired
	    private WebApplicationContext webApplicationContext;

	    @MockBean 
	    private EmployeeRepo employeeRepoMock;
	    @Before
	    public void setUp() {
	        this.mockMvc = webAppContextSetup(webApplicationContext).build();
	    }


	@Test     //Get all Employees
	public void verifyAllEmployee() throws Exception {
		Employee e1=new Employee(1L,"Aravind","CEO");
		Employee e2=new Employee(2L,"Siva","CFO");
		 when(employeeRepoMock.findAll()).thenReturn(Arrays.asList(e1, e2));
		mockMvc.perform(MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].emp_id").value(1L))
		.andExpect(jsonPath("$[0].emp_name").value("Aravind"))
		.andExpect(jsonPath("$[0].position").value("CEO"))
		.andExpect(jsonPath("$[1].emp_id").value(2L))
		.andExpect(jsonPath("$[1].emp_name").value("Siva"))
		.andExpect(jsonPath("$[1].position").value("CFO"));
	}

	@Test      //Get a particular Employee
	public void verifyEmployeeById() throws Exception{
		Employee e=new Employee(1L,"Aravind","CEO");
		Optional<Employee> e1= Optional.of(e);
		 when(employeeRepoMock.findById(1L)).thenReturn(e1);
			mockMvc.perform(MockMvcRequestBuilders.get("/employee/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.emp_id").value(1L))
			.andExpect(jsonPath("$.emp_name").value("Aravind"))
			.andExpect(jsonPath("$.position").value("CEO"));
	}
	
	@Test     
	public void verifyAddEmployeeById() throws Exception{
		
		Employee e=new Employee(1L,"Aravind","CEO");
		when(employeeRepoMock.save(Mockito.any(Employee.class))).thenReturn(e);
		mockMvc.perform(MockMvcRequestBuilders.post("/employee")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("{\"emp_id\" : \"1\", \"emp_name\" : \"Aravind\", \"position\" : \"CEO\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());	
	}
	
}
