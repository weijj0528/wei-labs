package com.weiun.springboot.base.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Setter;

/**
 * @author Administrator
 * @createTime 2019/5/19 12:12
 * @description
 */
@Setter
@JacksonXmlRootElement(localName = "res")
public class Response {


    private String code;

    private String msg;

    @JacksonXmlProperty(localName = "Code")
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
