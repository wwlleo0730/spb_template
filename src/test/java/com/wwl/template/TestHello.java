package com.wwl.template;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.wwl.template.service.PersonSay;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestHello {
	
	@Resource
	private PersonSay say;

	@Test
	public void testSay(){
		Assert.assertEquals("Hello! World", say.say());
		
		assertThat("Hello! Sam").isEqualTo(say.say("Sam"));
	}
	
	
}
