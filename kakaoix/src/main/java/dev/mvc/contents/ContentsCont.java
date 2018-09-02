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
   * ���
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
   // ���� ���� �ڵ� ����
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/contents/storage");
   MultipartFile filesMF = contentsVO.getFilesMF();
   String files = ""; // �÷��� ������ ���ϸ�
   int sizes = (int) filesMF.getSize();
   String thumbs = ""; // �÷��� ������ ���ϸ�

   if (sizes > 0) {
     files = Upload.saveFileSpring(filesMF, upDir);

     if (Tool.isImage(files)) {
       thumbs = Tool.preview(upDir, files, 150, 200); // Thumb �̹��� ����
     }
   }
   contentsVO.setFiles(files);
   contentsVO.setSizes(sizes);
   contentsVO.setThumbs(thumbs);
   // -------------------------------------------------------------------
   // ���� ���� �ڵ� ����
   // -------------------------------------------------------------------
   
   int count = contentsProc.create(contentsVO);
   
   if (count == 1) {
     System.out.println("categoryno : " + contentsVO.getCategoryno());
     //categoryProc.increaseCnt(contentsVO.getCategoryno()); // �ۼ� ����

     mav.setViewName("redirect:/contents/list.do?categoryno="+contentsVO.getCategoryno());
     // mav.setViewName("redirect:/contents/list_all_category.do");
   } else {
     msgs.add("[��� ����]");
     msgs.add("�� ��Ͽ� �����߽��ϴ�.");
     msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� ���� ���: 000-0000-0000");
     links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");

     links.add("<button type='button' onclick=\"location.href='./list.do.'\">���</button>");

     mav.addObject("msgs", msgs);
     mav.addObject("links", links);
   }

   return mav;
 }
 
 /**
  * ���
  * �˻�+����¡ ����
  */
 @RequestMapping(value = "/contents/list.do", method = RequestMethod.GET)
 public ModelAndView list(
     @RequestParam(value="categoryno") int categoryno,
     @RequestParam(value="word", defaultValue="") String word,
     @RequestParam(value="nowPage", defaultValue="1") int nowPage
     ) { 
   
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/contents/list"); 

   // ���ڿ� ���ڿ� Ÿ���� �����ؾ��ϹǷ� Obejct ���
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("word", word);                  
   hashMap.put("nowPage", nowPage);       
   
   // �˻� ���
   List<ContentsVO> list = contentsProc.list_search(hashMap);
   mav.addObject("list", list);
  
   // �˻��� ���ڵ� ����
   int search_count = contentsProc.search_count(hashMap);
   mav.addObject("search_count", search_count);
   
   // ī�װ� ����
   CategoryVO categoryVO = categoryProc.read(categoryno);
   mav.addObject("categoryVO", categoryVO);
   
   
   
  /*  * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
    * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
    *
    * @param categoryno   ī�װ���ȣ 
    * @param search_count �˻�(��ü) ���ڵ�� 
    * @param nowPage      ���� ������
    * @param word �˻���
    * @return ����¡ ���� ���ڿ�*/
     
   String paging = contentsProc.paging(search_count, nowPage, word);
   
   mav.addObject("paging", paging);
   mav.addObject("nowPage", nowPage);

   return mav;
 }
 
 /**
  * �ڼ������� 
  */
 @RequestMapping(value = "/contents/read.do", method = RequestMethod.GET)
 public ModelAndView read(int contentsno, int categoryno) {
   
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/contents/read"); 
   
   //��ǰ���� ��ȸ
   ContentsVO contentsVO = contentsProc.read(contentsno);
   mav.addObject("contentsVO", contentsVO);
   
   //ī�װ� ��ȸ
   CategoryVO categoryVO = categoryProc.read(categoryno);
   mav.addObject("categoryVO", categoryVO);
  
   //��ȸ�� ����
   contentsProc.increaseCnt(contentsno);
   
   return mav;
 }

}
