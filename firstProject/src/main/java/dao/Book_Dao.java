package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.springframework.stereotype.Repository;

import model.Book;

@Repository
public interface Book_Dao {

	@Select(" select book.*,type.name typename from type inner join book on book.typeid=type.id ${where} ${limit} ")
	public List<Book> select(@Param("where") String where,@Param("limit") String limit);
	
//	@Select(" select book.*,type.name typename from book inner join type on book.typeid=type.id")
//	public List<Book> select(@paraString txt);
//	
	
	@Insert("insert into book (name,sex,typeid) values(#{name},#{sex},#{typeid})")
	public void insert(Book t);
	
	@Update("update book set name=#{name},sex=#{sex},typeid=#{typeid} where id=#{id}")
	public void update(Book t);
	
	@Delete("delete from book where id=#{id} ")
	public void delete(int id);
	
	@Select(" select count(1) from book inner join type on book.typeid=type.id ${where} ")
	public int selectcount(@Param("where") String where);


}
