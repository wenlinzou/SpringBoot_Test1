启动运行springboot

1可以在目录下运行命令：`mvn spring-boot:run`

 2mvn install 然后到target里面运行

`java -jar speaker-platform-service-1.0-SNAPSHOT.jar --spring.profiles.active=test`



  项目配置-端口和上下文

```
server.port=8081
server.context-path=/myxxx
```



参数使用

```
girl.age: 18
girl.size: L  
```

配置里面使用配置属性

```
content: "size: ${girl.size}, age: ${girl.age}" 
```

使用bean配置（放置类中）

```
@Componet
@ConfigurationProperties(prefix = "girl")//前缀
public class GirlBean{    
	private Integer age;    
	private String size;    //Geter Seter

} 
```

使用

```
@Autowired
private GirlBean girlBean;  
```



多环境配置：

`application.yml` `application-dev.yml` `application-prod.yml`

```
spring:    
	profiles:        
		active: dev #动态
```

`java -jar myApp.jar --spring.profiles.active=dev`  



Controller的使用

`@RestController`等同于`@Controller`和`@ResponseBody`

支持两个路径访问

```
//方法
@RequestMapping(value = {"/context1", "/context2"}, method = RequestMethod.GET) 
```



获取参数

| @PathVariable | 获取url中的数据  |
| ------------- | ---------------- |
| @RequestParam | 获取请求参数的值 |
| @GetMapping   | 组合注解         |



```
//http:ip:port/xx/123
@GetMapping(value = "/xx/{id}")
public String test(@PathVariable("id") String id) {    
	return id;
} 

//http://ip:port/xx?id=123
@GetMapping(value = "/xx")
public String test(@RequestParam("id") String id) {    
	return id;
}

//可以设置默认值，是否必传
@GetMapping(value = "/xx")
public String test(@RequestParam(value ="id", required = false, defaultValue = "123") String id) {    
	return id;
}  
```



数据库

修改pom.xml

```
<dependency>        
	<groupId>org.springframework.boot</groupId>        
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>        
	<groupId>mysql</groupId>        
	<artifactId>mysql-connector-java</artifactId>
</dependency> 
```

修改`application.yml`

```
spring:    
	profiles:        
		active: dev    
	datasource:        
		driver-class-name: com.mysql.jdbc.Driver        
		url: jdbc:<mysql://127.0.0.1:3306/db>        
		username: root        
		password: 123456    
	jpa:        
		hibernate:            
			ddl-auto: update        
			show-sql: true
```

| update      | 常用                                   |
| ----------- | -------------------------------------- |
| create      | 运行时自动创建表(每次都先删除后创建表) |
| create-drop | 运行结束时，删除表                     |
| none        | 什么都不做                             |
| validate    | 验证类的属性和表是否一致               |



getOne报错：

```
com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: com.mooc.demo.config.entity.Girl_$$_jvstce5_0["handler"]) 
```

解决方法：

`@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })`  



数据事务

确保方法是public，引擎是InnoDB，如果是MyISAM事务是不起作用

`import org.springframework.transaction.annotation.Transactional;` 

```
import org.springframework.transaction.annotation.Transactional; 

@Servicepublic class GirlService {        

@Autowired    

private GirlMapper girlMapper;     

@Transactional(rollbackFor={Exception.class})    

public void initTwo() {    

}

}
```

  表单验证@Valid

实体对象加上注解

```
@Min(value = 18, message = "未成年少女禁止入内")

private Integer age; 
```

controller上方法上加上@Validate注解，加上BindingResult用于返回提示信息

```
@PostMapping(value = "/save")

public Girl save(@Validated Girl girl, BindingResult bindingResult) {    

if (bindingResult.hasErrors()) {        

System.out.println(bindingResult.getFieldError().getDefaultMessage());        

return null;    }

}
```

  AOP统一处理请求日志

pom.xml

```
<!--AOP切面-->
<dependency>    
	<groupId>org.springframework.boot</groupId>    
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

 拦截单个方法

`@Before("execution(public * com.mooc.demo.controller.GirlController.getList(..))")`

拦截类下所有方法

`@Before("execution(public * com.mooc.demo.controller.GirlController.*(..))")`

```
@Aspect
@Component
public class HttpAspect {     
	@Before("execution(public * com.mooc.demo.controller.GirlController.*(..))")    
	public void doBefore() {        
		System.out.println(111);    
	}     
	
