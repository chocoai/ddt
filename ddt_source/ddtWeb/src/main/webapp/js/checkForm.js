$.fn.error=function(str){
	if(str){
		$(this).next(".error").fadeIn("slow");
		$(this).next(".error").html(str);
		$(this).next(".error").attr("title",str);
	}else{
		$(this).next(".error").fadeOut(str);
	}
};
function isMail(mail){
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(reg.test(mail)){
	 	return true;
	}else{
		return false;
	}
}
function getStringLength(str) {
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
}
function isMobile(mobile){
	var myreg = /^1[3|5|8]\d{9}$/;
	if(getStringLength(mobile)!=11){
		return false;
	}else if(!myreg.test(mobile)){
		return false;
	}else{
		return true;
	}
}

function isTelephone(telephone) {
	var exp = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	if (exp.test(telephone)) {
		return true;
	}
	return false;
}

function isNumber(number) {
	var exp = /^[1-9]\d*$/;
	if (exp.test(number)) {
		return true;
	}
	return false;
}

function isDecimal(value) {
	var exp = /^\d+\.\d{1,2}$/;
	if (exp.test(value)) {
		return true;
	}
	return false;
}