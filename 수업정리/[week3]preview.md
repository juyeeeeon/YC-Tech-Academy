# [WEEK 3] PREVIEW

#### YC Tech Academy
---
---

# RDBMS

## 테이블들의 연관 관계
### SQL - Unique Key
* 테이블의 열에서 중복된 값을 허용하지 않는다.
* primary key와 유사하지만, unique key는 NULL값을 허용한다.
* 테이블에서 여러 개 생성 가능하다.

```SQL
CREATE TABLE table_name(
   column1 datatype UNIQUE KEY,
   column2 datatype,
   .....
   .....
   columnN datatype
);
```

```SQL
CREATE TABLE CUSTOMERS (
   ID INT NOT NULL UNIQUE KEY,
   NAME VARCHAR(20) NOT NULL,
   AGE INT NOT NULL,
   ADDRESS CHAR (25),
   SALARY DECIMAL (18, 2)
);
```

### SQL - Primary Key (기본 키)
* 각 행을 구분하는 유일한 열이다.
* 값의 중복을 허용하지 않는다.
* 즉, 식별키이다.
* NULL값을 허용하지 않는다.
* 테이블에서 여러 열이 존재하지만 기본 키는 한 개만 생성 가능하다.
* EX) 학생의 학번, 사원의 사번 등

```SQL
CREATE TABLE table_name(
   column1 datatype,
   column2 datatype,
   column3 datatype,
   .....
   columnN datatype,
   PRIMARY KEY(column_name)
);
```

```SQL
CREATE TABLE CUSTOMERS (
   ID INT NOT NULL,
   NAME VARCHAR (20) NOT NULL,
   AGE INT NOT NULL,
   ADDRESS CHAR (25),
   SALARY DECIMAL (18, 2),       
   PRIMARY KEY (ID)
);
```

- Primary Key와 Unique Key 차이점

|Primary Key|Unique Key|
|:---:|:---:|
|테이블에 반드시 하나만 존재|테이블에 여러 개 존재 가능|
|NULL 허용하지 않음|NULL 허용 가능|
|데이터 무결성 보장|데이터 무결성 보장(NULL은 여러 개 존재 가능)|

### SQL - Foreign Key
* 두 테이블 사이의 관계를 연결해줌
* 데이터의 무결성을 보장해주는 역할
* 외래 키가 설정된 열은 꼭 다른 테이블의 기본 키와 연결됨
* 기본키가 있는 테이블을 기준 테이블, 외래키가 있는 테이블을 참조 테이블이라 부름

```SQL
CREATE TABLE table_name (
    column1 datatype,
    column2 datatype,
    ...
    CONSTRAINT fk_name 
	FOREIGN KEY (column_name) 
	REFERENCES referenced_table(referenced_column)
);
```

```SQL
CREATE TABLE CUSTOMERS(
   ID INT NOT NULL,
   NAME VARCHAR (20) NOT NULL,
   AGE INT NOT NULL,
   ADDRESS CHAR (25) ,
   SALARY DECIMAL (18, 2),       
   PRIMARY KEY (ID)
);
```


## indexing

### SQL - Indexes
* 테이블의 조회를 빠르게 도와주는 자료구조
* 데이터의 위치를 빠르게 찾아주는 역할
* 책 뒷편의 색인과 같은 역할
* index 정보 추가비용 소모
* 따라서 UPDATE, INSERT, DELETE의 속도는 느려진다는 단점

```SQL
CREATE UNIQUE INDEX index_name
on table_name (column_name);
```


### SQL - Create Index
* index 생성
```SQL
CREATE INDEX index_name 
ON table_name (column_name1, column_name2,... column_nameN);
``` 
- index_name : 만들고자 하는 index의 이름
- table_name : 만들고자 하는 index의 테이블의 이름
- (column_name1, column_name2,…column_nameN) : index를 만드려는 열들의 이름

<br>
<br>

```SQL
CREATE TABLE CUSTOMERS(
   ID INT NOT NULL,
   NAME VARCHAR(15) NOT NULL,
   AGE INT NOT NULL,
   ADDRESS VARCHAR(25),
   SALARY DECIMAL(10, 4),
   PRIMARY KEY(ID));
```

```SQL
CREATE INDEX index_name ON CUSTOMERS(NAME);
```

### SQL - Drop Index
* index 삭제

```SQL
DROP INDEX index_name ON table_name;
```
- index_name : 삭제하려는 index의 이름
- table_name : index가 연관되어 있는 테이블의 이름

<br>
<br>

```SQL
DROP INDEX INDEX_NAME ON CUSTOMERS;
```

### SQL - Show Indexes
* index 조회

```SQL
SHOW INDEX FROM table_name;
```
<br>
<br>

```SQL
SHOW INDEX FROM CUSTOMERS;
```

### SQL - Unique Indexes
* index가 걸려있는 열에 중복될 수 없는 유일한 값만을 보장한다.
* NULL 허용한다.


```SQL
CREATE UNIQUE INDEX index_name
ON table_name (column1, column2, ..., columnN);
```
- index_name : 생성하려는 index의 이름
- table_name : index을 만드려는 테이블의 이름
- (column1, column2, …, columnN) : unique index가 만들어지는 행들의 이름

<br>
<br>

```SQL
CREATE UNIQUE INDEX UNIQUE_ID ON CUSTOMERS (NAME);
```

## Transaction
### SQL - Transactions
* 데이터베이스의 상태를 변화시키기 위해 수행하는 작업단위

#### Transactions의 특징
- 원자성(Atomicity) : 작업단위 안의 모든 동작이 반영되거나 반영되지 않아야 한다.
- 일관성(Consistency) : 작업처리가 항상 일관성이 있어야 한다.
- 독립성(Isolation) : 둘 이상의 transactions이 진행될 때 각각의 transaction이 서로에 영향을 미쳐서는 안된다.
- 지속성(Durability) : transaction이 수행되면 그 결과가 반영되어야 한다.


### Spring Data JPA 활용
* Spring Data JPA는 Java 기반의 애플리케이션에서 데이터베이스와 상호작용하는 데 도움을 주는 Spring Framework의 일부이다.
* JPA는 Java Persistence API의 약자로, 객체 관계 매핑(ORM, Object-Relational Mapping)을 지원하는 Java 표준 스펙 중 하나

* Repository 인터페이스 제공 : Spring Data JPA는 JpaRepository와 같은 여러 인터페이스를 제공한다. 이 인터페이스를 확장하고 제네릭 타입으로 엔티티 클래스와 식별자 타입을 지정하면, CRUD(Create, Read, Update, Delete) 작업을 위한 메서드를 자동으로 생성해준다.
* 쿼리 메서드 자동 생성 : Spring Data JPA는 메서드 이름을 기반으로 JPQL(Java Persistence Query Language) 쿼리를 자동으로 생성한다. 
   ```java
   public interface myRepository extends JpaRepository<Person, Long> {
      findByFirstNameAndLastName(String firstName, String lastName)
   }
   ```
   와 같은 메서드를 선언하면, 
   ```SQL
   SELECT * FROM Person WHERE first_name = ? AND last_name = ?
   ```
   와 같은 JPQL 쿼리가 생성된다.

   * 스프링 부트와 통합 : Spring Data JPA는 스프링 부트 프로젝트에서 쉽게 설정하고 사용할 수 있도록 지원