$(function(){
    var i = 0;
    $("#btnAdd").click(function(){
        $("#files").append(`
            <div>
                <input class="form-control col-xs-3" type="file" name="upfile${i}"/>
                <button class="btn btn-outline-danger" type="button" onclick="$(this).parent().remove()">삭제</button>
            </div>`);
    i++;
    });

        $("[date-fileid-del]").click(function(){
            let fileId = $(this).attr('date-fileid-del');
            deleteFiles(fileId);
            $(this).parent().remove();
        });

    $("#contents").summernote({
        height: 300,
    });
});

function deleteFiles(fileId){
    $("#delFiles").append(`<input type='hidden' name='delfile' value='${fileId}'>`);
}