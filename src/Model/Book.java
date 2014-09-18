package Model;
import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book implements Serializable{
	
	@Id
	@Column(name="bookId")
	@GeneratedValue
	private Integer bookId;
	
	@Column(name="bookName")
	private String bookName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="BookChapter",
			joinColumns = @JoinColumn(name="BOOK_ID"),
			inverseJoinColumns = @JoinColumn(name="CHAPTER_ID")
	)
	private Set<Chapter> chapter;
	
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Set<Chapter> getChapter() {
		return chapter;
	}

	public void setChapter(Set<Chapter> chapter) {
		this.chapter = chapter;
	}
}
