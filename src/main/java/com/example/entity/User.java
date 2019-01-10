package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;


/**
 * Created by Administrator on 2017/2/10.
 * 一、常用的校验注解
 （1）常用标签
 @Null  被注释的元素必须为null
 @NotNull  被注释的元素不能为null
 @AssertTrue  被注释的元素必须为true
 @AssertFalse  被注释的元素必须为false
 @Min(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 @Max(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 @Size(max,min)  被注释的元素的大小必须在指定的范围内。
 @Digits(integer,fraction)  被注释的元素必须是一个数字，其值必须在可接受的范围内
 @Past  被注释的元素必须是一个过去的日期
 @Future  被注释的元素必须是一个将来的日期
 @Pattern(value) 被注释的元素必须符合指定的正则表达式。
 @Email 被注释的元素必须是电子邮件地址
 @Length 被注释的字符串的大小必须在指定的范围内
 @NotEmpty  被注释的字符串必须非空
 @Range  被注释的元素必须在合适的范围内
 @NotBlank  只应用于字符串且在比较时会去除字符串的首位空格

 @JsonIgnore   忽略属性或者方法
 @JsonProperty  不忽略方法，只能作用于方法

 @Size (min=3, max=20, message="用户名长度只能在3-20之间")
 @Length(min = 5, max = 20, message = "用户名长度必须位于5到20之间")
 @Email(message = "比如输入正确的邮箱")
 @NotNull(message = "用户名称不能为空")
 @Max(value = 100, message = "年龄不能大于100岁")
 @Min(value= 18 ,message= "必须年满18岁！" )
 @AssertTrue(message = "bln4 must is true")
 @AssertFalse(message = "blnf must is falase")
 @DecimalMax(value="100",message="decim最大值是100")
 @DecimalMin(value="100",message="decim最小值是100")
 @Pattern(regexp="^(\d{18,18}|\d{15,15}|(\d{17,17}[x|X]))$", message="身份证格式错误")
 */
@Entity
public class User {

    //id自动生成，并且自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @NotBlank
    private String name;

    @Range(min = 1, max = 150, message = "年龄必须在1-150")
    private Integer age;

    @NotBlank
    private  String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //返回的json不包含password字段
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    //post请求接受password字段
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
