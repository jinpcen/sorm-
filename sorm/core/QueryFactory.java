package com.bjsxt.sorm.core;

/**
 * 创建Query对象的工厂类
 * @author gaoqi
 *
 */
public class QueryFactory {
	
	private static Query prototypeObj;  //原型对象
	static {
		
		
		
		try {
			Class c = Class.forName(DBManager.getConf().getQueryClass());  ////加载指定的query类
			prototypeObj = (Query) c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		//加载po包下面所有的类，便于重用，提高效率！
		TableContext.loadPOTables();
		
		
	}
	
	private QueryFactory(){  //私有构造器
	}
	
	
	public static Query createQuery(){
		try {
			return (Query) prototypeObj.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
