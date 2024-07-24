package com.example.news;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/news")		//http://localhost:8090/news
public class NewsWebController {
	final NewsDAO dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${news.img.dir}")
	String fdir;	//c:temp/img가 들어옴
	
	@Autowired
	public NewsWebController(NewsDAO dao) {
		this.dao = dao;
	}
	
	///news/add
	@PostMapping("/add")
	public String addNews(@ModelAttribute News news, Model m, @RequestParam("file")MultipartFile file){//set, parpulate 역할
																	//ㄴ>경로로 파일정보 찾아옴(file 메서드가 없기때문)
		try {
			//1.저장 파일 객체 생성											
			String fileName = file.getOriginalFilename();//파일이름 가져옴
			
			String sysName = System.currentTimeMillis() +
					fileName.substring(fileName.lastIndexOf("."));
			File dest = new File(fdir + "/" + sysName);
			
			file.transferTo(dest);	//서버 경로에 파일 쓰기
			
			news.setImg("/img/" + dest.getName());
			dao.addNews(news);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("뉴스 추가(등록) 과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다!!");
		}	
		
		return "redirect:/news/list";
	}
	
	
	//	/news/delete/${news.aid}
	@GetMapping("/delete/{aid}")
	public String deleteNews(@PathVariable int aid, Model m) {
		
		try {
			dao.delNews(aid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("뉴스 삭제과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다!!");
		}
		
		return "redirect:/news/list";
	}
	
	//목록 조회
	@GetMapping("/list")
	public String listNews(Model m) {
		List<News> list;
		
		try {
			list = dao.getAll();
			m.addAttribute("newslist", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("뉴스목록 생성과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "news/newsList";
	}
	
	//상세정보 조회
	@GetMapping("/{aid}")
	public String getNews(@PathVariable int aid, Model m) {
		News n;
		
		try {
			n = dao.getNews(aid);
			m.addAttribute("news", n);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("뉴스를 가져오는 과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다!!");
		}
		return "news/newsView";
	}
	
}
