// 마이페이지 - 동적 구현
function loadContent(action) {
    var url = '/mypage/' + action;
    fetch(url)
        .then(response => response.text())
        .then(data => {
            document.getElementById('content-container').innerHTML = data;
        })
        .catch(error => console.error('에러:', error));
}


// 마이페이지 - 회원 탈퇴 기능
function confirmDeleteAccount() {
    var confirmDelete = confirm("정말 회원 탈퇴를 진행하시겠습니까?");
    if (confirmDelete) {
        // 회원 탈퇴 요청 보내기
        deleteAccountRequest();
    }
}

function deleteAccountRequest() {
    var currentUsername = "USER1"; // 실제 사용자 아이디를 가져오도록 수정
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    // 서버로 회원 탈퇴 요청 보내기
    fetch('/mypage/deleteAccount', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            username: currentUsername
        })
    })
    .then(response => response.text())
    .then(result => {
        alert(result);
        // 회원 탈퇴가 성공적으로 이루어졌을 때 추가적으로 수행해야 하는 로직을 여기에 추가
        // 예: location.href = '/logout'; // 로그아웃 페이지로 이동
    })
    .catch(error => {
        console.error('에러:', error);
    });
}

// 마이페이지 - 프로필 수정 변경 버튼
function enableInput(fieldName) {
    var inputField = document.getElementById(fieldName);
    inputField.removeAttribute("readonly");
}

// 마이페이지 - 비밀번호 변경 기능
// 1) 비밀번호 변경 폼 생성 함수
function enablePasswordChangeForm() {
    document.getElementById("passwordChangeForm").style.display = "block";
    document.getElementById("password").readOnly = true;
}

// 2) 새로운 비밀번호 저장 함수
function saveNewPassword() {
    var newPassword = document.getElementById("newPassword").value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    // 비밀번호 유효성 검사 (원하는 규칙에 따라 구현)
    if (!isValidPassword(newPassword)) {
        displayPasswordMessages("비밀번호는 최소 8자 이상이어야 하며, 영문 대소문자, 숫자, 특수문자를 최소한 하나씩 포함해야 합니다.", null);
        return;
    }

    // 서버로 비밀번호 저장 요청 보내기
    var url = '/mypage/updatePassword';
    var data = {
        newPassword: newPassword
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        displayPasswordMessages(null, result);
    })
    .catch(error => {
        console.error('에러:', error);
    });
}

// 마이페이지 - 핸드폰 번호 변경 기능
// 1) 핸드폰 번호 변경 폼 생성 함수
function enablePhoneNMChangeForm() {
    document.getElementById("phoneNMChangeForm").style.display = "block";
    document.getElementById("phoneNM").readOnly = true;
}

// 2) 새로운 핸드폰 번호 저장 함수
function saveNewPhoneNumber() {
    var newPhoneNumber = document.getElementById("newPhoneNumber").value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    // 핸드폰 번호 유효성 검사 (원하는 규칙에 따라 구현)
    if (!isValidPhoneNumber(newPhoneNumber)) {
        displayPhoneNMMessages("유효하지 않은 핸드폰 번호 형식입니다. (예. 010-0000-0000)", null);
        return;
    }

    // 서버로 핸드폰 번호 저장 요청 보내기
    var url = '/mypage/updatePhoneNumber';
    var data = {
        newPhoneNumber: newPhoneNumber
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        displayPhoneNMMessages(null, result);
    })
    .catch(error => {
        console.error('에러:', error);
    });
}

// 마이페이지 - 이메일 변경 기능
// 1) 이메일 변경 폼 생성 함수
function enableEmailChangeForm() {
    document.getElementById("emailChangeForm").style.display = "block";
    document.getElementById("email").readOnly = true;
}

// 2) 새로운 이메일 저장 함수
function saveNewEmailAddress() {
    var newEmailAddress = document.getElementById("newEmailAddress").value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    // 이메일 유효성 검사 (원하는 규칙에 따라 구현)
    if (!isValidEmailAddress(newEmailAddress)) {
        displayEmailMessages("유효하지 않은 이메일 형식입니다. (예. example@gmail.com)", null);
        return;
    }

    // 서버로 이메일 저장 요청 보내기
    var url = '/mypage/updateEmailAddress';
    var data = {
        newEmailAddress: newEmailAddress
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        displayEmailMessages(null, result);
    })
    .catch(error => {
        console.error('에러:', error);
    });
}

// 마이페이지 - 공통 함수: 성공 및 실패 메시지 관리
function displayPasswordMessages(errorMessage, successMessage) {
    var errorElement = document.getElementById("passwordErrorMessage");

    // 에러 메시지 표시
    if (errorMessage) {
        errorElement.innerText = errorMessage;
    } else {
        errorElement.innerText = "";
    }

    // 성공 메시지 표시 (alert으로 변경)
    if (successMessage) {
        alert(successMessage);
    }
}

function displayPhoneNMMessages(errorMessage, successMessage) {
    var errorElement = document.getElementById("phoneNMErrorMessage");

    // 에러 메시지 표시
    if (errorMessage) {
        errorElement.innerText = errorMessage;
    } else {
        errorElement.innerText = "";
    }

    // 성공 메시지 표시 (alert으로 변경)
    if (successMessage) {
        alert(successMessage);
    }
}

function displayEmailMessages(errorMessage, successMessage) {
    var errorElement = document.getElementById("EmailErrorMessage");

    // 에러 메시지 표시
    if (errorMessage) {
        errorElement.innerText = errorMessage;
    } else {
        errorElement.innerText = "";
    }

    // 성공 메시지 표시 (alert으로 변경)
    if (successMessage) {
        alert(successMessage);
    }
}

// 마이페이지 - 비밀번호 유효성 검사 함수
function isValidPassword(password) {
    var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(password);
}

// 마이페이지 - 핸드폰 번호 유효성 검사 함수
function isValidPhoneNumber(phoneNumber) {
    var phoneRegex = /^\d{3}-\d{4}-\d{4}$/;
    return phoneRegex.test(phoneNumber);
}

// 마이페이지 - 이메일 유효성 검사 함수
function isValidEmailAddress(email) {
    var emailRegex = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]+$/;
    return emailRegex.test(email);
}

// 마이페이지 - 판매물품 전체 삭제
function confirmDeleteAllMyPosts() {
    var confirmDelete = confirm("정말 모든 판매글을 삭제하시겠습니까?");
    if (confirmDelete) {
        // 전체 삭제 요청 보내기
        deleteAllMyPostsRequest();
    }
}

function deleteAllMyPostsRequest() {
    var currentId = 2; // 2L 대신에 2를 사용
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");

    // 서버로 전체 삭제 요청 보내기
    fetch('/mypage/deleteAllMyPosts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            id: currentId,
        }),
    })
    .then(response => response.text())
    .then(result => {
        alert(result);
    })
    .catch(error => {
        console.error('에러:', error);
    });
}