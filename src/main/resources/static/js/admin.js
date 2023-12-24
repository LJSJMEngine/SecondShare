$(function(){
    $("[name='userPageRows']").change(function(){
        $("[name='frmPageRows']").attr({
            "method": "POST",
            "action": "userPageRows"
            }).submit();
    });
});

function checkAll(){
    if ($("input[name=allCheck]").is(':checked')) {
        $("input[name=selectedUserIds]").prop("checked", true);
    } else {
        $("input[name=selectedUserIds]").prop("checked", false);
    }
}
