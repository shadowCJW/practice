package  cn.chenjw.codedemo.tdd.personcase;
import static org.mockito.Mockito.*;  
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class ServiceTest {
	@Mock
	private PersonDao pDao;
	private PersonServiceImpl serImpl;
	public ServiceTest() {
	}
	@BeforeClass
	public static void setUpClass(){
		
	}
	@AfterClass
	public static void tearDownClass(){
		
	}
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		serImpl = new PersonServiceImpl(pDao);
	}
	@After
	public void tearDown(){
		
	}
	
	@org.junit.Test
	public void updatePerson(){
		Person person = new Person(1, "abc");
		when(pDao.fetchPerson(1)).thenReturn(person);
		boolean updated = serImpl.update(1, "div");
		assertTrue( updated);
		verify(pDao).fetchPerson(1);
		
		ArgumentCaptor<Person> personCaptor =  ArgumentCaptor.forClass(Person.class);  
		verify(pDao).update(personCaptor.capture());
		
		Person updatePerson = personCaptor.getValue();
		assertEquals("div", updatePerson.getName());
		verifyNoMoreInteractions(pDao);  
	}
	
	@Test  
    public void shouldNotUpdateIfPersonNotFound() {  
        // 设置模拟对象的返回预期值  
        when(pDao.fetchPerson(1)).thenReturn(null);  
        // 执行测试  
        boolean updated = serImpl.update(1, "David");  
        // 验证更新是否失败  
        assertFalse(updated);  
        // 验证模拟对象的fetchPerson(1)方法是否被调用了一次  
        verify(pDao).fetchPerson(1);  
        // 验证模拟对象是否没有发生任何交互  
        verifyZeroInteractions(pDao);  
        // 检查模拟对象上是否还有未验证的交互  
        verifyNoMoreInteractions(pDao);  
    }      
  
    /** 
     * Test of update method, of class PersonService. 
     */  
    @Test  
    public void testUpdate() {  
        System.out.println("update");  
        Integer personId = null;  
        String name = "Phillip";  
        PersonServiceImpl instance = new PersonServiceImpl(new PersonDao() {  
  
            @Override  
            public Person fetchPerson(int personID) {  
                System.out.println("Not supported yet.");  
                return null;  
            }  
  
            @Override  
            public void update(Person person) {  
                System.out.println("Not supported yet.");  
            }  
        });  
        boolean expResult = false;  
        boolean result = instance.update(personId, name);  
        assertEquals(expResult, result);  
        // TODO review the generated test code and remove the default call to fail.  
        fail("The test case is a prototype.");  
    }  
}