	@After("execution(public * com.mooc.demo.controller.GirlController.*(..))")    
	public void doAfter() {        
		System.out.println(222);    
	}
} 
```



简化代码

```
@Aspect
@Component
public class HttpAspect {     
	@Pointcut("execution(public * com.mooc.demo.controller.GirlController.*(..))")    
	public void log() {    
	}     

	@Before("log()")    
	public void doBefore() {        
		System.out.println(111);    
	}     
	
	@After("log()")    
	public void doAfter() {        
		System.out.println(222);    
	}
}  
```





统一异常处理

1自定义一个异常捕获的Handle

```
@ControllerAdvice
public class ExceptionHandle {    
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);    
	/**     
	 * 声明要捕获的异常    
	 * @return     
	 */    
	@ExceptionHandler(value = Exception.class)    
	@ResponseBody    
	public Result handle(Exception e) {        
		if (e instanceof GirlException) {            
			GirlException girlException = (GirlException) e;            
			return ResultUtil.error(girlException.getCode(), 				girlException.getMessage());
		} else {            
			logger.error("[系统异常]{}", e);            
			return ResultUtil.error(-1, "未知错误");        
		}    
	}
} 
```



2自定义一个自己的异常

```
public class GirlException extends RuntimeException {     
	private Integer code;        
	public GirlException(ResultEnum resultEnum) {        
		super(resultEnum.getMessage());        
		this.code = resultEnum.getCode();    
	}     
	public Integer getCode() {        
		return code;    
	}     
	public void setCode(Integer code) {        
		this.code = code;    
	}
} 
```



3在Service中抛出自己定义的异常

```
public void getAge(Integer id) {    
	Optional<Girl> optional = girlMapper.findById(id);     
	Girl girl = optional.orElseGet(Girl::new);    
	Integer age = girl.getAge();    
	if (age < 10) {                
		//返回你还在上小学吧        
		throw new GirlException(ResultEnum.PRIMARY_SCHOOL;    
	} else if (age > 10 && age < 16) {        
		//返回你可能在上初中        
		throw new GirlException(ResultEnum.MIDDLE_SCHOOL);    
	}
} 
```



4定义枚举

```
public enum ResultEnum {    
	UNKOWN_ERROR(-1, "未知错误")    
	, SUCCESS(0, "成功")    
	, PRIMARY_SCHOOL(100, "你还在上小学吧")    
	, MIDDLE_SCHOOL(101, "你可能在上初中")    ;     
	private Integer code;    
	private String message;     
	ResuleEnum(Integer code, String message) {        
		this.code = code;        
		this.message = message;    
	}     
	public Integer getCode() {        
		return code;    
	}     
	public String getMessage() {        
		return message;    
	} 
}  
```



单元测试

1Service方法测试在test包下新建GirlServiceTest（单元测试GirlService中findOne方法）{备注：在方法上右击选择goto然后选择Test，最后选择要单元测试的方法}

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {     
	@Autowired    
	private GirlService girlService;     
	@Test    
	public void findOneTest() {        
		Girl girl = girlService.findOne(23);        
		//断言        
		Assert.assertEquals(new Integer(25), girl.getAge());    
	}
} 
```



2Controller方法，单元测试

```
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GirlControllerTest {     
	@Autowired    
	private MockMvc mockMvc;     
	@Test    
	public void getList() throws Exception {        
		//状态码返回200        
		//期望返回的内容        
		mockMvc.perform(MockMvcRequestBuilders.get("/getList"))                
			.andExpect(MockMvcResultMatchers.status().isOk())                
			.andExpect(MockMvcResultMatchers.content().string("abc"));    
	}
}
```



```
Expected :abc            {实际值}
Actual   :[{"id"}]        {期望值}  
```

打包时会自动单元测试命令 `mvn clean package`

可见控制台输出

```
[INFO] Results:
[INFO]
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0  
```

打包时跳过单元测试命令 `mvn clean package -Dmaven.test.skip=true`     