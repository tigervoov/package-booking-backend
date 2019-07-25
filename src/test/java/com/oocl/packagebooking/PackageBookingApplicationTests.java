package com.oocl.packagebooking;

import com.oocl.packagebooking.entity.Order;
import com.oocl.packagebooking.repository.OrderRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PackageBookingApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void should_return_all_orders_when_request_getAllOrders_url() throws Exception {

		//given
		Order order=new Order("201907250001","mike","13750000123","已预约",new Date().getTime());
		orderRepository.saveAndFlush(order);
		JSONObject jsonObject=new JSONObject(order);

		//when
		String result=this.mockMvc.perform(get("/expressOrders")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		JSONArray jsonArray=new JSONArray(result);

		//then
		Assertions.assertEquals(1,jsonArray.length());
	}
	@Test
	public void should_return_success_message_when_request_addNewOrders() throws Exception {

		//given
		Order order=new Order("201907250001","mike","13750000123","未取件");
		JSONObject jsonObject=new JSONObject(order);

		//when
		String result=this.mockMvc.perform(post("/expressOrders").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse().getContentAsString();

		//then
		Assertions.assertEquals("添加成功",result);
	}
	@Test
	public void should_return_success_message_when_request_expressOrder() throws Exception {

		//given
		Order order=new Order("201907250001","mike","13750000123","未取件");
		JSONObject jsonObject=new JSONObject(order);

		//when
		String result=this.mockMvc.perform(post("/expressOrders").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse().getContentAsString();

		//then
		Assertions.assertEquals("添加成功",result);
	}
	@Test
	public void should_return_success_message_when_request_expressTakeOrdersTime() throws Exception {

		//given
		Order order=new Order("201907250001","mike","13750000123","未取件");
		orderRepository.saveAndFlush(order);

		Order sendRequestObj=new Order("201907250001",new Date().getTime());
		JSONObject jsonObject=new JSONObject(sendRequestObj);

		//when
		String result=this.mockMvc.perform(put("/expressOrders/201907250001").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse().getContentAsString();

		//then
		Assertions.assertEquals("预约成功",result);
	}
}