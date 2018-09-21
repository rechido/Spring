package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.SampleVO;

@RestController
@RequestMapping("/sample")
public class SampleRestController {
	
	@RequestMapping("/hello")
	public String sayHello(){
		
		return "Hello RestAPI";
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO(){
		
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(123);
		return vo; // 그냥 @Controller에서 이와같이 객체를 직접 리턴할 경우 에러 발생. String 타입을 제외하고 리턴값 식별 못함
		// @RestController에서는 json 문서 형식으로 반환값을 보여줌
		
	}
	
	@RequestMapping("/sendVOList")
	public List<SampleVO> sendVOList(){				
		
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++){
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			
			list.add(vo);
		}
		
		return list;
		
	}
	
	@RequestMapping("/sendVOMap")
	public Map<Integer, SampleVO> sendVOMap(){	
		
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for(int i=0; i<10; i++){
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			
			map.put(i, vo);
		}
		
		return map;
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/sendVOListErrorNot")
	public ResponseEntity<List<SampleVO>> sendVOListErrorNot(){				
		
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++){
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			
			list.add(vo);
		}
		
		return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);
		
	}

}