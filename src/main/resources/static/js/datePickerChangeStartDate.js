$(document).ready(function () {

    $("#targetStartDate").datepicker({
//        dateFormat: 'dd/mm/yyyy',
        startDate: new Date(),
        onSelect: function () {
            var dt2 = $('#targetFinishDate');
            var startDate = $(this).datepicker('getDate');
            var minDate = $(this).datepicker('getDate');
//            dt2.datepicker('setDate', minDate);
            startDate.setDate(startDate.getDate());
            //sets dt2 maxDate to the last day of 30 days window
            $('#testF').in = startDate;
            dt2.datepicker("setEndDate", startDate);
            dt2.datepicker({setStartDate : new Date()});
//            $(this).datepicker('option', 'minDate', minDate);
        }
    });
    $('#targetFinishDate').datepicker({
        startDate: new Date(),
    });
});
