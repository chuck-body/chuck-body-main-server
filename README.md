# Chuck Body Main Server: 의료 상담 관리 시스템

Spring Boot 기반의 서버 애플리케이션으로, 의사와 환자 간의 의료 상담을 관리하며 음성 파일 분석 및 상담 자동 요약 기능을 제공합니다. 본 시스템은 의사, 환자, 상담 관리를 위한 RESTful API를 제공하고, 외부 AI 서비스와 연동하여 음성 분석을 수행합니다.

이 애플리케이션은 의료 서비스 제공자와 환자 간의 다리 역할을 하며, 의료 상담 기록을 관리하고 상담 음성 녹음을 자동으로 분석 및 요약합니다. Spring Boot의 견고한 아키텍처를 활용하여 안전하고 확장 가능한 API 엔드포인트를 제공하며, 외부 AI 서비스와 연동하여 상담 분석 및 요약 기능을 제공합니다.

## 주요 기능

- 의사, 환자, 상담 관리를 위한 RESTful API 제공
- 비동기 음성 파일 처리 및 분석
- 상담 자동 요약 및 태깅 기능
- 표준화된 API 응답 처리
- 예외 처리 및 로깅 기능
- H2 DB (개발용), Maria DB (운영용) 사용

## 프로젝트 구조

```
chuck-body-main-server/
├── src/                           # 소스 코드 루트 디렉토리
│   ├── main/                      # 메인 애플리케이션 코드
│   │   ├── java/                  # Java 소스 파일
│   │   │   └── com/yunsseong/chuckbodymainserver/
│   │   │       ├── controller/    # 의사, 환자, 상담을 위한 REST API 컨트롤러
│   │   │       ├── domain/        # 도메인 엔티티 (Doctor, Patient, Consultation)
│   │   │       ├── service/       # 비즈니스 로직 서비스
│   │   │       ├── repository/    # 데이터 액세스 계층 인터페이스
│   │   │       ├── event/         # 상담 처리용 이벤트 핸들링
│   │   │       └── common/        # 공통 유틸리티, 설정, 예외 처리
│   │   └── resources/            # 애플리케이션 설정 파일
│   └── test/                     # 테스트 코드
├── gradle/                       # Gradle 래퍼 파일
└── build.gradle                  # Gradle 빌드 설정
```

## 사용 방법

### 사전 준비 사항

- Java Development Kit (JDK) 21
- Gradle 8.5+ (래퍼 사용 가능)
- 웹 서버용 포트 8080 사용 가능
- 음성 파일 처리를 위한 최소 500MB 디스크 공간 확보

### 설치 방법

1. 레포지토리 클론

```bash
git clone https://github.com/chuck-body/chuck-body-main-server
cd chuck-body-main-server
```

2. 애플리케이션 빌드

```bash
# Unix 계열
./gradlew build

# Windows
gradlew.bat build
```

3. 애플리케이션 실행

```bash
# Unix 계열
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

### 스웨거 확인
http://localhost:8080/swagger-ui/index.html

## 데이터 흐름

본 애플리케이션은 의료 상담을 음성 녹음 처리 및 요약 기능과 함께 다단계 파이프라인으로 처리합니다.

```ascii
[클라이언트] -> [REST API] -> [서비스 계층] -> [이벤트 시스템]
                                                      |
[데이터베이스] <- [요약 업데이트] <- [AI 분석] <-+
```

### 구성 요소 간 상호작용

1. 클라이언트가 REST API를 통해 상담 음성 파일 업로드
2. 컨트롤러에서 요청 검증 후 상담 기록 생성
3. 이벤트 시스템에서 음성 파일 비동기 처리
4. 외부 AI 서비스가 음성 콘텐츠 분석
5. 분석 결과로 상담 기록 업데이트 (요약 및 태그 추가)
6. 클라이언트가 처리된 상담 데이터 조회 가능
7. 모든 작업은 상세한 로깅과 함께 추적됨
