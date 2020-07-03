package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ConfigEntity;
import com.example.demo.repo.ConfigurationRepo;
import com.example.demo.vo.ConfigRequestVO;
import com.example.demo.vo.ConfigResponseVO;
import com.google.common.collect.Lists;

@RestController
@ConfigurationProperties
@RequestMapping("/myconfig")
public class ConfigController {
	@Autowired
	ConfigurationRepo repo;

	@PostMapping("/addConfig")
	public ResponseEntity<String> addConfig(@RequestBody ConfigRequestVO request) {
		try {
			repo.save(request.getConfig());
		} catch (Exception e) {
			return new ResponseEntity<>("Fail", HttpStatus.OK);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@GetMapping("/allConfig")
	public ResponseEntity<ConfigResponseVO> retrieveAllConfig() {
		ConfigResponseVO resp = new ConfigResponseVO();
		try {
			java.util.List<ConfigEntity> configList = Lists.newArrayList(repo.findAll().iterator());

			resp.setConfigList(configList);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<ConfigResponseVO>(resp, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/getConfigById") public ResponseEntity<ConfigResponseVO>
	 * getConfigById(@RequestBody ConfigRequestVO request) { ConfigResponseVO resp =
	 * new ConfigResponseVO(); try { java.util.List<ConfigEntity> configList =
	 * Lists.newArrayList(repo.findById(request.i).iterator());
	 * 
	 * resp.setConfigList(configList); } catch (Exception e) { return new
	 * ResponseEntity<>(null, HttpStatus.OK); } return new
	 * ResponseEntity<ConfigResponseVO>(resp, HttpStatus.OK); }
	 */
	
	@GetMapping("/clearCache")
	public ResponseEntity<String> deleteAllConfig() {
		try {
			repo.deleteAll();
		} catch (Exception e) {
			return new ResponseEntity<>("Fail", HttpStatus.OK);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	
	}
}
