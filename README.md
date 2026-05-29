
---

# Week_8 API 명세서

## Auth API

### 1) 회원가입

* **Method**: `POST`
* **URL**: `/api/auth/signup`

#### Request Body

```json
{
  "email": "user@example.com",
  "password": "password123!",
  "name": "홍길동",
  "nickname": "user-1"
}

```

#### Response (성공: 201)

```json
{
  "status": 201,
  "message": "회원가입이 완료되었습니다.",
  "data": {
    "email": "user@example.com",
    "name": "홍길동",
    "nickname": "user-1",
    "createdAt": "2026-05-28T15:40:00"
  }
}

```

#### Response (실패: 400 유효성 검증 실패)

```json
{
  "status": "error",
  "message": "비밀번호는 영문, 숫자를 포함하여 8자 이상이어야 합니다.",
  "code": "VALIDATION_FAILED"
}

```

#### Response (실패: 409 데이터 중복)

```json
{
  "status": "error",
  "message": "이미 존재하는 이메일입니다.",
  "code": "ALREADY_EXIST_EMAIL"
}

```

#### Response (실패: 500 서버 오류)

```json
{
  "status": "error",
  "message": "서버 내부 오류가 발생했습니다.",
  "code": "INTERNAL_SERVER_ERROR"
}

```

---

### 2) 이메일 중복 확인

* **Method**: `GET`
* **URL**: `/api/auth/check-email?email=user@example.com`

#### Response (성공: 200)

```json
{
  "status": "success",
  "message": "사용 가능한 이메일입니다.",
  "data": {
    "isAvailable": true
  }
}

```

#### Response (실패: 400 형식 오류)

```json
{
  "status": "error",
  "message": "이메일 형식이 올바르지 않습니다.",
  "code": "INVALID_EMAIL_FORMAT"
}

```

#### Response (실패: 409 이미 사용 중)

```json
{
  "status": "error",
  "message": "이미 가입된 이메일입니다.",
  "code": "ALREADY_EXIST_EMAIL"
}

```

#### Response (실패: 500 서버 오류)

```json
{
  "status": "error",
  "message": "서버 내부 오류가 발생했습니다.",
  "code": "INTERNAL_SERVER_ERROR"
}

```

---

### 3) 로그인

* **Method**: `POST`
* **URL**: `/api/auth/login`

#### Request Body

```json
{
  "email": "user@example.com",
  "password": "password123!"
}

```

#### Response (성공: 200)

```json
{
  "status": "success",
  "message": "로그인에 성공했습니다.",
  "data": {
    "accessToken": "eyJhbGci0iJIUzI1NiIsInR5c..."
  }
}

```

#### Response (실패: 400 요청 데이터 누락)

```json
{
  "status": "error",
  "message": "이메일과 비밀번호를 모두 입력해 주세요.",
  "code": "VALIDATION_FAILED"
}

```

#### Response (실패: 401 인증 실패)

```json
{
  "status": "error",
  "message": "이메일 또는 비밀번호가 일치하지 않습니다.",
  "code": "UNAUTHORIZED_USER"
}

```

#### Response (실패: 500 서버 오류)

```json
{
  "status": "error",
  "message": "서버 내부 오류가 발생했습니다.",
  "code": "INTERNAL_SERVER_ERROR"
}

```

---

## Member API

### 1) 내 정보 조회

* **Method**: `GET`
* **URL**: `/api/members/me`

#### Request Header

```text
Authorization: Bearer eyJhbGci0iJIUzI1NiIsInR5c...

```

#### Response (성공: 200)

```json
{
  "status": "success",
  "message": "내 정보 조회가 성공적으로 완료되었습니다.",
  "data": {
    "email": "user@example.com",
    "name": "홍길동",
    "nickname": "user-1"
  }
}

```

#### Response (실패: 401 토큰 없음/만료/위조)

```json
{
  "status": "error",
  "message": "유효하지 않은 토큰입니다. 다시 로그인해 주세요.",
  "code": "INVALID_TOKEN"
}

```

#### Response (실패: 500 서버 오류)

```json
{
  "status": "error",
  "message": "서버 내부 오류가 발생했습니다.",
  "code": "INTERNAL_SERVER_ERROR"
}

```

---

### 2) 로그아웃

* **Method**: `POST`
* **URL**: `/api/auth/logout`

#### Request Header

```text
Authorization: Bearer eyJhbGci0iJIUzI1NiIsInR5c...

```

#### Response (성공: 200)

```json
{
  "status": "success",
  "message": "성공적으로 로그아웃 되었습니다.",
  "data": null
}

```

#### Response (실패: 401 이미 만료/무효화)

```json
{
  "status": "error",
  "message": "유효하지 않은 토큰입니다.",
  "code": "INVALID_TOKEN"
}

```

#### Response (실패: 500 서버 오류)

```json
{
  "status": "error",
  "message": "서버 내부 오류가 발생했습니다.",
  "code": "INTERNAL_SERVER_ERROR"
}

```