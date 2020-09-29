var leaveStreetinputEvent = document.getElementById("housenumber").addEventListener('blur', getStreetFromSite);
function getStreetFromSite(){
    var postcode = document.getElementById("postcode").value;
    var huisnummer = document.getElementById("housenumber").value;
    const url = `https://postcode.tech/api/v1/postcode?postcode=${postcode}&number=${huisnummer}`;
    console.log(url)
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.setRequestHeader('Authorization', 'Bearer 05f0f3b1-dc8c-494d-aa48-449b5c906bfd')
    request.setRequestHeader('Content-Type', 'application/json'); // x-www-urlencoded
    request.setRequestHeader('Accept', 'application/json');
    request.send();

    request.onreadystatechange = function() {
        if(request.readyState === XMLHttpRequest.DONE){
            if(request.status === 200){
                console.log(request.response);
                var responseObject = JSON.parse(request.response);
                var street = responseObject.street;
                console.log(street);
                deleteError()
                document.getElementById("street").value = responseObject.street;
                document.getElementById("city").value = responseObject.city;
            } else {
                console.log(request.status + " " + request.statusText);
                deleteError();
                showError("combinatie van postcode/huisnummer is onbekend.")
            }
        }
    }
}


function findIndexOfRowInTableById(id, tableId){
    const rows = document.getElementById(tableId).rows;
    for (let i = 0; i < rows.length; i++){
        if (rows[i].id == id){
            return i;
        }
    }
}

function deleteError(){
    let indexToDelete = findIndexOfRowInTableById("error", "registrationtable");
    console.log(typeof indexToDelete)
    if (typeof indexToDelete == "number"){
    document.getElementById("registrationtable").deleteRow(indexToDelete);
    }
}

function showError(errortext){
    const table = document.getElementById("registrationtable");
    let indexRowBeforeError = findIndexOfRowInTableById("housenumberRow", "registrationtable")
    var row = table.insertRow(indexRowBeforeError+1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell2.innerHTML = errortext
    row.setAttribute("id", "error");
    cell2.setAttribute("class", "error");
}





