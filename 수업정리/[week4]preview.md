# [WEEK 4] PREVIEW

#### YC Tech Academy
---
---

# Spring Security

### 인증(Authentication)과 인가(Authorization)
* <b>인증(Authentication)</b>  
해당 사용자가 본인이 맞는지를 확인하는 절차
* <b>인가(Authorization)</b>  
인증된 사용자가 요청된 자원에 접근 가능한지를 결정하는 절차


### 인증 방식
1. <b>credential 방식</b>  
username, password을 이용하는 방식
2. <b>이중 인증 방식 (two factor 인증)</b>  
사용자가 입력한 개인정보를 인증 후, 다른 인증 체계를 이용하여 두 가지의 조합으로 인증하는 방식
3. <b>하드웨어 인증</b>  
자동차 키와 같은 하드웨어로 인증하는 방식

> 이 중 Spring Security는 credential 기반의 인증방식을 취한다.

* principal : 아이디 (username)  
* credential : 비밀번호 (password)

> 특정 자원에 대한 접근을 제어하기 위해서는 권한을 가지게 된다.  
> 특정 권한을 얻기 위해서는 유저는 <b>인증(Authentication)</b>이 필요하고, 관리자는 해당 정보를 참고하여 권한을 <b>인가(Authorization)</b>한다.  
> Spring Security는 principal - credential 패턴을 가진다.


### Spring Security의 특징
* Filter 기반으로 동작한다.  
Spring MVC와 분리되어 관리하고 동작할 수 있다.
* Bean으로 설정할 수 있다.  


