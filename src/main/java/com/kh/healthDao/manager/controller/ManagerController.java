package com.kh.healthDao.manager.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.manager.model.service.ManagerService;
import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.manager.model.vo.Qna;
import com.kh.healthDao.manager.model.vo.Refund;
import com.kh.healthDao.mypage.model.vo.QnaDept;


@Controller
@RequestMapping("/manager/*")
public class ManagerController {
	
	private ManagerService managerService;
	
	@Autowired
	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}

	// 정산내역 페이징 된
	@GetMapping("/calculateList")
	public ModelAndView managerCalculateList(ModelAndView mv, @RequestParam int page) {
		
		Map<String, Object> map = managerService.calculateList(page);
		
		mv.addObject("calculateList", map.get("calculateList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("manager/calculateList");
		
		return mv;
	}
	
	
	// 회원문의 페이징 된
	@GetMapping("/memberInquiry")
	public ModelAndView managerMemberInquiry(ModelAndView mv, @RequestParam int page) {
		
		Map<String, Object> map = managerService.InquiryPaging(page);
		
		mv.addObject("QnaList", map.get("QnaList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("manager/memberInquiry");
		
		return mv;
	}
	
	// 환불내역 페이징 된
	@GetMapping("/refundList")
	public ModelAndView managerRefundList(ModelAndView mv, @RequestParam int page) {
		
		Map<String, Object> map = managerService.refundListPaging(page);
		
		mv.addObject("refundList", map.get("refundList"));
		mv.addObject("listCount", map.get("listCount"));
		mv.addObject("pi", map.get("pi"));
		mv.setViewName("manager/refundList");
		
		return mv;
	}
	
	
	// 회원문의답변
	@GetMapping("memberInquiryAnswer")
	public ModelAndView managerMemberInquiryAnswer(ModelAndView mv, @RequestParam int qNo) {
		
		Qna QnaList = managerService.listQna(qNo);
		
		mv.addObject("QnaList", QnaList);
		mv.setViewName("manager/memberInquiryAnswer");
		
		return mv;
	}
	
	@PostMapping("/qanswer")
	public String managerQAnswer(Qna qna, RedirectAttributes rttr) {
		
		String result = managerService.managerQAnswer(qna) > 0 ? "문의 답변 수정 성공" : "문의 답변 수정 실패";
		rttr.addFlashAttribute("msg", result);
		
		return "redirect:/manager/memberInquiry?page=1";
	}
	
	// 환불 성공 버튼 업데이트
	@PostMapping("/refundOk")
	public String managerRefundOk(Refund refund, RedirectAttributes rttr) {
		
		String result = managerService.managerRefundOk(refund) > 0 ? "환불 수정 성공" : "환불 수정 실패";
		rttr.addFlashAttribute("msg", result);
		
		return "redirect:/manager/refundList?page=1";
	}
	
	 @GetMapping("/excel/download")
	    public void excelDownload(HttpServletResponse response) throws IOException {
//	        Workbook wb = new HSSFWorkbook();
	        Workbook wb = new XSSFWorkbook();
	        Sheet sheet = wb.createSheet("시트이름");
	        Row row = null;
	        Cell cell = null;
	        int rowNum = 0;
	        
	        List<Payment> excelList = managerService.excelList();

	        // Header
	        row = sheet.createRow(rowNum++);
	        cell = row.createCell(0);
	        cell.setCellValue("제품번호");
	        cell = row.createCell(1);
	        cell.setCellValue("제품명");
	        cell = row.createCell(2);
	        cell.setCellValue("회사명");
	        cell = row.createCell(3);
	        cell.setCellValue("총판매수량");
	        cell = row.createCell(4);
	        cell.setCellValue("정산금액");

	        
	        // Body
	        for(Payment p : excelList) {
		        row = sheet.createRow(rowNum++);
		        cell = row.createCell(0);
		        cell.setCellValue(p.getPayNo());
		        cell = row.createCell(1);
		        cell.setCellValue(p.getProductTitle());
		        System.out.println(p.getProductTitle());
		        cell = row.createCell(2);
		        cell.setCellValue(p.getProductBrand());
		        cell = row.createCell(3);
		        cell.setCellValue(p.getQuantity());
		        cell = row.createCell(4);
		        cell.setCellValue(p.getQuantity()*p.getProductPrice());
	        }

	        

	        // 컨텐츠 타입과 파일명 지정
	        response.setContentType("ms-vnd/excel");
//	        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
	        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

	        // Excel File Output
	        wb.write(response.getOutputStream());
	        wb.close();
	    }
	}
	


	
	
	
	
	
















