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