### Spring Security 구조
![Spring Security 구조](https://blog.kakaocdn.net/dn/CCMVm/btrmbnuMX7O/HZU3Lz0zDcihSJ31oSrMwK/img.png)

#### 1. HTTP REQUEST 수신
사용자가 로그인 정보와 함께 인증 요청을 한다.

#### 2. 유저 자격을 기반으로 인증 토큰 생성
AuthenticationFilter가 요청을 수신받아 요청 정보를 통해 UsernamePasswordAuthenticationToken의 인증용 객체를 생성한다.

#### 3. Filter을 통해 AuthenticationToken을 AuthenticationManager로 위임
AuthenticationManager의 구현체인 ProviderManager에게 생성한 UsernamePasswordToken 객체를 전달한다.

#### 4. AuthenticationProvider의 목록으로 인증 시도
AuthenticationManager는 등록된 AuthenticationProvider들을 조회하며 인증을 요구한다.

#### 5. UserDetailsService의 요구
실제 데이터베이스에서 사용자 인증정보를 가져오는 UserDetailsService에 사용자 정보를 넘겨준다.

#### 6. UserDetails을 이용해 User 객체에 대한 정보 탐색
넘겨받은 사용자 정보를 통해 데이터베이스에서 찾아낸 사용자 정보인 UserDetails 객체를 만든다.

#### 7. User 객체의 정보들을 UserDetails가 UserDetailsService(LoginService)로 전달
AuthenticationProvider들은 UserDetailsService을 통해 UserDetails을 넘겨받고 사용자 정보를 비교한다.

#### 8. 인증 객체 or AuthenticationException
인증이 완료되면 권한 등의 사용자 정보를 담은 Authentication 객체를 반환한다.

#### 9. 인증 끝
다시 최초의 AuthenticationFilter에 Authentication 객체가 반환된다.

#### 10. SecurityContext에 인증 객체를 설정
Authentication 객체를 Security Context에 저장한다.  
<br>
<br>
![](https://velog.velcdn.com/images/hope0206/post/a3748ea3-ef78-4a09-b746-f02e48fe587b/image.png)
- 최종적으로 SecurityContextHolder는 세션영역에 있는 SecurityContext에 Authentication 객체를 저장한다.    
- 사용자가 정보를 저장한다는 것은 Spring Security가 전통적인 세션-쿠키 기반의 인증 방식을 사용한다는 것을 의미한다.  
- Authentication 클래스는 현재 접근하는 주체의 정보와 권한을 담는 인터페이스고 SecurityContext 저장되며 SecurityContextHolder를 통해 SecurityContext에 접근하고, SecurityContext를 통해 Authentication에 접근할 수 있다.


-----------
### Authentication
- 현재 접근하는 주체의 정보와 권한을 담는 인터페이스이다. Authentication 객체는 SecurityContext에 저장되며,
SecurityContextHolder를 통해 SecurityContext에 접근하고, SecurityContext를 통해 Authentication에 접근할 수 있다.


```JAVA
public interface Authentication extends Principal, Serializable {
	// 현재 사용자의 권한 목록을 가져옴
	Collection<? extends GrantedAuthority> getAuthorities();
    
	// credentials(주로 비밀번호)을 가져옴
	Object getCredentials();
    
	Object getDetails();
 
	// Principal 객체를 가져옴
	Object getPrincipal();
 
	// 인증 여부를 가져옴
	boolean isAuthenticated();
    
	// 인증 여부를 설정함
	void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
 
}
```
---------

### UsernamePasswordAuthenticationToken
- UsernamePasswordAuthenticationToken은 Authentication을 implements한 AbstractAuthenticationToken의 하위 클래스로, User의 ID가 Principal 역할을 하고, Password가 Credential의 역할을 한다. 
- UsernamePasswordAuthenticationToken의 첫 번째 생성자는 인증 전의 객체를 생성하고, 두번째는 인증이 완료된 객체를 생성한다.

```JAVA
public abstract class AbstractAuthenticationToken implements Authentication, CredentialsContainer {
}
 
public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {
 
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
 
	// 주로 사용자의 ID에 해당
	private final Object principal;
 
	// 주로 사용자의 PW에 해당
	private Object credentials;
 
	// 인증 완료 전의 객체 생성
	public UsernamePasswordAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}
 
	// 인증 완료 후의 객체 생성
	public UsernamePasswordAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); // must use super, as we override
	}
}
```
-------
### AuthenticationManager
- 인증에 대한 부분은 AuthenticationManager를 통해서 처리하게 되는데, 실질적으로는 AuthenticationManager에 등록된 AuthenticationProviders에 의해 처리된다. 
- 인증에 성공하면 두번째 생성자를 이용해 객체를 생성하여 SecurityContext에 저장한다.

```JAVA
public interface AuthenticationManager {
 
	Authentication authenticate(Authentication authentication) throws AuthenticationException;
 
}
```
--------
### AuthenticationProvider
- AuthenticationProvider에서는 실제 인증에 대한 부분을 처리하는데, 인증 전의 Authentication 객체를 받아서 인증이 완료된 객체를 반환하는 역할을 한다. 
- 아래와 같은 인터페이스를 구현해 Custom한 AuthenticationProvider를 작성하고 AuthenticationManager에 등록하면 된다.

```JAVA
public interface AuthenticationProvider {
 
	Authentication authenticate(Authentication authentication) throws AuthenticationException;
 
	boolean supports(Class<?> authentication);
 
}
```
---------
### ProviderManager
- AuthenticationManager를 implements한 ProviderManager는 AuthenticationProvider를 구성하는 목록을 갖는다.
```JAVA
public class ProviderManager implements AuthenticationManager, MessageSourceAware, InitializingBean {
	
    public List<AuthenticationProvider> getProviders() {
		return this.providers;
	}
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Class<? extends Authentication> toTest = authentication.getClass();
		AuthenticationException lastException = null;
		AuthenticationException parentException = null;
		Authentication result = null;
		Authentication parentResult = null;
		int currentPosition = 0;
		int size = this.providers.size();
        
        // for문으로 모든 provider를 순회하여 처리하고 result가 나올때까지 반복한다.
		for (AuthenticationProvider provider : getProviders()) { ... }
	}
}
```
-------
### UserDetailsService
- UserDetailsService는 UserDetails 객체를 반환하는 하나의 메소드만을 가지고 있는데, 일반적으로 이를 implements한 클래스에 UserRepository를 주입받아 DB와 연결하여 처리한다.
```JAVA
public interface UserDetailsService {
 
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
 
}
```
----------
### UserDetails
- 인증에 성공하여 생성된 UserDetails 객체는 Authentication객체를 구현한 UsernamePasswordAuthenticationToken을 생성하기 위해 사용된다. 
- UserDetails를 implements하여 처리할 수 있다.

```JAVA
public interface UserDetails extends Serializable {
 
	// 권한 목록
	Collection<? extends GrantedAuthority> getAuthorities();
 
	String getPassword();
 
	String getUsername();
 
	// 계정 만료 여부
	boolean isAccountNonExpired();
 
	// 계정 잠김 여부
	boolean isAccountNonLocked();
 
	// 비밀번호 만료 여부
	boolean isCredentialsNonExpired();
 
	// 사용자 활성화 여부
	boolean isEnabled();
 
}
```
--------
### SercurityContextHolder
- SecurityContextHolder는 보안 주체의 세부 정보를 포함하여 응용프로그램의 현재 보안 컨텍스트에 대한 세부 정보가 저장된다.
--------
### SecurityContext
- Authentication을 보관하는 역할을 하며, SecurityContext를 통해 Authentication을 저장하거나 꺼내올 수 있다.
```JAVA
SecurityContextHolder.getContext().setAuthentication(authentication);
SecurityContextHolder.getContext().getAuthentication(authentication);

```
--------
### GrantedAuthority
- GrantedAuthority는 현재 사용자(Principal)가 가지고 있는 권한을 의미하며, ROLE_ADMIN이나 ROLE_USER와 같이 ROLE_*의 형태로 사용한다. 
- GrantedAuthority 객체는 UserDetailsService에 의해 불러올 수 있고, 특정 자원에 대한 권한이 있는지를 검사하여 접근 허용 여부를 결정한다.

---------------------
---------------------
[참고1](https://velog.io/@hope0206/Spring-Security-%EA%B5%AC%EC%A1%B0-%ED%9D%90%EB%A6%84-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%97%AD%ED%95%A0-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0)  
[참고2](https://dev-coco.tistory.com/174)