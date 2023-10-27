/* Truyền dữ liệu từ usersEdit vào input password dưới dạng mã hóa kí tự */
window.addEventListener('DOMContentLoaded', function () {
    const passwordHidden = document.getElementById('passwordHidden');
    const passwordInput = document.getElementById('passwords');
    passwordInput.value = passwordHidden.value;
});

/* Ẩn hiện mật khẩu */
let checkPass = true;

function check() {
    if (checkPass) {
        document.getElementById('passwords').type = "text"
        checkPass = false;
    } else {
        document.getElementById('passwords').type = "password"
        checkPass = true;
    }
}

/* Hiển thị số 0 trước số điện thoại */
let input = document.getElementById('numberPhone').value
if (input.value != null) {
    document.getElementById('numberPhone').value = ""
} else {
    document.getElementById('numberPhone').value = "0" + input
}
