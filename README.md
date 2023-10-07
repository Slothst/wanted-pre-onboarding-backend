# wanted-pre-onboarding-backend
프리온보딩 백엔드 인턴십 선발과제

# 요구사항
- 채용공고 CRUD 구현
- 채용공고 검색 기능 구현(선택사항)
- 사용자 채용공고 지원 구현(선택사항)

# 참조사항
- 요구사항을 만족시킨다면 다른 형태의 요청 및 리스폰스 사용 가능
- 필요한 모델 : 회사, 사용자, 채용공고, 지원내역(선택사항)
- 사용자 / 회사 정보는 DB에서 따로 입력
- 로그인 등 사용자 인증절차 생략

# 필수 기술 요건
- ORM 사용하여 구현
- RDBMS 사용

# 구현 과정
1. User, Company, Recruitment Entity 구현
2. User, Company, Recruitment Repository를 Jpa를 상속받아 구현
3. User와 Company 데이터는 MySQL Workbench를 통해 임의로 생성
4. 채용공고 CRUD를 구현할 Recruitment Service 구현
5. 검색 기능을 구현하기 위해 Recruitment Repository에서 Query문을 사용해 구현
6. 이 기능들의 API를 구현하기 위해 Recruitment Controller 구현
7. 채용 공고 지원을 위해 Apply Entity, Repository를 만듦
8. 채용 공고 지원 기능 구현을 위해 Apply Service, Controller 구현
