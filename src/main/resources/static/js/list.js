$(function(){
    // 페이징 헤더 동작
    $("[name='pageRows']").change(function(){
        const form = document.getElementById('search');


        $("[name='frmPageRows']").attr({
            "method": "POST",
            "action": "pageRows",
        }).submit();
    });

      $(".page-link").on("click", function(e) {
            e.preventDefault();
            const page = $(this).text();
            const form = document.getElementById('search');
            form.action = `${contextPath}/board/list`;
            form.method = 'GET';
            form.querySelector("[name='page']").value = page;
            form.submit();
        });
    });

});


  form.addEventListener('submit', (e) =>     {
        e.preventDefault();

        const searchTerm = search.value;
        selectedGenre=[];
        setGenre()
        if(search) {
            getMovies(searchURL+`&query=`+searchTerm)
        }else{
            getMovies(API_URL);
        }
    })







