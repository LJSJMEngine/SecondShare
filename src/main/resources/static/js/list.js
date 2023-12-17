$(function(){
    // 페이징 헤더 동작
    $("[name='pageRows']").change(function(){
        const form = document.getElementById('searchForm');


        $("[name='frmPageRows']").attr({
            "method": "POST",
            "action": "pageRows",
        }).submit();
    });
});


  form.addEventListener('submit', (e) => {
        e.preventDefault();

        const searchTerm = search.value;
        selectedGenre=[];
        setGenre()
        if(searchTerm) {
            getMovies(searchURL+`&query=`+searchTerm)
        }else{
            getMovies(API_URL);
        }
    })

      function movePage(page) {

            // 1. 검색 폼
            const form = document.getElementById('searchForm');

            // 2. drawPage( )의 각 버튼에 선언된 onclick 이벤트를 통해 전달받는 page(페이지 번호)를 기준으로 객체 생성
            const queryParams = {
                page: (page) ? page : 1,
                recordSize: 10,
                pageSize: 10,
                searchType: form.searchType.value,
                keyword: form.keyword.value
            }

            /*
             * 3. location.pathname : 리스트 페이지의 URI("/post/list.do")를 의미
             *    new URLSearchParams(queryParams).toString() : queryParams의 모든 프로퍼티(key-value)를 쿼리 스트링으로 변환
             *    URI + 쿼리 스트링에 해당하는 주소로 이동
             *    (해당 함수가 리턴해주는 값을 브라우저 콘솔(console)에 찍어보시면 쉽게 이해하실 수 있습니다.)
             */
            location.href = location.pathname + '?' + new URLSearchParams(queryParams).toString();
        }





