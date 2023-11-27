# [WEEK 7] PREVIEW 

#### YC Tech Academy
---
---

> # Redis
Redis는 오픈 소스의 in-memory data structure store 이다. 주로 데이터를 캐싱하거나 데이터베이스로 사용되며, 다양한 데이터 구조를 지원하여 메모리 내에서 빠르게 데이터를 저장하고 검색할 수 있다. Redis는 Remote Dictionary Server의 약자로, key-value 쌍을 저장하고 이를 효율적으로 관리하는데 중점을 둔 데이터베이스 시스템이다.

1. **인메모리 데이터 스토어**: Redis는 데이터를 메모리에 저장하므로 빠른 읽기와 쓰기 연산이 가능하다. 이 특징은 데이터베이스나 캐시로 사용될 때 효율적이다.

2. **다양한 데이터 구조 지원**: Redis는 Strings, Hashes, Lists, Sets 등 다양한 데이터 구조를 지원한다. 이는 어플리케이션에 따라 적절한 데이터 모델을 선택할 수 있도록 한다.

3. **지속성 및 스냅샷**: Redis는 디스크에 스냅샷을 저장하여 지속성을 제공한다. 이는 데이터의 영속성을 확보하고, 장애 시에도 데이터를 복구할 수 있도록 한다.

4. **고성능**: Redis는 단일 스레드로 동작하며 비동기적인 접근 방식을 사용함으로써 높은 성능을 제공한다. 이는 특히 읽기 연산이 많은 경우에 유용하다.

5. **분산 시스템 지원**: Redis는 마스터-슬레이브 복제를 통해 데이터의 복제와 분산을 지원한다. 이를 통해 고가용성과 확장성을 달성할 수 있다.

6. **트랜잭션 및 원자성 지원**: Redis는 여러 연산을 하나의 트랜잭션으로 묶어 실행하고, 이를 지원하지 않는 다른 연산은 전혀 실행하지 않도록 하는 트랜잭션과 관련된 기능을 제공한다.

7. **Pub/Sub 메시징 패턴**: Redis는 발행-구독(pub/sub) 메시징 패턴을 지원하여 메시지 기반의 통신을 가능하게 한다.

<br>
<br>

> ## Quick Start

* Redis는 데이터베이스, 캐시, 스트리밍 엔진, 메시지 브로커 등으로 사용될 수 있다.   
다음과 같은 특정 목적으로 Redis를 사용하는 방법을 보여준다.

  * 데이터 구조 저장소 ([Redis as an in-memory data structure store](https://redis.io/docs/get-started/data-store/))
  * 문서 데이터베이스 ([Redis as a document database](https://redis.io/docs/get-started/document-database/))
  * 벡터 데이터베이스 ([Redis as a vector database](https://redis.io/docs/get-started/vector-database/))


<br>
<br>


> ### 데이터 구조 저장소 (Redis as an in-memory data structure store)

### 데이터 저장 및 검색
byte arrays와 유사하게 Redis 문자열은 text, serialized objects, counter values 및 binary arrays을 포함하여 sequences of bytes을 저장한다. 다음 예에서는 문자열 값을 설정하고 가져오는 방법을 보여준다.
```_Redis CLI
SET bike:1 "Process 134"
GET bike:1
```
<br>
<br>

Hash는 dictionaries(dicts 또는 hash maps)와 동일하다. 무엇보다도 해시를 사용하여 일반 개체를 나타내고 카운터 그룹을 저장할 수 있다. 다음 예에서는 개체의 필드 값을 설정하고 액세스하는 방법을 설명한다.
```_Redis CLI
> HSET bike:1 model Deimos brand Ergonom type 'Enduro bikes' price 4972
(integer) 4
> HGET bike:1 model
"Deimos"
> HGET bike:1 price
"4972"
> HGETALL bike:1
1) "model"
2) "Deimos"
3) "brand"
4) "Ergonom"
5) "type"
6) "Enduro bikes"
7) "price"
8) "4972"
```
<br>
<br>

### 키스페이스 스캔
Redis 내의 각 항목에는 고유한 키가 있다. 모든 항목은 Redis keyspace 내에 있다 . SCAN 명령을 통해 Redis 키스페이스를 스캔할 수 있다 . 다음은 bike:. 접두사가 있는 처음 100개의 키를 검색하는 예이다.
```_Redis CLI
SCAN 0 MATCH "bike:*" COUNT 100
``` 
SCAN은 커서 위치를 반환하므로 커서 값 0에 도달할 때까지 다음 키 배치를 반복적으로 검색할 수 있다.

<br>
<br>

> ## 데이터 타입([Data Types](https://redis.io/docs/data-types/))
### Strings

### JSON


### Lists


### Sets


### Hashes

<br>
<br>

> ## 데이터와 상호 작용([Interact with data](https://redis.io/docs/interact/))
### 검색 및 쿼리

<br>
<br>

---
---
참고 : https://redis.io/docs/about/


