package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Member;

@WebServlet("/ajax/test")
public class AjaxTextController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/ajax/test.jsp").forward(req, resp);
		
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// System.out.println("요청받아줌");
		
		// 응답 데이터 형식 지정 - MIME 타입
		// resp.setContentType("text/html; charset=utf-8");
		resp.setContentType("application/json; charset=utf-8");

		// 응답 출력 스트림
		PrintWriter out = resp.getWriter();
		
		// 데이터 직접 출력 하기
		
		// 단순 문자
		// out.println("response Data Send");
		
		// JSON Text, String 타입 전송
		// out.println("\"JSON DATA\"");
		
		// JSON Text, Object 타입
		// out.println("{ \"data\" : \"JSON DATA\" }");
		// out.println("{ \"cnt\" : 1 "}");
		
		// DTO 를 이용한 출력 (응답)
		
		Member member = new Member();
		
		member.setId("apple");
		member.setPw("Hi");
		
		// 마샬링 및 응답 (Java DTO Object -> JSON Text)
		// out.println(new Gson().toJson(member));
		
		// List 를 이용한 응답
		List<Member> list = new ArrayList<>();
		
		Member m1 = new Member();
		m1.setId("Apple");
		m1.setPw("iPhone");
		
		Member m2 = new Member();
		m2.setId("Banana");
		m2.setPw("bPhone");
		
		list.add(m1);
		list.add(m2);
		
		out.println(new Gson().toJson(list));
	
	}
	
}







