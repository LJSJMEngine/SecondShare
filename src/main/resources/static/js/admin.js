$(function(){
    $("[name='userPageRows']").change(function(){
        $("[name='frmPageRows']").attr({
            "method": "POST",
            "action": "pageRows"
            }).submit();
    });
});

function checkAll(){
    if ($("input[name=allCheck]").is(':checked')) {
        $("input[name=selectedIds]").prop("checked", true);
    } else {
        $("input[name=selectedIds]").prop("checked", false);
    }
}
