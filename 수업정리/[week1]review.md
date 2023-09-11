# [WEEK 1] REVIEW


> ## REST API 설계 [[참고]](https://www.freecodecamp.org/news/rest-api-best-practices-rest-endpoint-design-examples/)
 * **Use JSON as the Format for Sending and Receiving Data**

 * **Use Nouns Instead of Verbs in Endpoints**
    * HTTP methods (GET, POST, PUT, PATCH, and DELETE) are already in verb form for performing basic CRUD (Create, Read, Update, Delete) operations.
   ```  
   EX) http://mysite.com/getPosts (X)   
      -> https://mysite.com/posts (O)  
   ```

 * **Name Collections with Plural Nouns**
   ```
   EX) http://mysite.com/post/123 (X)   
      -> https://mysite.com/posts/123 (O)
   ```
 * **Use Status Codes in Error Handling**

    |STATUS CODE RANGE|MEANING|
    |:----------------:|:-----:|
    | 100 – 199 | Informational Responses |
    | 300 – 399 | Redirects |
    | 400 – 499 | Client-side errors |
    | 500 – 599 | Server-side errors |


 * **Use Nesting on Endpoints to Show Relationships**
    * You should avoid nesting that is more than 3 levels deep as this can make the API less elegant and readable.
   ```
   EX) https://mysite.com/posts/postId/comments
   ```

 * **Use Filtering, Sorting, and Pagination to Retrieve the Data Requested**   
   ```
   EX) https://mysite.com/posts?tags=javascript
   ```

 * **Use SSL for Security**   
   ```
   EX) https://mysite.com/posts runs on SSL.   
       http://mysite.com/posts does not run on SSL.
   ```

 * **Be Clear with Versioning**   
   ```
   EX) https://mysite.com/v1/ for version 1  
       https://mysite.com/v2 for version 2
   ```

 * **Provide Accurate API Documentation**    
    * The documentation should contain:   
        1. relevant endpoints of the API
        2. example requests of the endpoints   
        3. implementation in several programming languages   
        4. messages listed for different errors with their status codes


<br>
<br>


> ## Controller 작성 & 설계
1. **Model과 View 사이를 이어주는 인터페이스 역할.**   
즉, Model이 데이터를 어떻게 처리할지 알려주는 역할을 수행. 사용자로부터 View에 요청이 있으면 Controller는 해당 업무를 수행하는 Model을 호출하고 Model이 업무를 모두
수행하면 다시 결과를 View에 전달하는 역할을 함.
<br>
<br>
* **@Controller 와 @RestController의 차이**

|@Controller|@RestController|
|:----------:|:------------:|
|@Controller is used to mark classes as Spring MVC Controller.|@RestController annotation is a special controller used in RESTful Web services, and it’s the combination of @Controller and @ResponseBody annotation.|
|It is a specialized version of @Component annotation.|It is a specialized version of @Controller annotation.|
|In @Controller, we can return a view in Spring Web MVC.|In @RestController, we can not return a view.|
|@Controller annotation indicates that the class is a “controller” like a web controller.|@RestController annotation indicates that class is a controller where @RequestMapping methods assume @ResponseBody 
|In @Controller, we need to use @ResponseBody on every handler method.|In @RestController, we don’t need to use @ResponseBody on every handler method.|



