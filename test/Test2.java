package com.bjsxt.test;

import java.util.List;

import com.bjsxt.sorm.core.Query;
import com.bjsxt.sorm.core.QueryFactory;
import com.bjsxt.vo.EmpVO;

/**
 * 测试连接池的效率
 * @author gaoqi
 *
 */
public class Test2 {
	
	public static void test01(){
		Query q = QueryFactory.createQuery();
		String sql2 = "select e.id,e.empname,salary+bonus 'xinshui',age,d.dname 'deptName',d.address 'deptAddr' from emp e "
			+"join dept d on e.deptId=d.id ";
		List<EmpVO> list2 = q.queryRows(sql2,EmpVO.class, null);
		for(EmpVO e:list2){
			System.out.println(e.getEmpname()+"-"+e.getDeptAddr()+"-"+e.getXinshui());
		}
	}
	
	
	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		for(int i=0;i<3000;i++){
			test01();
		}
		long b = System.currentTimeMillis();
		System.out.println((b-a));   //不加连接池的耗时：15375. 增加连接池之后，耗时为：1752
	}
}
