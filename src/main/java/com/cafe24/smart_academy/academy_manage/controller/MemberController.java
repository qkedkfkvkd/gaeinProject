package com.cafe24.smart_academy.academy_manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.smart_academy.academy_manage.member.service.MemberService;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;

@Controller
public class MemberController {
// 로그인, 학생 및 강사 등록 시 아이디-이메일 중복확인, 상담코드 관리하는 컨트롤러
	
	@Autowired
	private MemberService memberService;
	// 회원 정보 관리 서비스 객체
	
	// 로그인 버튼 클릭시 로그인 폼으로 이동
	@GetMapping("/loginMember")
	public String memberLogin() {
		return "/view/login/login";
	}
	
	
	// 로그인 처리
	@PostMapping("/loginMember")
	public String memberLogin(Model model, HttpSession session, MemberLogin login) {
		System.out.println(login.getMemberId() + " <- memberId   memberLogin()   MemberController.java");
		System.out.println(login.getMemberPw() + " <- memberPw   memberLogin()   MemberController.java");
		
		Map<String, Object> map = memberService.memberLogin(login);
		// 아이디와 비밀번호를 가지고 로그인 서비스 실행
		
		String path = "";
		// 페이지 이동할 경로
		
		if(map.get("result") == null) { // result 키값의 객체가 없다 -> 로그인 성공했다.
			System.out.println(map.get("memberId") + "<- map.memberId   memberLogin()   MemberController.java");
			System.out.println(map.get("memberLevel") + "<- map.memberLevel   memberLogin()   MemberController.java");
			System.out.println(map.get("memberName") + "<- map.memberName   memberLogin()   MemberController.java");
			
			session.setAttribute("memberId", map.get("memberId"));
			session.setAttribute("memberLevel", map.get("memberLevel"));
			session.setAttribute("memberName", map.get("memberName"));
			// 세션객체에 아이디, 권한, 이름을 할당한다.
			
//			String level = (String)map.get("memberLevel");
//			if(level.equals("관리자")) {
//				path = "/view/adminIndex";
//			} else if(level.equals("강사")) {
//				path = "/view/teacherIndex";
//			} else {
				path = "/view/index";
//			}
			
		} else { // 로그인 실패시
			System.out.println(map.get("result") + "<- map.result   memberLogin()   MemberController.java");
			
			path = "/view/login/login";
			// 로그인 실패했으므로 로그인 페이지로 이동한다.
			
			model.addAttribute("result", map.get("result"));
			// 로그인 실패 관련 메세지를 넣어준다(아이디 불일치 혹은 비밀번호 불일치 메세지)
		}
		return path;
	}
	
	
	// 로그아웃
	@GetMapping("/logoutMember")
	public String memberLoginOut(HttpSession session) {
		session.invalidate();
		// 세션 무효화
		
		return "redirect:/";
	}
	
	
	// 아이디 찾기 폼 이동
	@GetMapping("/findLoginId")
	public String findLoginId() {
		return "/view/academyRegister/academyRegisterInfo/findLoginId";
	}
	
	
	// 아이디 찾기
	@PostMapping("/findLoginId")
	@ResponseBody
	public Map<Object, Object> findLoginId(@RequestBody Map<String, Object> findLoginInfo) {
		String memberName = (String)findLoginInfo.get("memberName");
		String memberEmail = (String)findLoginInfo.get("memberEmail");
		// 입력한 이름과 이메일을 변수에 저장한다.
		
		System.out.println(memberName + "<- memberName   findLoginId()   MemberController.java");
		System.out.println(memberEmail + "<- memberEmail   findLoginId()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 뷰페이지에 보낼 객체
		
		String memberId = memberService.findLoginId(findLoginInfo);
		// 아이디 찾기 메소드 호출
		
		System.out.println(memberId + " <- memberId   findLoginId()   MemberController.java");
		
		if(memberId != null) { // 아이디가 존재함 : 입력한 이름과 이메일이 디비의 데이터와 정확히 일치
			map.put("result", "success");
			// 아이디를 찾았다는 뷰페이지의 조건문 통과
			
			map.put("memberId", memberId);
			// 찾은 아이디를 넣어줌
		} else { // 아이디가 존재하지 않음 : 이름이나 이메일을 잘못 입력
			map.put("result", "fail");
			// 아이디를 찾지 못했다는 뷰페이지의 조건문 통과
		}
		
		return map;
	}
	
	
	// 비밀번호 찾기 폼 이동
	@GetMapping("/findLoginPw")
	public String findLoginPw() {
		return "/view/academyRegister/academyRegisterInfo/findLoginPw";
	}
	
	
	// 비밀번호 찾기
	@PostMapping("/findLoginPw")
	@ResponseBody
	public Map<Object, Object> findLoginPw(@RequestBody Map<String, Object> findLoginInfo) {
		String memberId = (String)findLoginInfo.get("memberId");
		String memberEmail = (String)findLoginInfo.get("memberEmail");
		// 입력한 아이디와 이메일을 변수에 저장한다.
		
		System.out.println(memberId + "<- memberId   findLoginPw()   MemberController.java");
		System.out.println(memberEmail + "<- memberEmail   findLoginPw()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 뷰페이지에 보낼 객체
		
		MemberLogin loginInfo = memberService.findLoginPw(findLoginInfo);
		// 비밀번호 찾기 메소드 호출
		
		if(loginInfo != null) { // 객체가 존재함 : 입력한 아이디와 이메일이 디비의 데이터와 정확히 일치
			System.out.println(loginInfo.getMemberId() + " <- memberId   findLoginPw()   MemberController.java");
			System.out.println(loginInfo.getMemberPw() + " <- memberPw   findLoginPw()   MemberController.java");
			
			map.put("result", "success");
			// 비밀번호를 찾았다는 뷰페이지의 조건문 통과
			
			map.put("memberId", loginInfo.getMemberId());
			// 아이디를 넣어줌
			
			map.put("memberPw", loginInfo.getMemberPw());
			// 비밀번호를 넣어줌
		} else {
			map.put("result", "fail");
			// 객체가 존재하지 않음 : 이름이나 이메일을 잘못 입력
		}
		
		return map;
	}
	
	
	
	
	
	// 관리자 전용 학생, 강사 등록 폼에서 아이디 중복버튼 눌렀을 경우
	@PostMapping("/memberIdOverlapChk")
	@ResponseBody
	public Map<Object, Object> memberIdOverlapChk(@RequestBody String memberId) {
		System.out.println(memberId + "<- memberId   memberIdOverlapChk()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 뷰페이지에 보낼 객체
		
		String memberIdResult = memberService.memberLoginById(memberId);
		// 서비스의 아이디 중복확인 메소드 호출
		
		if(memberIdResult == null) { // 입력한 아이디가 존재한다면
			map.put("result", 1);
			// 로그인 테이블에 해당 아이디값이 존재하지 않음 : 사용 가능한 아이디
			
			//result = "{\"result\":\"1\"}"; // {"result":"1"} -> 사용 가능한 아이디
		} else {
			map.put("result", 0);
			// 로그인 테이블에 해당 아이디값이 존재함 : 사용 중인 아이디
			
			//result = "{\"result\":\"0\"}"; // {"result":"0"} -> 사용 중인 아이디
		}
		
		return map;
	}
	
	
	// 관리자 전용 학생, 강사 등록 폼에서 이메일 중복버튼 눌렀을 경우
	@PostMapping("/memberEmailOverlapChk")
	@ResponseBody
	public Map<Object, Object> memberEmailOverlapChk(@RequestBody String memberEmail) {
		System.out.println(memberEmail + "<- memberEmail   memberEmailOverlapChk()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 뷰페이지에 보낼 객체
		
		String memberEmailResult = memberService.memberByEmail(memberEmail);
		// 서비스의 이메일 중복확인 메소드 호출
		
		if(memberEmailResult == null) { // 입력한 이메일이 존재한다면
			map.put("result", 1);
			// 회원신상정보 테이블에 해당 이메일 값이 존재하지 않음 : 사용 가능한 이메일
			
		} else {
			map.put("result", 0);
			// 회원신상정보 테이블에 해당 이메일 값이 존재함 : 사용 중인 이메일
		}
		
		return map;
	}
	
	
	// 로그인정보 수정 폼 이동 (패스워드 수정처리)
	@GetMapping("/updateLoginPassword")
	public String updateLoginPassword(
			 @RequestParam(value = "memberId") String memberId
			,Model model) {
		Member member = memberService.memberSimpleInfo(memberId);
		// 회원의 간단한 정보만 얻어온다.
		// 아이디, 이름, 생년월일
		
		model.addAttribute("member", member);
		// 화면에 표시해줄 회원의 간단한 정보를 넣어준다.
		
		return "/view/academyRegister/academyRegisterInfo/updateLoginPassword";
	}
	
	// 로그인정보 수정 처리 (패스워드 수정처리)
	@PostMapping("/updateLoginPassword")
	public String updateLoginPassword(
			 MemberLogin login
			,RedirectAttributes redirectAttributes) {
		String message = memberService.updateLoginPassword(login);
		// 로그인정보 수정처리 후 메세지를 반환받는다.
		
		String path = "redirect:/";
		// 로그인정보 수정에 성공했을 경우 메인 인덱스 페이지로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 로그인정보 수정에 실패했다는 뜻이다.
			
			System.out.println("로그인정보 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("memberId", login.getMemberId());
			// 패스워드 수정 페이지로 리다이렉트하면서 회원아이디를 넘겨준다.
			
			path = "redirect:/updateLoginPassword";
			//  패스워드 수정 페이지로 이동
		}
		
		return path;
	}
	
	
	
	
	
	// 관리자 : 관리자 자신의 정보 수정 폼 이동
	@GetMapping("/updateAdminInfo")
	public String updateAdminInfo(
			 @RequestParam(value = "memberId") String memberId
			,Model model) {
		
		System.out.println(memberId + " <- memberId   updateAdminInfo()   MemberController.java");
		
		Map<String, Object> adminInfo =
				memberService.detailAdminInfoByMemberId(memberId);
		// 관리자 자신의 상세 정보를 얻어온다.
		
		model.addAttribute("adminInfo", adminInfo);
		// 화면에 뿌려줄 관리자 상세정보
		
		return "/view/academyRegister/academyRegisterInfo/detailAdminInfo";
	}
	
	
	// 관리자 : 관리자 자신의 정보 수정처리
	@PostMapping("/updateAdminInfo")
	public String updateAdminInfo(
			 MemberLogin login
			,Member member
			,RedirectAttributes redirectAttributes) {
		
		int result = memberService.updateAdminInfo(login, member);
		// 관리자 자신의 정보 수정 처리 후 메세지를 반환받는다.
		
		String path = "redirect:/";
		// 관리자정보 수정에 성공했을 경우 메인 인덱스 페이지로 이동하게 초기화한다.
		
		if(result < 2) {
			// 리턴받은 결과값이 2보다 작다면 관리자정보 수정에 실패했다는 뜻이다.
			
			System.out.println("관리자정보 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("memberId", login.getMemberId());
			// 관리자정보 상세 페이지로 리다이렉트하면서 회원아이디를 넘겨준다.
			
			path = "redirect:/updateAdminInfo";
			// 관리자정보 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	
	
	
	// 관리자 : 상담기준코드 리스트 가져오기
	@GetMapping("/counselStandardList")
	public String counselStandardList(Model model) {
		
		List<Map<String, Object>> counselStandardList =
				memberService.counselStandardList();
		// 관리자 : 상담기준코드 리스트 가져오기
		
		
		int counselTypeListSize = memberService.counselTypeListSize();
		// 관리자 : 상담기준코드 리스트에서 상담결과코드를 추가할려고 할 때
		// 참조하는 테이블인 상담구분코드에 레코드가 존재하는지 리스트 사이즈 숫자 리턴
		// --> 0: 존재하지 않음
		
		
		List<CounselType> counselTypeList = memberService.counselTypeList();
		// 상담구분코드 테이블에서 전체 상담구분코드 리스트 가져오기
		// (기본키 - 상담구분코드)와 상담구분명만 가져온다.
		
		
		//List<CounselResult> counselResultList = memberService.counselResultList();
		// 상담결과코드 테이블에서 전체 상담결과코드 리스트 가져오기
		// (기본키 - 상담결과코드)와 상담결과명만 가져온다.
		
		
		model.addAttribute("counselStandardList", counselStandardList);
		// 화면에 보여줄 상담기준코드 리스트 (상담구분-상담결과 조인)
		
		model.addAttribute("counselStandardListSize", counselStandardList.size());
		// 리스트의 사이즈를 보고 리스트 존재여부 판단
		
		model.addAttribute("counselTypeListSize", counselTypeListSize);
		// 상담결과 추가 버튼 노출 시 참조하는 상담구분 테이블의 리스트 존재여부 판단
		
		model.addAttribute("counselTypeList", counselTypeList);
		// 검색 폼의 샐랙트 박스에 넣어줄 상담구분 리스트
		
		//model.addAttribute("counselResultList", counselResultList);
		
		return "/view/academyRegister/academyRegisterCode/listCounselStandard";
	}
	
	
	// 관리자 : 상담기준코드 리스트에서 상담구분코드 선택시
	//			선택값에 따른 상담결과코드 리스트 보이기
	@PostMapping("/counselResultListSelect")
	@ResponseBody
	public List<Map<String, Object>> counselResultListSelect(@RequestBody String counselTypeNo) {
		System.out.println(counselTypeNo
				+ "<- counselTypeNo   counselResultSelect()   MemberController.java");
		
		//Map<Object, Object> map = new HashMap<Object, Object>();
		
		List<Map<String, Object>> counselResultList =
				memberService.counselResultListBycounselTypeNo(counselTypeNo);
		// 선택한 상담구분코드를 참조하는 상담결과 리스트 가져오기
		
		return counselResultList;
	}
	
	
	// 관리자 : 선택한 상담구분코드와 상담결과코드로 상담기준리스트 검색결과 가져오기
	@PostMapping("/searchCounselStandard")
	public String searchCounselStandard(CounselResult counselResult, Model model) {
		
		List<Map<String, Object>> searchCounselStandardList =
				memberService.counselStandardList(counselResult);
		// 선택한 상담구분코드와 상담결과코드로 상담기준리스트 검색결과 가져오기
		
		
		int counselTypeListSize = memberService.counselTypeListSize();
		// 관리자 : 상담기준코드 리스트에서 상담결과코드를 추가할려고 할 때
		// 참조하는 테이블인 상담구분코드에 레코드가 존재하는지 리스트 사이즈 숫자 리턴
		// --> 0: 존재하지 않음
		
		
		List<CounselType> counselTypeList = memberService.counselTypeList();
		// 상담구분코드 테이블에서 전체 상담구분코드 리스트 가져오기
		// (기본키 - 상담구분코드)와 상담구분명만 가져온다.
		
		
		model.addAttribute("counselStandardList", searchCounselStandardList);
		// 화면에 보여줄 상담기준 검색결과 리스트(상담구분 - 상담결과 조인)
		
		model.addAttribute("counselStandardListSize", searchCounselStandardList.size());
		// 리스트의 사이즈를 보고 리스트 존재여부 판단
		
		model.addAttribute("counselTypeListSize", counselTypeListSize);
		// 상담결과 추가 버튼 노출 시 참조하는 상담구분 테이블의 리스트 존재여부 판단
		
		model.addAttribute("counselTypeList", counselTypeList);
		// 검색 폼의 샐랙트 박스에 넣어줄 상담구분 리스트
		
		return "/view/academyRegister/academyRegisterCode/listCounselStandard";
	}
	
	
	
	
	
	// 관리자 : 상담구분코드 리스트 이동
	@GetMapping("/counselTypeList")
	public String counselTypeList(Model model) {
		
		List<CounselType> counselTypeViewList = memberService.counselTypeList();
		// 상담구분코드 리스트 가져오기
		
		model.addAttribute("counselTypeList", counselTypeViewList);
		// 검색 폼의 샐랙트 박스에 넣을 상담구분코드 리스트
		
		model.addAttribute("counselTypeViewList", counselTypeViewList);
		// 화면에 보여줄 상담구분코드 리스트
		
		model.addAttribute("counselTypeViewListSize", counselTypeViewList.size());
		// 화면에 리스트의 존재 여부를 알려줄 리스트 사이즈
		
		return "/view/academyRegister/academyRegisterCode/listCounselType";
	}
	
	
	// 관리자 : 상담구분코드 검색결과 리스트
	@PostMapping("/searchCounselType")
	public String searchCounselType(
			 @RequestParam(value = "counselTypeNo") String counselTypeNo
			,Model model) {
		
		List<CounselType> counselTypeList = memberService.counselTypeList();
		// 검색 폼의 샐랙트 박스에 넣어줄 상담구분코드 리스트 가져오기
		
		List<CounselType> counselTypeViewList = memberService.counselTypeList(counselTypeNo);
		// 상담구분코드 검색결과 리스트 가져오기
		
		model.addAttribute("counselTypeList", counselTypeList);
		// 검색 폼의 샐랙트 박스에 넣을 상담구분코드 리스트
		
		model.addAttribute("counselTypeViewList", counselTypeViewList);
		// 화면에 보여줄 검색결과 상담구분코드 리스트
		
		model.addAttribute("counselTypeViewListSize", counselTypeViewList.size());
		// 화면에 리스트의 존재 여부를 알려줄 리스트 사이즈
		
		return "/view/academyRegister/academyRegisterCode/listCounselType";
	}
	
	
	// 관리자 : 상담구분코드 추가폼 이동
	@GetMapping("/addCounselType")
	public String addCounselType() {
		return "/view/academyRegister/academyRegisterCode/addCounselType";
	}
	
	
	// 관리자 : 상담구분코드 테이블에서 상담구분코드 중복확인
	@PostMapping("/counselTypeNoOverlapChk")
	@ResponseBody
	public Map<Object, Object> counselTypeNoOverlapChk(
			@RequestBody String inputCounselTypeNo) {
		System.out.println(inputCounselTypeNo
				+ "<- inputCounselTypeNo   counselTypeNoOverlapChk()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 뷰페이지에 보낼 객체
		
		String counselTypeNoResult = memberService.counselTypeByCounselTypeNo(inputCounselTypeNo);
		// 서비스의 상담구분코드 중복확인 메소드 호출
		
		if(counselTypeNoResult == null) {
			map.put("result", 1);
			// 상담구분코드 테이블에 해당 코드 값이 존재하지 않음 : 사용 가능한 코드
			
		} else {
			map.put("result", 0);
			// 상담구분코드 테이블에 해당 코드 값이 존재함 : 사용 중인 코드
		}
		
		return map;
	}
	
	
	// 관리자 : 상담구분코드 추가처리
	@PostMapping("/addCounselType")
	public String addCounselType(Model model, CounselType counselType) {
		String message = memberService.addCounselType(counselType);
		// 상담구분 추가처리 후 메세지 반환
		
		String path = "/view/academyRegister/academyRegisterCode/addCounselType";
		// 상담구분코드 추가에 실패했을 경우 다시 상담구분코드를 추가하는 폼으로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 상담구분코드 추가에 성공했다는 뜻이다.
			path = "redirect:/counselTypeList";
			// 상담구분코드 리스트로 이동한다.
			
			//path = "redirect:/listCounselStandard";
			// 상담기준코드 리스트로 이동한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 상담구분코드 상세 보기
	@GetMapping("/updateCounselType")
	public String updateCounselType(@RequestParam("counselTypeNo") String counselTypeNo
				, Model model) {
		CounselType counselType =
				memberService.detailCounselTypeByCounselTypeNo(counselTypeNo);
		// 해당 상담구분코드를 가진 상담구분 전체 내용 가져오기
		
		List<Map<String, Object>> counselResultList =
				memberService.counselResultListBycounselTypeNo(counselTypeNo);
		// 해당 상담구분코드를 참조하는 상담결과목록 가져오기
		
		model.addAttribute("counselType", counselType);
		// 화면에 보여줄 상담구분 상세정보
		
		model.addAttribute("counselResultList", counselResultList);
		// 화면에 보여줄 해당 상담구분코드를 참조하는 상담결과 리스트
		
		model.addAttribute("counselResultListSize", counselResultList.size());
		// 상담 리스트의 사이즈를 보고 상담구분-결과목록를 뿌려줄 것인지,
		// '해당 항목으로 등록된 상담결과목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		return "/view/academyRegister/academyRegisterCode/detailCounselType";
	}
	
	
	// 관리자 : 상담구분코드 수정 처리
	@PostMapping("/updateCounselType")
	public String updateCounselType(CounselType counselType, Model model,
			RedirectAttributes redirectAttributes) {
		String message = memberService.updateCounselType(counselType);
		// 해당 상담구분코드 수정 처리 후 메세지 반환
		
		String path = "redirect:/counselTypeList";
		// 상담구분 수정에 성공했을 경우 상담구분코드 리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 상담구분 수정에 실패했다는 뜻이다.
			
			System.out.println("상담구분 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("counselTypeNo", counselType.getCounselTypeNo());
			// 상담구분 상세 페이지로 리다이렉트하면서 상담구분코드를 넘겨준다.
			
			path = "redirect:/updateCounselType";
			// 상담구분 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 상담구분코드 삭제처리
	@GetMapping("/deleteCounselType")
	public String deleteCounselType(
					@RequestParam(value = "counselTypeNo")String counselTypeNo) {
		
		System.out.println(counselTypeNo
				+ " <- counselTypeNo   deleteCounselType()   MemberController.java");
		String message = memberService.deleteCounselType(counselTypeNo);
		// 해당 상담구분 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteCounselType()   MemberController.java");
		
		return "redirect:/counselTypeList";
		// 삭제 완료 후 상담구분코드 리스트로 이동한다.
	}
	
	
	
	
	
	// 관리자 : 상담결과코드 추가폼 이동
	@GetMapping("/addCounselResult")
	public String addCounselResult(Model model) {
		List<CounselType> counselTypeList = memberService.counselTypeList();
		
		model.addAttribute("counselTypeList", counselTypeList);
		return "/view/academyRegister/academyRegisterCode/addCounselResult";
	}
	
	
	// 관리자 : 상담결과코드 테이블에서 상담결과코드 중복확인
	@PostMapping("/counselResultNoOverlapChk")
	@ResponseBody
	public Map<Object, Object> counselResultNoOverlapChk(
			@RequestBody String inputCounselResultNo) {
		System.out.println(inputCounselResultNo
				+ "<- inputCounselResultNo   counselResultNoOverlapChk()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String counselResultNoResult =
				memberService.counselResultByCounselResultNo(inputCounselResultNo);
		// 서비스의 상담결과코드 중복확인 메소드 호출
		
		if(counselResultNoResult == null) {
			map.put("result", 1);
			// 상담결과코드 테이블에 해당 코드 값이 존재하지 않음 : 사용 가능한 코드
			
		} else {
			map.put("result", 0);
			// 상담결과코드 테이블에 해당 코드 값이 존재함 : 사용 중인 코드
		}
		
		return map;
	}
	
	
	// 관리자 : 상담결과코드 추가처리
	@PostMapping("/addCounselResult")
	public String addCounselResult(Model model, CounselResult counselResult) {
		String message = memberService.addCounselResult(counselResult);
		
		String path = "/view/academyRegister/academyRegisterCode/addCounselResult";
		// 상담결과코드 추가에 실패했을 경우 다시 상담결과코드를 추가하는 폼으로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 상담결과코드 추가에 성공했다는 뜻이다.
			path = "redirect:/counselStandardList";
			// 상담기준코드 리스트로 이동한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 상담결과코드 상세보기
	@GetMapping("/updateCounselResult")
	public String updateCounselResult(@RequestParam("counselResultNo") String counselResultNo
			, Model model) {
		CounselResult counselResult =
				memberService.detailCounselResultByCounselResultNo(counselResultNo);
		// 해당 상담결과코드를 가진 상담결과 전체 내용 가져오기
		
		List<Map<String, Object>> counselAppointmentList =
				memberService.counselAppointmentListBycounselResultNo(counselResultNo);
		// 해당 상담결과코드를 참조하는 상담예약목록 가져오기
		
		List<CounselType> counselTypeList = memberService.counselTypeList();
		// 샐랙트 박스에 넣어줄 상담구분코드 리스트 가져오기
		
		model.addAttribute("counselTypeList", counselTypeList);
		// 샐랙트 박스에 넣어줄 상담구분코드 리스트
		
		model.addAttribute("counselResult", counselResult);
		// 화면에 보여줄 상담결과 객체
		
		model.addAttribute("counselAppointmentList", counselAppointmentList);
		model.addAttribute("counselAppointmentListSize", counselAppointmentList.size());
		// 상담 리스트의 사이즈를 보고 상담구분-결과목록를 뿌려줄 것인지,
		// '해당 항목으로 등록된 상담결과목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		return "/view/academyRegister/academyRegisterCode/detailCounselResult";
	}
	
	
	// 관리자 : 상담결과코드 수정 처리
	@PostMapping("/updateCounselResult")
	public String updateCounselResult(CounselResult counselResult, Model model,
			RedirectAttributes redirectAttributes) {
		String message = memberService.updateCounselResult(counselResult);
		// 해당 상담결과코드 수정 처리 후 메세지 반환
		
		String path = "redirect:/counselStandardList";
		// 상담결과 수정에 성공했을 경우 상담기준코드 리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 상담결과 수정에 실패했다는 뜻이다.
			
			System.out.println("상담구분 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("counselResultNo",
						counselResult.getCounselResultNo());
			// 상담결과 상세 페이지로 리다이렉트하면서 상담결과코드를 넘겨준다.
			
			path = "redirect:/updateCounselResult";
			// 상담결과 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 상담결과코드 삭제 처리
	@GetMapping("/deleteCounselResult")
	public String deleteCounselResult(
					@RequestParam(value = "counselResultNo")String counselResultNo) {
		System.out.println(counselResultNo
				+ " <- counselResultNo   deleteCounselResult()   MemberController.java");
		String message = memberService.deleteCounselResult(counselResultNo);
		// 해당 상담결과 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteCounselResult()   MemberController.java");
		
		return "redirect:/counselStandardList";
		// 삭제 완료 후 상담기준코드 리스트로 이동한다.
	}
}
