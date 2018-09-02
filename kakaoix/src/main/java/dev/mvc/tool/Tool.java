package dev.mvc.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Tool {
  
  /**
   * CSV 파일 생성
   * @param list
   * @param title
   * @param filepath
   * @return
   */
  public static synchronized int createCSV(ArrayList<Integer> list, String title, String filepath) {
    int resultCount = 0;
    try {
      BufferedWriter fw = new BufferedWriter(new FileWriter(filepath+"/"+title+".csv", true));
      for(Integer val : list) {
        if(resultCount == (list.size())) {
          fw.write(val);
        } else {
          fw.write(val+",");
        }
        resultCount++;
      }
      fw.newLine();
      fw.flush();
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultCount;
  }
  
  /**
   * 로그인된 회원 계정인지 검사합니다.
   * @param request
   * @return true: 관리자
   */
  public static synchronized boolean isMember(HttpSession session){
    boolean sw = false;
    
    String id = (String)session.getAttribute("mid");
    
    if (id != null){
      sw = true;
    }
    return sw;
  }
  
  
    /**
     * key를 추출하고 공백을 제거하는 메소드
     * @param map
     * @return
     */
    public static synchronized String getOrder(Map<String, String> map) {
      Set<String> orderset = map.keySet();
      ArrayList<String> orderlist = new ArrayList<String>(orderset);
      Collections.sort(orderlist);
      String order = String.join(",", orderlist);
  //    String order = map.keySet().toString();
  //    order = order.substring(1, order.length()-1);
  //    order = order.replaceAll("\\s+","");
      System.out.println(order);
      return order;
    }

    public static synchronized String getRealPath(HttpServletRequest request, String dir){
        String path="";
        try {
            path=request.getRealPath(dir)+"/";
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return path;
    }
    public static synchronized String comma(long no) {
        String str = "";
        DecimalFormat df = new DecimalFormat("#,###,###");
        str = df.format(no);

        return str;
    }

    /**
     * 201710 형식의 날짜를 리턴합니다.
     * 
     * @return 201710 형식의 문자열 리턴
     */
    public static synchronized String getDate() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");

        String date = sd.format(new Date());

        // System.out.println(date);
        return date;
    }

    /**
     * 관리자 계정인지 검사합니다.
     * 
     * @param request
     * @return true: 관리자
     */
    public static synchronized boolean isAdmin(HttpServletRequest request) {
        boolean sw = false;

        HttpSession session = request.getSession(); // java class에서 session 객체
                                                    // 추출
        String act = (String) session.getAttribute("alevel"); // 권한 산출

        if (act != null) {
            if (act.equals("M") || act.equals("Y")) { // 로그인 가능
                sw = true;
            }
        }
        return sw;
    }

    /**
     * 마스터 계정인지 검사합니다.
     * 
     * @param request
     * @return true: 마스터 계정
     */
    public static synchronized boolean isMaster(HttpServletRequest request) {
        boolean sw = false;

        HttpSession session = request.getSession();
        String act = (String) session.getAttribute("act"); // 역활
        // System.out.println("--> Tool.java act: " + act);

        if (act != null) {
            if (act.equals("M")) { // 마스터인지 검사
                sw = true;
            }
        }
        return sw;
    }

    /**
     * response.sendRedirect("")에서의 한글 전달시 변환
     * 
     * @param str
     *            변환할 문자열
     * @return 변환된 문자열
     */
    public static synchronized String toKorea(String str) {
        try {
            if (str == null) {
                str = "";
            } else {
                str = URLEncoder.encode(str, "utf-8");
                // System.out.println("Tool.java: " + str);
            }
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * 한글 변환 코드를 찾는 기능을 지원
     * 
     * @param s
     */
    public static synchronized void charsetTest(String s) {
        try {
            String[] charset = { "EUC-KR", "KSC5601", "ISO-8859-1", "8859_1",
                    "ASCII", "UTF-8" };
            for (int i = 0; i < charset.length; i++) {
                for (int j = 0; j < charset.length; j++) {
                    if (i == j)
                        continue;
                    System.out.print(charset[i] + " -> " + charset[j] + " : ");
                    System.out.println(new String(s.getBytes(charset[i]),
                            charset[j]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajax 한글 변환
     * 
     * @param ko
     * @return
     */
    public static synchronized String koAjax(String ko) {
        String corean = null;
        try {
            // corean= new String(ko.getBytes("ISO-8859-1"), "UTF-8");
            corean = new String(ko.getBytes("KSC5601"), "EUC-KR");
        } catch (Exception e) {
            return corean;
        }
        return corean;
    }

    /**
     * 자바스크립트 특수문자, 줄바꿈 문자 변환
     * 
     * @param str
     * @return
     */
    public static synchronized String toJS(String str) {
        if (str != null) {
            return str.replace("\\", "\\\\").replace("\'", "\\\'")
                    .replace("\"", "\\\"").replace("\r\n", "\\n")
                    .replace("\n", "\\n");

        } else {
            return "";
        }
    }

    /**
     * Android 한글 변환
     * 
     * @param ko
     * @return
     */
    public static synchronized String koAndroid(String ko) {
        String corean = null;
        try {
            // corean= new String(ko.getBytes("UTF-8"), "EUC-KR");
            // corean= new String(ko.getBytes("ISO-8859-1"), "UTF-8");
            // corean= new String(ko.getBytes("KSC5601"), "EUC-KR");
            corean = new String(ko.getBytes("8859_1"), "UTF-8");
        } catch (Exception e) {
            return corean;
        }
        return corean;
    }

    /**
     * 문자열의 길이가 length 보다 크면 "..." 을 표시하는 메소드
     * 
     * @param str
     *            문자열
     * @param length
     *            선별할 문자열 길이
     * @return
     */
    public static synchronized String textLength(String str, int length) {

        if (str != null) {
            if (str.length() > length) {
                str = str.substring(0, length) + "..."; // 범위: 0 ~ length - 1 산출
            }
        } else {
            str = "";
        }

        return str;
    }

    /**
     * null 특수 문자나 "null" 문자열을 공백 ""으로 변경합니다.
     * 
     * @param str
     *            원본 문자열
     * @return 변경된 문자열
     */
    public static synchronized String checkNull(String str) {
        if (str == null) {
            return "";
        } else if (str.equals("null")) {
            return "";
        } else {
            return str;
        }
    }

    public static synchronized String convertChar(String str) {
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("'", "&#39;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("\r\n", "<BR>");

        return str;
    }

    /**
     * 이미지 사이즈를 변경하여 새로운 Preview 이미지를 생성합니다.
     * 
     * <pre>
     *    사용예): Too.preview(folder 명, 원본 파일명, 200, 150)
     * </pre>
     * 
     * @param upDir
     *            원본 이미지 폴더
     * @param _src
     *            원본 파일명
     * @param width
     *            생성될 이미지 너비
     * @param height
     *            생성될 이미지 높이, ImageUtil.RATIO는 자동 비례 비율
     * @return src.jpg 파일을 이용하여 src_t.jpg 파일을 생성하여 파일명 리턴
     */
    public static synchronized String preview(String upDir, String _src,
            int width, int height) {
        int RATIO = 0;
        int SAME = -1;

        File src = new File(upDir + "/" + _src); // 원본 파일 객체 생성
        String srcname = src.getName(); // 원본 파일명 추출

        // 순수 파일명 추출, mt.jpg -> mt 만 추출
        String _dest = srcname.substring(0, srcname.indexOf("."));

        // 축소 이미지 조합 /upDir/mt_t.jpg
        File dest = new File(upDir + "/" + _dest + "_t.jpg");

        Image srcImg = null;
        // 파일의 확장자 추출
        String name = src.getName().toLowerCase(); // 파일명 추출
        // 이미지 파일인지 검사
 
        if (name.endsWith("jpg") || name.endsWith("bmp")
                || name.endsWith("png") || name.endsWith("gif") || name.endsWith("jpeg")) {
            try {
                srcImg = ImageIO.read(src); // 메모리에 원본 이미지 생성
                int srcWidth = srcImg.getWidth(null); // 원본 이미지 너비 추출
                int srcHeight = srcImg.getHeight(null); // 원본 이미지 높이 추출
                int destWidth = -1, destHeight = -1; // 대상 이미지 크기 초기화

                if (width == SAME) { // width가 같은 경우
                    destWidth = srcWidth;
                } else if (width > 0) {
                    destWidth = width; // 새로운 width를 할당
                }

                if (height == SAME) { // 높이가 같은 경우
                    destHeight = srcHeight;
                } else if (height > 0) {
                    destHeight = height; // 새로운 높이로 할당
                }

                // 비율에 따른 크기 계산
                if (width == RATIO && height == RATIO) {
                    destWidth = srcWidth;
                    destHeight = srcHeight;
                } else if (width == RATIO) {
                    double ratio = ((double) destHeight) / ((double) srcHeight);
                    destWidth = (int) ((double) srcWidth * ratio);
                } else if (height == RATIO) {
                    double ratio = ((double) destWidth) / ((double) srcWidth);
                    destHeight = (int) ((double) srcHeight * ratio);
                }

                // 메모리에 대상 이미지 생성
                Image imgTarget = srcImg.getScaledInstance(destWidth,
                        destHeight, Image.SCALE_SMOOTH);
                int pixels[] = new int[destWidth * destHeight];
                PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
                        destHeight, pixels, 0, destWidth);

                pg.grabPixels();

                BufferedImage destImg = new BufferedImage(destWidth,
                        destHeight, BufferedImage.TYPE_INT_RGB);
                destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0,
                        destWidth);

                // 파일에 기록
                ImageIO.write(destImg, "jpg", dest);

                System.out.println(dest.getName() + " 이미지를 생성했습니다.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dest.getName();
    }

    /**
     * 이미지인지 검사
     * 
     * @param file
     * @return
     */
    public static synchronized boolean isImage(String file) {
        boolean sw = false;

        if (file != null) {
            file = file.toLowerCase(); // 소문자
            if (file.endsWith(".jpg") || file.endsWith(".jpeg")
                    || file.endsWith(".png") || file.endsWith(".gif")) {
                sw = true;
            }
        }
        return sw;
    }

    /**
     * 수를 전달받아 자료의 단위를 적용합니다.
     * 
     * @param size
     * @return 1000 > 1000 Byte
     */
    public static synchronized String unit(long size) {
        String str = "";

        if (size < 1024) { // 1 KB 이하
            str = size + " Byte";
        } else if (size < 1024 * 1024) { // 1 MB 이하
            str = (int) (Math.ceil(size / 1024.0)) + " KB"; // 1048576 보다 작으면 KB
        } else if (size < 1024 * 1024 * 1024) { // 1 GB 이하
            str = (int) (Math.ceil(size / 1024.0 / 1024.0)) + " MB";
        } else if (size < 1024L * 1024 * 1024 * 1024) { // 1 TB 이하
            str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0)) + " GB";
        } else if (size < 1024L * 1024 * 1024 * 1024 * 1024) { // 1 PT 이하
            str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0))
                    + " TB";
        } else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 EX 이하
            str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0
                    / 1024.0))
                    + " PT";
        }

        return str;
    }

    /**
     * 파일을 삭제합니다.
     */
    public static synchronized boolean deleteFile(String folder, String fileName) {
        boolean ret = false;

        try {
            if (fileName != null) { // 기존에 파일이 존재하는 경우 삭제
                File file = new File(folder + "/" + fileName);
                if (file.exists() && file.isFile()) { // 존재하는 파일인지 검사
                    ret = file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
