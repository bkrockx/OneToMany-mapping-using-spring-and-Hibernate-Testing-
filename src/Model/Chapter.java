package Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="chapter")
public class Chapter implements Serializable{
	
	@Id
	@Column(name="chapterId")
	@GeneratedValue
	private Integer chapterId;
	
	@Column(name="chapterName")
	private String chapterName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name="BookChapter",
			inverseJoinColumns = @JoinColumn(name="BOOK_ID"),
			joinColumns= @JoinColumn(name="CHAPTER_ID")
	)
	private Book book;
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	
}
