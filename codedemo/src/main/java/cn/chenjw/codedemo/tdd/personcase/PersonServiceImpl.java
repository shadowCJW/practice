package  cn.chenjw.codedemo.tdd.personcase;

public class PersonServiceImpl {
	private PersonDao pdao;

	public PersonServiceImpl(PersonDao pdao) {
		super();
		this.pdao = pdao;
	}
	public boolean update(int id,String name){
		Person per = pdao.fetchPerson(id);
		if(per!=null){
			Person updatePerson = new Person(per.getId(), name);
			pdao.update(updatePerson);return true;
		}else{
			
			return false;
		}
	}
}
