$(function() {
	$("#btn-login").each(function() {
		$(this).click(function() {
			var _n = $("#uname").val();
			var _p = $("#upwd").val();

			if (_n == null || $.trim(_n) == "") {
				$("#error_log").html("请输入用户名");
				$("#error_log").css("display", "block");
				$("#username").focus();
				return;
			} else {
				$("#error_log").html("");
				$("#error_log").css("display", "none");
			}

			if (_p == null || $.trim(_p) == "") {
				$("#error_log").html("请输入密码");
				$("#error_log").css("display", "block");
				$("#password").focus();
				return;
			} else {
				$("#error_log").html("");
				$("#error_log").css("display", "none");
			}

			$.post("/login", {
				username : _n,
				password : _p
			}, function(data) {
				if (data.status == 1) {
					window.location.href = "/rollbook/list";
				} else {
					$("#error_log").html(data.result);
					$("#error_log").css("display", "block");
				}
			}, "json");
		});
	});
});
function checkForm() {
	$("#mail").blur(function() {
		if ($(this).val() == '') {
			$(this).error("请输入邮箱！");
			$(this).data("isMail", 0);
		} else if (!isMail($(this).val())) {
			$(this).error("邮箱格式错误！");
			$(this).data("isMail", 0);
		} else {
			$(this).error(false);
			$(this).data("isMail", 1);
		}
	});
	$("#password").blur(function() {
		if ($(this).val() == '') {
			$(this).error("请输入密码！");
		} else if (getStringLength($(this).val()) < 3) {
			$(this).error("密码长度小于3个字符！请输入3-20个字符作为密码！");
			$(this).data("isPassword", 0);
		} else if (getStringLength($(this).val()) > 20) {
			$(this).error("密码长度大于20个字符！请输入3-20个字符作为密码！");
			$(this).data("isPassword", 0);
		} else {
			$(this).error(false);
			$(this).data("isPassword", 1);
		}
		if ($(this).val() != $("#repassword").val()) {
			$("#repassword").data("isRePassword", 0);
		} else {
			$("#repassword").data("isRePassword", 1);
		}
	});
	$("#repassword").blur(function() {
		if ($(this).val() == '') {
			$(this).error("请输入确认密码！");
			$(this).data("isRePassword", 0);
		} else if ($(this).val() != $("#password").val()) {
			$(this).error("两次输入密码不一样！");
			$(this).data("isRePassword", 0);
		} else {
			$(this).error(false);
			$(this).data("isRePassword", "true");
		}
	});
	$("#companyName").blur(function() {
		if ($(this).val() == '') {
			$(this).error("请输入企业名称！");
			$(this).data("isCompanyName", 0);
		} else {
			$(this).error(false);
			$(this).data("isCompanyName", "true");
		}
	});
	$("#linkman").blur(function() {
		if ($(this).val() == '') {
			$(this).error("请输入联系人！");
			$(this).data("isLinkman", 0);
		} else {
			$(this).error(false);
			$(this).data("isLinkman", "true");
		}
	});
	$("#mobile").blur(function() {
		if ($(this).val() == '') {
			$(this).error("请输联系人手机！");
			$(this).data("isMobile", 0);
		} else if (!isMobile($(this).val())) {
			$(this).error("请输入有效的手机号码！");
			$(this).data("isMobile", 0);
		} else {
			$(this).error(false);
			$(this).data("isMobile", "true");
		}
	});
}
function isSubmit() {
	var isMail = $("#mail").data("isMail");
	var isPassword = $("#password").data("isPassword");
	var isRePassword = $("#repassword").data("isRePassword");
	var isCompanyName = $("#companyName").data("isCompanyName");
	var isLinkman = $("#linkman").data("isLinkman");
	var isMobile = $("#mobile").data("isMobile");
	var imgCode = $("#imgCode").val();
	var isAgree = document.getElementById("isAgree").checked;
	if (!Boolean(isMail)) {
		$("#mail").blur();
		return false;
	}

	if (!Boolean(isPassword)) {
		$("#password").blur();
		return false;
	}

	if (!Boolean(isRePassword)) {
		$("#repassword").blur();
		return false;
	}

	if (!Boolean(isCompanyName)) {
		$("#companyName").blur();
		return false;
	}

	if (!Boolean(isLinkman)) {
		$("#linkman").blur();
		return false;
	}

	if (!Boolean(isMobile)) {
		$("#mobile").blur();
		return false;
	}

	if (imgCode == null || imgCode == "") {
		$("#imgCode_error").html("请输入验证码！");
		$("#imgCode_error").attr("title", "请输入验证码！");
		$("#imgCode_error").css("display", "block");
		return false;
	} else {
		$("#imgCode_error").fadeOut("请输入验证码！");
	}

	if (!isAgree) {
		$("#service_error").html("请同意服务条款！");
		$("#service_error").attr("title", "请同意服务条款！");
		$("#service_error").css("display", "block");
		return false;
	} else {
		$("#service_error").fadeOut("请同意服务条款！");
	}

	return true;
}



