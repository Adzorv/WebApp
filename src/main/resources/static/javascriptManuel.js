let field_username = document.querySelector("#field_username")
let login_error_field = document.querySelector(".usernameError")
let url = 'usernameCheck'

let tryUsername = function() {
    const data = { username: field_username.value }
    fetch(url,
        {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        }
    ).then(response => response.json()).then(
        data => {
            login_error_field.innerHTML = data.error

        }
    ).catch(error => console.log(error))
}

field_username.addEventListener('focusout', function() {
    tryUsername();
})