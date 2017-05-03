$(document).on("show.bs.modal", "#myModal", function (e) {
    $(this).find('.modal-body').html('Fired By: ' + e.relatedTarget.id);
});