function loadContent(action) {
    var contentContainer = document.getElementById('content-container');
    if (!contentContainer) {
        console.error('content-container를 찾을 수 없습니다.');
        return;
    }

    var url = '/admin/' + action;
    fetch(url)
        .then(response => response.text())
        .then(data => {
            contentContainer.innerHTML = data;
        })
        .catch(error => console.error('에러:', error));
}


// 관리자페이지 - 내 판매글 삭제
function deleteSelectedPostIds() {
   var checkboxes = document.querySelectorAll('input[name="selectedPostIds"]:checked');
       var selectedPostIds = [];

       if (!checkboxes.length) {
           alert("변경할 항목을 선택해주세요.");
           return;
       }

       checkboxes.forEach(function (checkbox) {
           selectedPostIds.push(checkbox.value);
       });

       console.log("Selected PostIds:", selectedPostIds);

        console.log("Selected PostIds:", selectedPostIds);

    // Fetch 요청
    fetch("/admin/deletePosts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            selectedPostIds: selectedPostIds,
        }),
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
            loadContent('post');
            location.reload(true);
         })
        .catch(error => {
            console.error('Error:', error);
            alert("삭제를 실패했습니다.");
        });
}





function changeStatus() {
    var checkboxes = document.querySelectorAll('input[name="selectedPostIds"]:checked');
    var selectedStatus = document.getElementById('selectedStatus').value;

    var selectedPostIds = [];
    if (!checkboxes.length) {
        alert("변경할 항목을 선택해주세요.");
        return;
    }

    checkboxes.forEach(function (checkbox) {
        selectedPostIds.push(checkbox.value);
    });

    console.log("Selected PostIds:", selectedPostIds);
    console.log("Selected Status:", selectedStatus);

    // Fetch 요청
    fetch("/admin/chStatus", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            selectedPostIds: selectedPostIds,
            selectedStatus: selectedStatus,
        }),
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('상태 변경 실패');
        }
    })
    .then(data => {
        console.log(data);
        alert("상태 변경이 완료되었습니다.");
        loadContent('post');  // 필요에 따라 새로운 데이터 로딩 함수 호출
        location.reload(true);
    })
    .catch(error => {
        console.error('Error:', error);
        alert("상태 변경을 실패했습니다.");
    });
}








$(document).ready(function() {
    $.ajax({
        type: "POST",
        url: "/admin/PostsData",
        success: function(data) {
            // data를 이용하여 필요한 작업 수행
            console.log(data);
        },
        error: function(error) {
            console.error(error);
        }
    });
});