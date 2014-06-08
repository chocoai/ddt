$(function() {
	$("#save_check").on("click", function(){
		var _name = $("#name").val();
		if (_name == null || $.trim(_name) == '') {
			return;
		}
		
		var _start = $("#start_datepicker").val();
		if (_start == null || $.trim(_start) == '') {
			return;
		}
		
		var _end = $("#end_datepicker").val();
		if (_end == null || $.trim(_end) == '') {
			return;
		}
		
		var _name = $("#name").val();
		if (_name == null || $.trim(_name) == '') {
			return;
		}
		
		document.roll_book_form.submit();
	});
});