# [WEEK 4] PREVIEW

#### YC Tech Academy
---
---

# OAuth 2.0

<br>

<br>

### OAuth 이란?
```HTML
OAuth("Open Authorization")는 인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단으로서 사용되는, 접근 위임을 위한 개방형 표준이다. <위키백과>
```

>	<b><GitHub을 통한 OAuth 2.0 예시></b>  
	GitHub은 OAuth 2.0을 지원하여 사용자가 GitHub 계정을 사용하여 다른 애플리케이션에 로그인하고 권한을 부여할 수 있다.


<br>

<br>


### OAuth 2.0 주체
* <b>Resource Owner</b>  
리소스 소유자.   
우리의 서비스를 이용하면서, 구글, 페이스북 등의 플랫폼에서 리소스를 소유하고 있는 사용자이다. 리소스라 하면 '구글 캘린더 정보', '페이스북 친구 목록', '네이버 블로그 포스트 작성' 등이 있다.

* <b>Client</b>  
보호된 자원을 사용하려고 접근 요청을 하는 애플리케이션
* <b>Authorization Server</b>  
권한 서버. 인증/인가를 수행하는 서버로 클라이언트의 접근 자격을 확인하고 Access Token을 발급하여 권한을 부여하는 역할을 수행한다.
* <b>Resource Server</b>  
사용자의 보호된 자원을 호스팅하는 서버

<br>

<br>

### 인증 방식 및 동작 과정

<b> * Authorization Code Grant / 권한 부여 코드 승인 방식</b>

