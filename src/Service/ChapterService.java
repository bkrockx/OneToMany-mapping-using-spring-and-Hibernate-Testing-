package Service;

import java.util.List;

import java.util.*;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("chapterService")
@Transactional
public class ChapterService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Chapter> getAll(Integer bookId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Book as b WHERE b.id="+bookId);
		Book book = (Book)query.uniqueResult();
		return new ArrayList<Chapter>(book.getChapter());
	}

	public List<Chapter>getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Chapter");
		return query.list();
	}
	
	public Chapter get(Integer chapterId){
		Session session = sessionFactory.getCurrentSession();
		Chapter chapter = (Chapter)session.get(Chapter.class,chapterId);
		return chapter;
	}
	
	public void add(Integer bookId,Chapter chapter){
		Session session = sessionFactory.getCurrentSession();
		session.save(chapter);
		
		Book book1 = (Book)session.get(Book.class,bookId);
		book1.getChapter().add(chapter);
		
		session.save(book1);
	}
	
	public void delete(Integer chapterId){
		Session session = sessionFactory.getCurrentSession();
		//Query query = session.createSQLQuery("DELETE FROM bookchapter"+"WHERE CHAPTER_ID="+chapterId);
		
		//query.executeUpdate();
		Chapter chapter = (Chapter)session.get(Chapter.class,chapterId);
		session.delete(chapter);
	}
	
	public void edit(Chapter chapter){
		Session session = sessionFactory.getCurrentSession();
		Chapter chapter1 = (Chapter)session.get(Chapter.class,chapter.getChapterId());
		
		chapter1.setChapterName(chapter.getChapterName());
		session.save(chapter1);
	}
}
