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
    // 현재 로그인된 사용자 아이디 가져오기
    var currentUsernameElement = document.getElementById("username");
    var currentUsername = currentUsernameElement ? currentUsernameElement.value : null;

    /*var currentUsername = "USER1";
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");*/

    // 서버로 회원 탈퇴 요청 보내기
    fetch('/mypage/deleteAccount', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            /*'X-CSRF-TOKEN': csrfToken*/
        },
        body: JSON.stringify({
            username: currentUsername
        })
    })
    .then(response => response.text())
    .then(result => {
        alert(result);
        location.href = '/user/logout';
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
    var currentUsername = document.getElementById("username").getAttribute("data-username");

    /*var newPassword = document.getElementById("newPassword").value;
    var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");*/

    // 비밀번호 유효성 검사 (원하는 규칙에 따라 구현)
    if (!isValidPassword(newPassword)) {
        displayPasswordMessages("비밀번호는 문자, 숫자, 특수문자의 조합으로 8자 이상 22자 이하로 입력해주세요.", null);
        return;
    }

    // 서버로 비밀번호 저장 요청 보내기
    var url = '/mypage/updatePassword';
    var data = {
        newPassword: newPassword,
        username: currentUsername
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            /*'X-CSRF-TOKEN': csrfToken*/
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        displayPasswordMessages(null, result);
        document.getElementById("passwordChangeForm").style.display = "none";
        var passwordElement = document.getElementById("password");
        passwordElement.value = newPassword;
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
    var currentUsername = document.getElementById("username").getAttribute("data-username");

    /*var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");*/

    // 핸드폰 번호 유효성 검사 (원하는 규칙에 따라 구현)
    if (!isValidPhoneNumber(newPhoneNumber)) {
        displayPhoneNMMessages("유효하지 않은 핸드폰 번호 형식입니다. (예. 010-0000-0000)", null);
        return;
    }

    // 서버로 핸드폰 번호 저장 요청 보내기
    var url = '/mypage/updatePhoneNumber';
    var data = {
        newPhoneNumber: newPhoneNumber,
        username: currentUsername
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            /*'X-CSRF-TOKEN': csrfToken*/
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        displayPhoneNMMessages(null, result);
        document.getElementById("phoneNMChangeForm").style.display = "none";
        var phoneNMElement = document.getElementById("phoneNM");
        phoneNMElement.value = newPhoneNumber;
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

function sendConfirmationCode() {
    var newEmailAddress = document.getElementById("newEmailAddress").value;

    // 이메일 유효성 검사 (원하는 규칙에 따라 구현)
    if (!isValidEmailAddress(newEmailAddress)) {
        displayEmailMessages("유효하지 않은 이메일 형식입니다. (예. example@gmail.com)", null);
        return;
    }

    // 서버로 확인 코드 전송 요청 보내기
    var url = '/mypage/sendConfirmationCode';
    var data = {
        newEmailAddress: newEmailAddress
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        displayEmailMessages(null, result);
        // 저장해 둔 이메일 주소 업데이트
        window.newEmailAddress = newEmailAddress;
        document.getElementById("emailChangeForm").style.display = "none";
        document.getElementById("confirmationCodeForm").style.display = "block";
    })
    .catch(error => {
        // 실패 메시지를 에러 메시지로 변경
        displayEmailMessages(ConfirmationCodeErrorMessage, null);

        // 사용자가 잘못 적은 인증번호를 지우고 커서를 폼에 다시 올림
        document.getElementById("confirmationCode").value = "";
        document.getElementById("confirmationCode").focus();
    });
}

// 3) 인증 확인 함수
function verifyEmailAddress() {
    var confirmationCode = document.getElementById("confirmationCode").value;

    // 서버로 확인 코드 검증 요청 보내기
    var url = '/mypage/verifyEmailAddress';
    var data = {
        confirmationCode: confirmationCode,
        newEmailAddress: window.newEmailAddress // 저장해 둔 이메일 주소 사용
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            return response.text().then(error => Promise.reject(error));
        }
    })
    .then(result => {
        displayEmailMessages(null, result);
        document.getElementById("ConfirmationCodeErrorMessage").innerText = ""; // 성공 시 에러 메시지 초기화
        document.getElementById("confirmationCodeForm").style.display = "none";
        var emailAddressElement = document.getElementById("email");
        emailAddressElement.value = window.newEmailAddress; // 저장해 둔 이메일 주소 사용
    })
    .catch(error => {
        console.error('에러:', error);

        // 실패 메시지를 에러 메시지로 변경
        displayEmailMessages(null, null);
        document.getElementById("ConfirmationCodeErrorMessage").innerText = error;

        // 사용자가 잘못 적은 인증번호를 지우고 커서를 폼에 다시 올림
        document.getElementById("confirmationCode").value = "";
        document.getElementById("confirmationCode").focus();
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
    var passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,22}$/;
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
/*function confirmDeleteAllMyPosts() {
    var confirmDelete = confirm("정말 모든 판매글을 삭제하시겠습니까?");
    if (confirmDelete) {
        // 전체 삭제 요청 보내기
        deleteAllMyPostsRequest();
    }
}*/

/*
function deleteSelectedPosts() {
    var selectedPostIds = [];
    $('.postCheckbox:checked').each(function () {
        selectedPostIds.push($(this).attr('data-post-id'));
    });
    return selectedPostIds;
}

// 체크박스가 변경될 때마다 실행
$('.postCheckbox').on('change', function() {
    var selectedIds = deleteSelectedPosts();
    console.log(selectedIds);
});
*/





    /*// 선택한 게시물의 ID를 담을 배열
    var selectedPostIds = [];

    // 체크된 체크박스를 찾아서 ID를 배열에 추가
    $('.postCheckbox:checked').each(function () {
        var postId = $(this).val();
        selectedPostIds.push(Number(postId));
    });*/

/*    // 각 체크박스에서 data-postid 값을 읽어와 배열에 추가
    $('.postCheckbox:checked').each(function () {
        var postId = $(this).data('postid');
        selectedPostIds.push(Number(postId));
    });*/

    // Fetch API를 사용하여 서버로 데이터 전송
    /*fetch('/mypage/deleteSelectedPosts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedPostIds)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP 오류! 상태: ${response.status}`);
        }
        return response.text();
    })
    .then(data => {
        // 삭제 성공한 경우
        alert("판매글이 성공적으로 삭제되었습니다.");
    })
    .catch(error => {
        // 삭제 실패한 경우
        alert("판매글 삭제 중 오류가 발생했습니다.");
    });*/
/*}*/

function deleteSelectedPosts() {
    var selectedPosts = [];
    document.querySelectorAll('input[name="selectedPosts"]:checked').forEach(function (checkbox) {
        selectedPosts.push(checkbox.value);
    });

    console.log("Selected Posts:", selectedPosts);

    if (selectedPosts.length === 0) {
        alert("삭제할 항목을 선택해주세요.");
        return;
    }

    // Fetch 요청
    fetch("/mypage/deleteMyPosts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ selectedPosts: selectedPosts }),
    })
    .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('삭제 실패');
            }
        })
        .then(data => {
            console.log(data);
            alert("삭제가 완료되었습니다.");
            loadContent('myPosts');
        })
        .catch(error => {
            console.error('Error:', error);
            alert("삭제 실패했습니다.");
        });
}