package kr.co.softsoldesk.Service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softsoldesk.Beans.ExhibitionBean;
import kr.co.softsoldesk.Beans.ExhibitionDetailBean;
import kr.co.softsoldesk.Beans.PageBean;
import kr.co.softsoldesk.dao.AdminExhibitionDao;


@Service
public class AdminExhibitionService {
	
	@Autowired
	private AdminExhibitionDao adminExhibitionDao;
	
	@Value("${path.mainupload}")
	private String path_mainupload;
	
	@Value("${path.detailupload}")
	private String path_detailupload;
	
	@Value("${path.mainbannerupload}")
	private String path_mainbannerupload;
	
	@Value("${path.subbannerupload}")
	private String path_subbannerupload;
	
	@Value("${admin.listcnt}")
	private int admin_listcnt;
	
	@Value("${admin.paginationcnt}")
	private int admin_paginationcnt;
	
	 // FTP 서버 정보 설정
    private String server = "timtory.synology.me";
    private int port = 3060;
    private String user = "soldeskdream";
    private String pass = "Rladnxo12@";
	
	
	private String saveMainPosterUploadFile(MultipartFile upload_file) {	// 메인 포스터 업로드 경로
		
		FTPClient ftpClient = new FTPClient();
        String file_name = System.currentTimeMillis() + "_" + FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "." + FilenameUtils.getExtension(upload_file.getOriginalFilename());
        ftpClient.setControlEncoding("UTF-8");
        
        String ftpUploadDirectory = "/docker/tomcat/webapps/ROOT/WEB-INF/resources/img/main_poster/";
        
        try {
        	ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            boolean done = ftpClient.storeFile(ftpUploadDirectory + file_name, upload_file.getInputStream());
            if (done) {
                System.out.println("The file is uploaded successfully.");
            } else {
                System.out.println("Could not upload the file.");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return file_name;
	}	
	
	
	private String saveDetailPosterUploadFile(MultipartFile upload_file) {	// 상세 포스터 업로드 경로
			
		FTPClient ftpClient = new FTPClient();
        String file_name = System.currentTimeMillis() + "_" + FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "." + FilenameUtils.getExtension(upload_file.getOriginalFilename());
        ftpClient.setControlEncoding("UTF-8");
        
        String ftpUploadDirectory = "/docker/tomcat/webapps/ROOT/WEB-INF/resources/img/detail_poster/";
        
        try {
        	ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            boolean done = ftpClient.storeFile(ftpUploadDirectory + file_name, upload_file.getInputStream());
            if (done) {
                System.out.println("The file is uploaded successfully.");
            } else {
                System.out.println("Could not upload the file.");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return file_name;
		}
	
	//=================================== 전시회 관리 ===========================
		
		// 전시회 관리 첫페이지 찍혀오는 값 가져오기
		public List<ExhibitionBean> getAdminexhibitionmange(int page) {
			
			int start = (page - 1) * admin_listcnt;
			RowBounds rowBounds = new RowBounds(start, admin_listcnt);
			
			return adminExhibitionDao.getAdminexhibitionmange(rowBounds);
		}
	
		
		// 전시회 관리 전시총개수, 전시예정, 진행중, 종료 전시 개수 반환
		public ExhibitionBean getExhibitionCount() {
			return adminExhibitionDao.getExhibitionCount();
		}
		
		// 전시회 관리 페이징 처리
		public PageBean getExhibitionCnt(int currentPage) {
			int exhibition_Cnt = adminExhibitionDao.getExhibitionCnt();
			PageBean pageBean = new PageBean(exhibition_Cnt, currentPage, admin_listcnt, admin_paginationcnt);

			return pageBean;
		}
	
		// 전시회 관리 작가 검색
		public List<ExhibitionBean> getauthorSearchExhibitionInfo(String search) {
			return adminExhibitionDao.getauthorSearchExhibitionInfo(search);
		}
		
		// 전시회 관리 작가 검색 총 전시 개수, 종료예정, 전시예정, 진행중 개수 반환
		public ExhibitionBean getauthorSearchExhibitionCount(String search) {
			return adminExhibitionDao.getauthorSearchExhibitionCount(search);
		}
		
		// 전시회 관리 작가 검색시 페이징 처리 위한 검색 총 개수 반환
		public PageBean getauthorSearchExhibitionCnt(String search, int currentPage) {
			
			int searchautor_Cnt = adminExhibitionDao.getauthorSearchExhibitionCnt(search);
			PageBean pageBean = new PageBean(searchautor_Cnt, currentPage, admin_listcnt, admin_paginationcnt);
			
			return pageBean;
		}
		
		// 전시회 관리 제목 검색
		public List<ExhibitionBean> gettitleSearchExhibitionInfo(String search, int page) {
			int start = (page - 1) * admin_listcnt;
			RowBounds rowBounds = new RowBounds(start, admin_listcnt);
			
			return adminExhibitionDao.gettitleSearchExhibitionInfo(search, rowBounds);
		}
		
		// 전시회 관리 제목 검색 총 전시 개수, 종료예정, 전시예정, 진행중 개수 반환
		public ExhibitionBean gettitleSearchExhibitionCount(String search) {
			return adminExhibitionDao.gettitleSearchExhibitionCount(search);
		}
		
		// 전시회 관리 제목 검색시 페이징 처리 위한 검색 총 개수 반환
		public PageBean gettitleSearchExhibitionCnt(String search, int currentPage) {
			
			int searchtitle_Cnt = adminExhibitionDao.gettitleSearchExhibitionCnt(search);
			PageBean pageBean = new PageBean(searchtitle_Cnt, currentPage, admin_listcnt, admin_paginationcnt);
			
			return pageBean;
		}		
		
		// 전시회 관리 전시회 수정 페이지 들어가기
		public ExhibitionDetailBean getAllDetailExhibitionBean(int exhibition_id) {
			
			ExhibitionDetailBean b1 = adminExhibitionDao.getAllDetailExhibitionBean(exhibition_id);
			
			// open 컬럼 open_time ~ close_time 으로 나눔
			if (b1.getOpen() != null && !b1.getOpen().isEmpty()) {
		        String[] times = b1.getOpen().split(" - ");
		        if (times.length == 2) {
		            String open_time = times[0]; // "09:00"
		            String close_time = times[1]; // "18:00"
		            
		            b1.setOpen_time(open_time);
		            b1.setClose_time(close_time);
		        }
		    }
			
			return b1;
		}
		
		// 전시회 수정 업데이트문 1 (file table)
		public void UpdateExhibitionInfo1(String name, int file_id) {
			adminExhibitionDao.UpdateExhibitionInfo1(name, file_id);
		}
		
		// 전시회 수정 업데이트문 2 (exhibition table)
		public void UpdateExhibitionInfo2(ExhibitionDetailBean exhibitiondetailBean) {
			
		MultipartFile main_poster_upload_file = exhibitiondetailBean.getMain_poster_file();
		MultipartFile detail_poster_upload_file  = exhibitiondetailBean.getDetail_poster_file();
				
				if(main_poster_upload_file.getSize()>0) {
					String main_poster_file_name = saveMainPosterUploadFile(main_poster_upload_file);
					
					System.out.println("메인포스터 이름 : " + main_poster_file_name);
					exhibitiondetailBean.setMain_poster_name(main_poster_file_name);
					
					// file_table 메인포스터 이름 저장
					adminExhibitionDao.UpdateExhibitionInfo1(main_poster_file_name, exhibitiondetailBean.getMain_poster_file_id());
				}
				
				if(detail_poster_upload_file.getSize()>0) {
					String detail_poster_file_name = saveDetailPosterUploadFile(detail_poster_upload_file);
					
					System.out.println("상세포스터 이름 : " + detail_poster_file_name);
					exhibitiondetailBean.setDetail_poster_name(detail_poster_file_name);
					
					// file_table 상세포스터 이름 저장
					adminExhibitionDao.UpdateExhibitionInfo1(detail_poster_file_name, exhibitiondetailBean.getDetail_poster_file_id());
				}
			
			adminExhibitionDao.UpdateExhibitionInfo2(exhibitiondetailBean);
		}
		
		// 전시회 추가 파일테이블 1 (file_table)
		public void addfiletableExhibition(ExhibitionDetailBean exhibitionDetailBean) {
			
			MultipartFile main_poster_upload_file = exhibitionDetailBean.getMain_poster_file();
			MultipartFile detail_poster_upload_file  = exhibitionDetailBean.getDetail_poster_file();
					
					if(main_poster_upload_file.getSize()>0) {
						String main_poster_file_name = saveMainPosterUploadFile(main_poster_upload_file);
						
						// exhibition테이블 name 컬럼 set
						exhibitionDetailBean.setMain_poster_name(main_poster_file_name);
						
						// file_table테이블 name, path 컬럼 set
						exhibitionDetailBean.setName(main_poster_file_name);
						exhibitionDetailBean.setPath("../img/main_poster/");
						
						// file_table 메인포스터 이름 저장
						adminExhibitionDao.addfiletableExhibition(exhibitionDetailBean);
						
						// 저장된 파일 file_id exhibitionDetailBean에 set 해줌
						exhibitionDetailBean.setMain_poster_file_id(adminExhibitionDao.getFileId(main_poster_file_name));
					}
					
					if(detail_poster_upload_file.getSize()>0) {
						String detail_poster_file_name = saveDetailPosterUploadFile(detail_poster_upload_file);
						
						// exhibition테이블 name 컬럼 set
						exhibitionDetailBean.setDetail_poster_name(detail_poster_file_name);
						
						// file_table 메인포스터 이름 저장
						exhibitionDetailBean.setName(detail_poster_file_name);
						exhibitionDetailBean.setPath("../img/detail_poster/");
						
						// file_table 상세포스터 이름 저장
						adminExhibitionDao.addfiletableExhibition(exhibitionDetailBean);
						
						// 저장된 파일 file_id exhibitionDetailBean에 set 해줌
						exhibitionDetailBean.setDetail_poster_file_id(adminExhibitionDao.getFileId(detail_poster_file_name));
					}
		}
		
		// 전시회 등록신청에서 전시회 등록 할 경우 파일 1개만 바꿀 경우를 생각해 파일 나눔 (메인포스터 파일)
		public void addmainfiletableExhibition(ExhibitionDetailBean exhibitionDetailBean) {
				
				MultipartFile main_poster_upload_file = exhibitionDetailBean.getMain_poster_file();
						
						if(main_poster_upload_file.getSize()>0) {
							String main_poster_file_name = saveMainPosterUploadFile(main_poster_upload_file);
							
							// exhibition테이블 name 컬럼 set
							exhibitionDetailBean.setMain_poster_name(main_poster_file_name);
							
							// file_table테이블 name, path 컬럼 set
							exhibitionDetailBean.setName(main_poster_file_name);
							exhibitionDetailBean.setPath("../img/main_poster/");
							
							// file_table 메인포스터 이름 저장
							adminExhibitionDao.addfiletableExhibition(exhibitionDetailBean);
							
							// 저장된 파일 file_id exhibitionDetailBean에 set 해줌
							exhibitionDetailBean.setMain_poster_file_id(adminExhibitionDao.getFileId(main_poster_file_name));
						}
			}
		
		// 전시회 등록신청에서 전시회 등록 할 경우 파일 1개만 바꿀 경우를 생각해 파일 나눔 (상세포스터 파일)
		public void adddetailfiletableExhibition(ExhibitionDetailBean exhibitionDetailBean) {
				
				MultipartFile detail_poster_upload_file  = exhibitionDetailBean.getDetail_poster_file();
						
						if(detail_poster_upload_file.getSize()>0) {
							String detail_poster_file_name = saveDetailPosterUploadFile(detail_poster_upload_file);
							
							// exhibition테이블 name 컬럼 set
							exhibitionDetailBean.setDetail_poster_name(detail_poster_file_name);
							
							// file_table 메인포스터 이름 저장
							exhibitionDetailBean.setName(detail_poster_file_name);
							exhibitionDetailBean.setPath("../img/detail_poster/");
							
							// file_table 상세포스터 이름 저장
							adminExhibitionDao.addfiletableExhibition(exhibitionDetailBean);
							
							// 저장된 파일 file_id exhibitionDetailBean에 set 해줌
							exhibitionDetailBean.setDetail_poster_file_id(adminExhibitionDao.getFileId(detail_poster_file_name));
						}
			}
		
		// 전시회 추가 전시회 테이블에 저장
		public void addexhibitiontableExhibition(ExhibitionDetailBean exhibitionDetailBean) {
			
			// 전시회 휴무일이 없을경우
			if(exhibitionDetailBean.getHoliday() == null) {
				exhibitionDetailBean.setHoliday("없음");
			}
			
			// open 컬럼 변경
			String open = (exhibitionDetailBean.getOpen_time()+ " - " + exhibitionDetailBean.getClose_time());
			exhibitionDetailBean.setOpen(open);
			
			adminExhibitionDao.addexhibitiontableExhibition(exhibitionDetailBean);
		}
		
		// =========================== 전시회 등록 신청 ========================

		// 전시회 등록신청 관리자 페이지 리스트
		public List<ExhibitionDetailBean> getEnrollExhibitionSearch(String title,String name,String author,int page) {
			int start = (page - 1) * admin_listcnt;
			RowBounds rowBounds = new RowBounds(start, admin_listcnt);

			return adminExhibitionDao.getEnrollExhibitionSearch(title,name,author,rowBounds);
		}
		
		public PageBean getEnrollExhibitionSearchCnt(String title,String name,String author,int currentPage) 
		{
			int searchautor_Cnt=  adminExhibitionDao.getEnrollExhibitionSearchCnt(title,name,author);

			PageBean pageBean = new PageBean(searchautor_Cnt, currentPage, admin_listcnt, admin_paginationcnt);
			
			return pageBean;
		}
		public ExhibitionDetailBean getEnrollExhibitionSearch_BadgeCnt(String title,String name,String author) {
			return adminExhibitionDao.getEnrollExhibitionSearch_BadgeCnt(title,name,author);
		}
		
		// 전시회 등록신청 한개 모든 정보 가져오기
		public ExhibitionDetailBean getOneEnrollExhitiion(int exhibition_enroll_id) {
			
			// open 시간 open_time, close_time으로 나눔
			ExhibitionDetailBean b1 = adminExhibitionDao.getOneEnrollExhitiion(exhibition_enroll_id);
			
			if (b1.getOpen() != null && !b1.getOpen().isEmpty()) {
		        String[] times = b1.getOpen().split(" - ");
		        if (times.length == 2) {
		            String open_time = times[0]; 
		            String close_time = times[1]; 
		            
		            b1.setOpen_time(open_time);
		            b1.setClose_time(close_time);
		        }
		    }
			
			return b1;
		}
		
		// 전시회 등록 신청 -> 전시회 등록 할때
		public void addEnrollexhibitiontableExhibition(ExhibitionDetailBean exhibitionDetailBean) {
			
			// 전시회 휴무일이 없을경우
			if(exhibitionDetailBean.getHoliday() == null) {
				exhibitionDetailBean.setHoliday("없음");
			}
					
			// open 컬럼 변경
			String open = (exhibitionDetailBean.getOpen_time()+ " - " + exhibitionDetailBean.getClose_time());
			exhibitionDetailBean.setOpen(open);
			
			adminExhibitionDao.addEnrollexhibitiontableExhibition(exhibitionDetailBean);
		}
		
		// 전시회 등록 신청 완료 후 상태값 변경
		public void UpdateExhibitionEnrollState(int enroll_state, int exhibition_enroll_id) {
			
			adminExhibitionDao.UpdateExhibitionEnrollState(enroll_state, exhibition_enroll_id);
		}


}
