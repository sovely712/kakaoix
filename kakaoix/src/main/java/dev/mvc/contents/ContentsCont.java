package dev.mvc.contents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;


@Controller
public class ContentsCont {

  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc = null;
 
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc = null;
  
  public ContentsCont() {
    //System.out.println("--> ContentsCont created.");
  }
  
  /**
   * 등록
   */
 @RequestMapping(value = "/contents/create.do", method = RequestMethod.GET)
 public ModelAndView create() {
   System.out.println("--> create() GET executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/contents/create"); 

   return mav;
 }
 
 @RequestMapping(value = "/contents/create.do", method = RequestMethod.POST)
 public ModelAndView create(HttpServletRequest request, ContentsVO contentsVO) {
   System.out.println("--> create() POST executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/contents/message"); 

   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // 파일 전송 코드 시작
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/contents/storage");
   MultipartFile filesMF = contentsVO.getFilesMF();
   String files = ""; // 컬럼에 저장할 파일명
   int sizes = (int) filesMF.getSize();
   String thumbs = ""; // 컬럼에 저장할 파일명

   if (sizes > 0) {
     files = Upload.saveFileSpring(filesMF, upDir);

     if (Tool.isImage(files)) {
       thumbs = Tool.preview(upDir, files, 150, 200); // Thumb 이미지 생성
     }
   }
   contentsVO.setFiles(files);
   contentsVO.setSizes(sizes);
   contentsVO.setThumbs(thumbs);
   // -------------------------------------------------------------------
   // 파일 전송 코드 종료
   // -------------------------------------------------------------------
   
   int count = contentsProc.create(contentsVO);
   
   if (count == 1) {
     System.out.println("categoryno : " + contentsVO.getCategoryno());
     //categoryProc.increaseCnt(contentsVO.getCategoryno()); // 글수 증가

     mav.setViewName("redirect:/contents/list.do?categoryno="+contentsVO.getCategoryno());
     // mav.setViewName("redirect:/contents/list_all_category.do");
   } else {
     msgs.add("[등록 실패]");
     msgs.add("글 등록에 실패했습니다.");
     msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 전산 운영팀: 000-0000-0000");
     links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");

     links.add("<button type='button' onclick=\"location.href='./list.do.'\">목록</button>");

     mav.addObject("msgs", msgs);
     mav.addObject("links", links);
   }

   return mav;
 }
 
 /**
  * 목록
  * 검색+페이징 지원
  */
 @RequestMapping(value = "/contents/list.do", method = RequestMethod.GET)
 public ModelAndView list(
     @RequestParam(value="categoryno") int categoryno,
     @RequestParam(value="word", defaultValue="") String word,
     @RequestParam(value="nowPage", defaultValue="1") int nowPage
     ) { 
   
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/contents/list"); 

   // 숫자와 문자열 타입을 저장해야하므로 Obejct 사용
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("word", word);                  
   hashMap.put("nowPage", nowPage);       
   
   // 검색 목록
   List<ContentsVO> list = contentsProc.list_search(hashMap);
   mav.addObject("list", list);
  
   // 검색된 레코드 개수
   int search_count = contentsProc.search_count(hashMap);
   mav.addObject("search_count", search_count);
   
   // 카테고리 정보
   CategoryVO categoryVO = categoryProc.read(categoryno);
   mav.addObject("categoryVO", categoryVO);
   
   
   
  /*  * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
    * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
    *
    * @param categoryno   카테고리번호 
    * @param search_count 검색(전체) 레코드수 
    * @param nowPage      현재 페이지
    * @param word 검색어
    * @return 페이징 생성 문자열*/
     
   String paging = contentsProc.paging(search_count, nowPage, word);
   
   mav.addObject("paging", paging);
   mav.addObject("nowPage", nowPage);

   return mav;
 }
 
 /**
  * 자세히보기 
  */
 @RequestMapping(value = "/contents/read.do", method = RequestMethod.GET)
 public ModelAndView read(int contentsno, int categoryno) {
   
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/contents/read"); 
   
   //상품정보 조회
   ContentsVO contentsVO = contentsProc.read(contentsno);
   mav.addObject("contentsVO", contentsVO);
   
   //카테고리 조회
   CategoryVO categoryVO = categoryProc.read(categoryno);
   mav.addObject("categoryVO", categoryVO);
  
   //조회수 증가
   contentsProc.increaseCnt(contentsno);
   
   return mav;
 }

}
