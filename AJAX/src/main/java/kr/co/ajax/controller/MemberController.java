package kr.co.ajax.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.ajax.service.MemberService;
import kr.co.ajax.vo.MemberVo;
import kr.co.ajax.vo.TermsVo;




@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;

	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}
	////////변진하 로그인 2021/11/25 ////////////
	@PostMapping("/member/login")
	public String login(HttpSession sess, String uid, String pass) {
		  MemberVo vo = service.selectMember(uid, pass);

	  if(vo == null) {
		  // 회원이 아닐경우
		  return "redirect:/member/login?success=100";
	  }else {
		  // 회원이 맞을경우
		  sess.setAttribute("sessMember", vo);
		  return "redirect:/index"; 
	  }
	}
	/////////////////////////////////////
	@GetMapping("/member/register")
	public String register() {
		return "/member/register";
	}
	
	@PostMapping("/member/register")
	public String register(MemberVo vo) {
		
		service.insertMember(vo);
		
		return "redirect:/index";
	}
	
	
	@GetMapping("/member/terms")
	public String terms(Model model) {
		
		TermsVo vo = service.selectTerms();
		model.addAttribute("vo", vo);
		
		return "/member/terms";
	}
	
	////////////변진하 2021 11 25 이메일 벨리데이션///////////////
	
	@ResponseBody
	@GetMapping("/member/checkEmail")
	public String checkEmail(String email) {
		int result = service.selectCountEmail(email);
		// Json 객체 생성 후 클라이언트 전송
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		return new Gson().toJson(json);
	}
	////////////////////////////////

}
