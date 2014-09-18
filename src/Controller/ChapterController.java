package Controller;

import javax.annotation.Resource;
import Model.*;
import Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
	
	@Resource(name="chapterService")
	private ChapterService chapterService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String getAdd(@RequestParam("id")Integer bookId,Model model){
		
		Chapter chapter = new Chapter();
		
		model.addAttribute("bookId",bookId);
		model.addAttribute("chapterAttribute",chapter);
		
		return "addChapter";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String postAdd(@RequestParam("id")Integer bookId,@ModelAttribute("chapterAttribute")Chapter chapter){
		
		chapterService.add(bookId, chapter);
		return "redirect:/record/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer chapterId) {
		
		chapterService.delete(chapterId);
		return "redirect:/record/list";
	}
   
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("bid") Integer bookId,@RequestParam("cid") Integer chapterId, Model model) {
    	
    	Chapter chapter1 = chapterService.get(chapterId);

    	model.addAttribute("bookId",bookId);
    	model.addAttribute("chapterAttribute",chapter1);

    	return "editChapter";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer chapterId,
    		@ModelAttribute("chapterAttribute") Chapter chapter) {
		
		chapter.setChapterId(chapterId);
		chapterService.edit(chapter);

		return "redirect:/record/list";
	}
}
