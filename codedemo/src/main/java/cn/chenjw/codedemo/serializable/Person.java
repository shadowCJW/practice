package cn.chenjw.codedemo.serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author chenjw
 * 2017年9月24日
 * 实现序列化的第二种方式为实现接口Externalizable
 * 
 * java中的序列化时transient变量(这个关键字的作用就是告知JAVA我不可以被序列化)
 * 和静态变量不会被序列化(下面是一个测试的例子)
 * 
 *2.也是最应该注意的，如果你先序列化对象A后序列化B，那么在反序列化的时候一定记着JAVA规定先读到的对象是先被序列化的对象，
 *不要先接收对象B，那样会报错.尤其在使用上面的Externalizable的时候一定要注意读取的先后顺序。

3.实现序列化接口的对象并不强制声明唯一的serialVersionUID，
是否声明serialVersionUID对于对象序列化的向上向下的兼容性有很大的影响。 
 */
public class Person implements Externalizable{

	private static final long serialVersionUID = 1L;
	String username;
	String password;
	String age;
	
	
	public Person(String username, String password, String age) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
	}
	

	public Person() {
		super();
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	/**
	 * 序列化操作扩展类
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		//增加一个新的对象
		Date date = new Date();
		out.writeObject(username);
		out.writeObject(password);
		out.writeObject(date);
	}
	/**
	 * 反序列话的扩展类
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		 //注意这里的接受顺序是有限制的哦，否则的话会出错的
        // 例如上面先write的是A对象的话，那么下面先接受的也一定是A对象...
		username = (String) in.readObject();
		password = (String) in.readObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = (Date) in.readObject();
		System.out.println("反序列时间:"+sdf.format(date));
	}
	
	@Override
	public String toString() {
		//注意这里的年龄是不会被序列化的，所以在反序列化的时候是读取不到数据的
        return "用户名:"+username+"密 码:"+password+"年龄:"+age;
	}
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		 Operate operate=new Operate();
	       Person person=new Person("小浩","123456","20");
	       System.out.println("为序列化之前的相关数据如下:\n"+person.toString());
	       operate.serializable(person);
	       Person newPerson=operate.deSerializable();
	       System.out.println("-------------------------------------------------------");
	       System.out.println("序列化之后的相关数据如下:\n"+newPerson.toString());
	
	}

}
class Operate{
    /**
     * 序列化方法
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void serializable(Person person) throws FileNotFoundException, IOException{
        ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream("src/main/java/cjw/cn/com/serializable/a.txt"));
        outputStream.writeObject(person);      
    }

    /**
     * 反序列化的方法
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public Person deSerializable() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/main/java/cjw/cn/com/serializable/a.txt"));
        return (Person) ois.readObject();
    }

}