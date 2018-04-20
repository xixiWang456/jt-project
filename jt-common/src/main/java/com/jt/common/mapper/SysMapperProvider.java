package com.jt.common.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Table;

import org.apache.ibatis.jdbc.SqlBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;

import com.github.abel533.mapper.MapperProvider;
import com.github.abel533.mapperhelper.EntityHelper;
import com.github.abel533.mapperhelper.MapperHelper;


public class SysMapperProvider extends MapperProvider {

    public SysMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public SqlNode updateByIDS(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
        EntityHelper.EntityColumn column = null;
        for (EntityHelper.EntityColumn entityColumn : entityColumns) {
            column = entityColumn;
            break;
        }
        System.out.println("bbbbbbbbbbbbbbbbbbb"+column.getProperty());
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        // 开始拼sql
        BEGIN();
//        Update 
        SqlBuilder.UPDATE(tableName(entityClass));
//        SqlBuilder.SET("status=2");
        // 得到sql
        String sql = SqlBuilder.SQL();
        System.out.println(sql);
        // 静态SQL部分
        sqlNodes.add(new StaticTextSqlNode(sql + " WHERE " + column.getColumn() + " IN "));
        // 构造foreach sql
        SqlNode foreach = new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode("#{"
                + column.getProperty() + "}"), "ids", "index", column.getProperty(), "(", ")", ",");
        sqlNodes.add(foreach);
        return new MixedSqlNode(sqlNodes);
    }
    
    public SqlNode deleteByIDS(MappedStatement ms) {
    	Class<?> entityClass = getSelectReturnType(ms);
    	Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
    	EntityHelper.EntityColumn column = null;
    	for (EntityHelper.EntityColumn entityColumn : entityColumns) {
    		column = entityColumn;
    		break;
    	}
    	System.out.println("aaaaaaaaaaaaaaaaaaaa");
    	List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
    	// 开始拼sql
    	BEGIN();
    	// delete from table
    	DELETE_FROM(tableName(entityClass));
    	// 得到sql
    	String sql = SQL();
    	System.out.println(sql);
    	// 静态SQL部分
    	sqlNodes.add(new StaticTextSqlNode(sql + " WHERE " + column.getColumn() + " IN "));
    	// 构造foreach sql
    	SqlNode foreach = new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode("#{"
    			+ column.getProperty() + "}"), "ids", "index", column.getProperty(), "(", ")", ",");
    	sqlNodes.add(foreach);
    	return new MixedSqlNode(sqlNodes);
    }
    
    
    
    
    
    
    
    
    
    
    

    /**
     * 实现通用mapper的查询表总数
     */
    
    public SqlNode testFindCount(MappedStatement ms){
    	try {
        	String methodPath = ms.getId();
        	
        	//2.获取ItemMapper的字符串
        	String targetPath = methodPath.substring(0, methodPath.lastIndexOf("."));
        	
        	//3.获取ItemMapper对象
        	Class<?> targetClass = Class.forName(targetPath);
        	
        	//4.获取ItemMapper的父级接口 由于接口是可以多继承的
        	Type[] types = targetClass.getGenericInterfaces();
        	
        	//5.获取SysMapper
        	Type targetType = types[0];
        	
        	//判断该类型是否为泛型 SysMapper<Item>
        	if(targetType instanceof ParameterizedType){
        		//表示当前接口是一个泛型,并且获取泛型参数
        		ParameterizedType parameterizedType = (ParameterizedType) targetType;
        		
        		//SysMapper<T,V,K>   获取泛型的全部参数
        		Type[] supers =  parameterizedType.getActualTypeArguments();
        		
        		//表示成功获取第一个参数
        		Class<?> targetMethodClass = (Class<?>) supers[0];
        		
        		//判断Class不能为空
        		if(targetMethodClass !=null){
        			
        			//判断该类中是否含有注解
        			if(targetMethodClass.isAnnotationPresent(Table.class)){
        				//获取目标对象的注解
        				Table table = targetMethodClass.getAnnotation(Table.class);
        				
        				//获取表名
        				String tableName = table.name();
        				      
        				//定义查询sql语句
        				String sql = "select count(*) from "+tableName;
        				
        				//定义sqlNode对象
        				SqlNode sqlNode = new StaticTextSqlNode(sql);
        				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        				
        				return sqlNode;
        			}	
        		}
        	}
        	
    		} catch (ClassNotFoundException e) {
    			
    			e.printStackTrace();
    		}
        	
        	return null;
        	
    }
}
