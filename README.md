## damon-convention
### 基于spring-boot开发的web服务端开发规约包
### 主要功能
>> 统一接口返回
>
>> 统一异常处理
>
>>  参数校验
>
### 待添加功能
>> 统一日志规范
>
>> 丰富工具方法


### 使用方法
* step1 基于spring initializr创建spring boot工程，添加依赖

````  
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>

    <dependency>
        <groupId>com.damon</groupId>
        <artifactId>damon-convention-web</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
````

* step2 使用

````
    @GetMapping("/success")
    public Result<DemoEntity> demoSuccess(){
        return Results.success(new DemoEntity("test"));
    }

    @GetMapping("/fail")
    public Result<DemoEntity> demoFail(){
        throw new BusinessException();
    }



    @GetMapping("/params-fail")
    public Result<DemoEntity> demoParamsFail(@RequestParam(required = false) @NotBlank(message = "参数消息") String param){
        return  Results.success(new DemoEntity("test"));
    }

    @GetMapping("/params-fail2")
    public Result<DemoEntity> demoParamsFail2(@RequestParam(required = false) String param){
        validateService.doSomething(param,null);
        return  Results.success(new DemoEntity("test"));
    }

    @PostMapping("/params-fail3")
    public Result<DemoEntity> demoParamsFail3(@RequestBody @Valid RequestEntity entity){
        validateService.doSomething("teset",null);
        return  Results.success(new DemoEntity("test"));
    }
````