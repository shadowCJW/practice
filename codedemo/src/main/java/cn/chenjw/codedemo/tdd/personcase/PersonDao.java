package  cn.chenjw.codedemo.tdd.personcase;

public interface PersonDao {
	public Person fetchPerson(int id);
	public void update(Person p);
}
