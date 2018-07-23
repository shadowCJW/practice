package cn.chenjw.codedemo.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.chenjw.codedemo.bean.Dept;
/**
 * 递归查找某部门下面的所有子部门
 * @author Administrator
 *
 */
public class FindDept {
	public static List<Dept> deptList = new ArrayList<Dept>();
	public static Map<String,Dept> map = new HashMap<String, Dept>();
	/**
	 * 传单个参数查询其所有下级目录
	 * @param list 所有部门集合
	 * @param pid 查询的部门id
	 * @return
	 */
	public static List<Dept> treeMenuList(List<Dept> list,String pid){
		for(Dept mu: list){  
            //遍历出父id等于参数的id，add进子节点集合  
            if(mu.getPid()==pid){  
                //递归遍历下一级  
                treeMenuList(list,mu.getId());  
                deptList.add(mu);  
            }  
        }  
		
		return deptList;
	}
	/**
	 * 传多个参数获取其下级目录
	 * @param list所有部门集合
	 * @param pid查询的部门id
	 * @return
	 */
	public static  Map<String,Dept> treeMenuList2(List<Dept> list,String pid){
		for(Dept mu: list){  
            //遍历出父id等于参数的id，add进子节点集合  
            if(mu.getPid()==pid){  
                //递归遍历下一级  
                treeMenuList2(list,mu.getId());  
                map.put(mu.getId(), mu);
            }  
        }  
		for(Dept mu2: list){
			if(mu2.getId()==pid){
				map.put(pid, mu2);
			}
		}
		return map;
	}
	public static void main(String[] args) {
		List<Dept> deptList = new ArrayList<Dept>();
		deptList.add(new Dept("0", "-1", "总部"));
		deptList.add(new Dept("1", "0", "分公司1"));
		deptList.add(new Dept("2", "0", "分公司2"));
		deptList.add(new Dept("3", "1", "开发部A"));
		deptList.add(new Dept("4", "2", "开发部1"));
		deptList.add(new Dept("5", "2", "开发部1"));
		deptList.add(new Dept("6", "5", "开发组a"));
		
		
		List<Dept> ll = treeMenuList(deptList, "1");
		for(Dept d : ll){
			System.out.println(d.toString());
		}
		//gettemp(deptList,"2");//{4=Dept [id=4, pid=2, name=开发部1], 5=Dept [id=5, pid=2, name=开发部1]}
		gettemp(deptList,"2");
	}
	
	public static void gettemp(List<Dept> sd ,String ...s){
		for(String str : s){
			System.out.println(str);
			 Map<String,Dept>  m = treeMenuList2(sd,str);
			System.out.println(m);
		}
	}
	
}
