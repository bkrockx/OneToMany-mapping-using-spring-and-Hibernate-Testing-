package Controller;

import java.util.ArrayList;

import java.util.List;
import javax.annotation.Resource;
import Model.*;
import Dto.*;
import Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/record")
public class MainController {
	
	@Resource(name="bookService")
	private BookService bookService;
	
	@Resource(name="chapterService")
	private ChapterService chapterService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) {
    	
    	List<Book> books = bookService.getAll();
    	
    	List<BookDTO> bookDTO = new ArrayList<BookDTO>();
    	
    	for (Book book: books) {
    		BookDTO dto = new BookDTO();
    		
			dto.setBookId(book.getBookId());
			dto.setBookName(book.getBookName());
		
			dto.setChapter(chapterService.getAll(book.getBookId()));
			
			bookDTO.add(dto);
    	}
    	
    	model.addAttribute("books", bookDTO);
		return "record";
	}
    
  
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
    	
    	model.addAttribute("bookAttribute", new Book());
    	
    	return "addBook";
	}
 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("bookAttribute") Book book) {
		
    	bookService.add(book);
		return "redirect:/record/list";
	}
    
 
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer bookId) {
    	
		bookService.delete(bookId);
		return "redirect:/record/list";
	}
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer bookId, Model model) {
    	
    	Book book1 = bookService.get(bookId);
    	model.addAttribute("bookAttribute",book1);
    	
    	return "editBook";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer bookId, 
    						    @ModelAttribute("bookAttribute") Book book) {
		
		book.setBookId(bookId);
		bookService.edit(book);
		return "redirect:/record/list";
	}

}