![권한 부여 코드 승인 방식](https://hudi.blog/static/7dced69214d91d7f1f0892720b1b5e1b/9afce/oauth2.0-process.png)

<b> 1. ~ 2. 로그인 요청 </b> 

Resource Owner가 우리 서비스의 '구글로 로그인하기' 등의 버튼을 클릭해 로그인을 요청한다. Client는 OAuth 프로세스를 시작하기 위해 사용자의 브라우저를 Authorization Server로 보내야한다.  

클라이언트는 이때 Authorization Server가 제공하는 Authorization URL에 response_type , client_id , redirect_uri , scope 등의 매개변수를 쿼리 스트링으로 포함하여 보낸다.

```HTML
response_type : 반드시 code 로 값을 설정해야한다. (참고) 인증이 성공할 경우 클라이언트는 후술할 Authorization Code를 받을 수 있다.
client_id : 애플리케이션을 생성했을 때 발급받은 Client ID
redirect_uri : 애플리케이션을 생성할 때 등록한 Redirect URI
scope : 클라이언트가 부여받은 리소스 접근 권한.
```

<br>

<b> 3. ~ 4. 로그인 페이지 제공, ID/PW 제공  </b>

클라이언트가 빌드한 Authorization URL로 이동된 Resource Owner는 제공된 로그인 페이지에서 ID와 PW 등을 입력하여 인증할 것 이다.

<br>

<b> 5. ~ 6. Authorization Code 발급, Redirect URI로 리디렉트 </b> 

인증이 성공되었다면, Authorization Server 는 제공된 Redirect URI로 사용자를 리디렉션시킬 것 이다. 이때, Redirect URI에 Authorization Code를 포함하여 사용자를 리디렉션 시킨다.

이때, Authorization Code란 Client가 Access Token을 획득하기 위해 사용하는 임시 코드이다. 이 코드는 수명이 매우 짧다.
>Authorization Code가 필요한 이유  
: Redirect URI를 통해 Authorization Code를 발급하는 과정이 생략된다면, Authorization Server가 Access Token을 Client에 전달하기 위해 Redirect URI를 통해야 한다. 이때, Redirect URI를 통해 데이터를 전달할 방법은 URL 자체에 데이터를 실어 전달하는 방법밖에 존재하지 않는다. 브라우저를 통해 데이터가 곧바로 노출되는 것이다.  
하지만, Access Token은 민감한 데이터이다. 이렇게 쉽게 노출되어서는 안된다. 이런 보안 사고를 방지 Authorization Code를 사용하는 것 이다.

<br>

<b> 7. ~ 8. Authorization Code와 Access Token 교환  </b>

Client는 Authorization Server에 Authorization Code를 전달하고, Access Token을 응답받는다. Client는 발급받은 Resource Owner의 Access Token을 저장하고, 이후 Resource Server에서 Resource Owner의 리소스에 접근하기 위해 Access Token을 사용한다.

Access Token은 유출되어서는 안된다. 따라서 제 3자가 가로채지 못하도록 HTTPS 연결을 통해서만 사용될 수 있다.

Authorization Code와 Access Token 교환은 token 엔드포인트에서 이루어진다. 아래는 token 엔드포인트에서 Access Token을 발급받기 위한 HTTP 요청의 예시이다. 이 요청은 application/x-www-form-urlencoded 의 형식에 맞춰 전달해야한다.

```HTML
grant_type : 항상 authorization_code 로 설정되어야 한다. (참고)
code : 발급받은 Authorization Code
redirect_uri : Redirect URI
client_id : Client ID
client_secret : RFC 표준상 필수는 아니지만, Client Secret이 발급된 경우 포함하여 요청해야한다.
```

<br>

<b> 9. 로그인 성공</b>

위 과정을 성공적으로 마치면 Client는 Resource Owner에게 로그인이 성공하였음을 알린다.

<br>

<b> 10. ~ 13. Access Token으로 리소스 접근  </b>

이후 Resource Owner가 Resource Server의 리소스가 필요한 기능을 Client에 요청한다. Client는 위 과정에서 발급받고 저장해둔 Resource Owner의 Access Token을 사용하여 제한된 리소스에 접근하고, Resource Owner에게 자사의 서비스를 제공한다.


<b> * Implicit Grant / 암묵적 승인 방식</b>
![암묵적 승인 방식](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FtgjgS%2Fbtr5fNuAYEs%2Fgwf8HLqsOeuvQqAV1vJ000%2Fimg.jpg)

자격 증명을 안전하게 저장하기 힘든 클라이언트 사이드에서의 OAuth2 인증에 최적화된 방식으로 response_type=token의 형식으로 요청된다.

 

암묵적 승인 방식은 권한 부여 코드(Authorization Code) 발급 과정 없이 바로 액세스 토큰이 발급된다.

access token이 URI를 통해 바로 전달되기 때문에 만료 기간을 짧게 설정해야 한다는 특징이 있다.

해당 방식은 refresh token의 사용이 불가능한 방식이며, 이 방식에서 Authorization Server는 client_secret을 사용해 클라이언트를 인증하지 않는다.

access token을 획득하기 위한 절차가 간소화되기 때문에 응답성과 효율성은 높아지지만, access token이 uri로 전달되는 보안적인 측면에서의 단점이 있다.

<br>

1. 권한 부여 승인 요청 시 response_type을 token으로 설정하여 요청하면, 클라이언트는 권한 서버에서 제공하는 로그인 페이지를 브라우저에 띄워 출력한다.
2. 해당 페이지를 통해 사용자가 로그인을 하면 Authorization Server는 권한 부여 승인 요청 시 전달받은 redirect_uri로 Authorization Code가 아닌 access token을 전달하게 된다.


<br>

<br>

<b> * Resource Owner Password Credentials Grant / 자원 소유자 자격 증명 방식</b>
![자원 소유자 자격 증명 방식](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FSVT9t%2Fbtr5dSDeHRG%2FnTkgszrvxHv2626a976M6K%2Fimg.jpg)

username, password로만 access token을 발급받는 방식으로 grant_type=password의 형태로 요청한다.

이 방식은 권한 서버, 리소스 서버, 클라이언트가 모두 같은 시스템에 속해 있을 때만 사용할 수 있는 방식이며, 해당 방식에서는 refresh token을 사용할 수 있다.

username, password를 통해 바로 access token을 발급받는 간단한 로직

<br>

<br>

<b> * Client Credentials Grant / 클라이언트 자격 증명 방식</b>
![클라이언트 자격 증명 방식](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F8fXqm%2Fbtr5e5ChYGD%2FcWrCm3oiWsjhgDVPTHFSJ1%2Fimg.jpg)

클라이언트의 자격 증명만으로 access token을 획득하는 방식으로 grant_type=client_credentials 형식으로 요청한다.

User가 아닌 Client(서비스 또는 애플리케이션)에 대한 인가(Authorization)가 필요할 때 사용되는 방식이다.

OAuth2의 권한 부여 방식 중 가장 간단하며, 클라이언트 자신이 관리하는 리소스 혹은 권한 서버에 해당 클라이언트를 위한 제한된 리소스 접근 권한이 설정되어 있는 경우에 사용된다.

이 방식은 자격 증명을 안전하게 보관할 수 있는 클라이언트에서만 사용되어야 하며, refresh token은 사용할 수 없다.

---------------------
---------------------
[참고1](https://wildeveloperetrain.tistory.com/247)  
[참고2](https://hudi.blog/oauth-2.0/)  
[참고3](https://blog.naver.com/mds_datasecurity/222182943542)