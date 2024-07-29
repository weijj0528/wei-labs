package com.weiun.springboot.base.controller;

import com.weiun.springboot.base.bean.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author William
 * @Date 2019/2/27
 * @Description Rest风格
 */
@RestController
@RequestMapping("/rest")
public class RestDemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @RequestMapping(value = {"/json/{id}", "/jjson/{id}"}, method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response jsonResponse(@PathVariable("id") String id) {
        return new Response();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Response xmlResponse() {
        return new Response();
    }

}
