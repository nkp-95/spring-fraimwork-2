package com.example.news;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController						//or @Conponent 쓸수 있음 => @Con
@RequestMapping("/api/news")		//IOC
public class NewsAPIController {
	
	final NewsDAO dao;
	
	@Autowired
	public NewsAPIController(NewsDAO dao) {
		this.dao = dao;
	}
	
	//프로퍼티 파일로부터 저장 경로 참조
	@Value("${news.img.dir}")
	String fdir;
	
	//뉴스 등록 API 서비스
	@PostMapping
	public String addNews(@RequestBody News news) {
		
		try {
			dao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			return "News API: 뉴스 등록 실패!!";
		}
		
		return "News API: 뉴스 등록됨!!";
	}
	
	//뉴스 삭제 API 서비스		//Delete - /api/news - 4 (위에)
	@DeleteMapping("{aid}")	
	public String delNews(@PathVariable("aid") int aid) {
		
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			return "News API: 뉴스 삭제 실패!! =" + aid;
		}
		
		return "News API: 뉴스 삭제됨!! = " + aid;
	}
	
	//목록 조회 API 서비스	http://localhost:8090/api/news
	@GetMapping
	public List<News> getNewsList(){
		List<News> newsList = null;
		
		try {
			newsList = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}
	
//	//파일 등록 API 서비스
//	@PostMapping
//	public String addNews(@ModelAttribute News news) {
//		try {
//			//1. 저장 파일객체
//			String fileName = news.getImgFile().getOriginalFilename();//파일이름 가져옴
//			
//			String sysName = System.currentTimeMillis() +
//					fileName.substring(fileName.lastIndexOf("."));
//			File dest = new File(fdir + "/" + sysName);
//			
//			news.getImgFile().transferTo(dest);	//서버 경로에 파일 쓰기
//			
//			news.setImg("/img/" + dest.getName());
//			
//			dao.addNews(news);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "News API: 뉴스 등록 실패!!";
//		}
//		
//		return "News API: 뉴스 등록됨!!";
//	}
	
	//뉴스 상세정보 조회  겟방식 /api/news + / (aid)  => id 부분
	@GetMapping("{aid}")
	public News getNews(@PathVariable("aid")int aid) {
		News news = null;
		
		try {
			news = dao.getNews(aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
	
	
}
