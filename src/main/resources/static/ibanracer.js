const plane = $('#plane');
const target = $('#target');
const score = $('#score');
let saldo = $('#balance');
let selectedAccount = null;
let allBankaccounts = null;

$(document).ready(function () {
        saldo.hide()
        getBankAccounts();
        $("#bankaccounts").change(function () {
            selected = $(this).children("option:selected").val();
            $.each(allBankaccounts, function (key, account) {
                if (account.accountName === selected) {
                    selectedAccount = account;
                    getBalanceFromBankaccount(account);
                    saldo.show();
                    return false
                } else {
                    saldo.hide()
                }
            })
        })
    }
);

let start = 3;
function resetTimer() {
    start = 3;
}

setInterval(function() {
    if (start === 0) {
        resetTimer();
        setPositionOfTarget();
    }
    $('.timer').html(start--);
}, 1000);

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

function incrementScore() {
    score.html(function (i, val) {
        return +val + 1
    })
}

function updateBalanceHtml(balance) {
    $("#balance > span").html(balance)
}

function getBalanceFromBankaccount(bankaccount) {
    $.ajax({
        url: "getBankAccountBalance",
        type: 'get',
        data: {
            id: bankaccount.id
        },
        success: function (response) {
            updateBalanceHtml(response.balance)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}

function setPositionOfTarget() {
    let newLeft = getRandomInt($(document).width() - 100);
    let newTop = getRandomInt($(document).height() - 100);
    target.css({left: newLeft, top: newTop})
}

function getBankAccounts() {
    $.ajax({
        type: 'post',
        contentType: "application/json",
        url: "getBankAccounts",
        success: function (data) {
            allBankaccounts = data.bankAccounts;
            $.each(allBankaccounts, function (key, account) {
                let name = account.accountName;
                let option = new Option(name, name);
                $(option).html(name);
                $("#bankaccounts").append(option)
            });
            selectedAccount = allBankaccounts[0]
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}

function increaseBalanceOfSelectedBankaccount() {
    $.ajax({
        type: "post",
        url: "increaseBalance",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(selectedAccount),
        success: function(data) {
            updateBalanceHtml(data.balance)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}

right = function () {
    plane.css({left: plane.position().left + 10})
}

left = function () {
    plane.css({left: plane.position().left - 10})
}

down = function () {
    plane.css({top: plane.position().top + 10})
}

up = function () {
    plane.css({top: plane.position().top - 10})
};

touch = function () {
    let planeTop = plane.position().top;
    let planeLeft = plane.position().left;
    let targetTop = target.position().top;
    let targetLeft = target.position().left;

    if (targetLeft - planeLeft < 40 &&
        targetLeft - planeLeft > -40 &&
        targetTop - planeTop < 40 &&
        targetTop - planeTop > -40) {
        setPositionOfTarget();
        incrementScore();
        increaseBalanceOfSelectedBankaccount();
        resetTimer();
    }
};

window.addEventListener("keydown", function (event) {

    switch (event.key) {
        case "ArrowDown":
            down();
            break;
        case "ArrowUp":
            up();
            break;
        case "ArrowLeft":
            left();
            break;
        case "ArrowRight":
            right();
            break;
    }
    event.preventDefault();
    touch()
}, true);