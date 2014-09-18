package Service;

import java.util.List;

import java.util.Set;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("bookService")
@Transactional
public class BookService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Book> getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Book");
		return query.list();
	}
	
	public Book get(Integer bookId){
		Session session = sessionFactory.getCurrentSession();
		return (Book)session.get(Book.class,bookId);
	}
	
	public void add(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.save(book);
	}
	
	public void delete(Integer bookId){
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book)session.get(Book.class,bookId);
		session.delete(book);
	}

	public void edit(Book book){
		Session session = sessionFactory.getCurrentSession();
		Book book1 = (Book)session.get(Book.class,book.getBookId());
		
		book1.setBookName(book.getBookName());
		session.save(book1);
	}

}
