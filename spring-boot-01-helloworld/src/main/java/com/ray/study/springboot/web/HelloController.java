package com.ray.study.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc
 *
 * @author shirayner
 * @date 2018/12/4
 */

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}

}
