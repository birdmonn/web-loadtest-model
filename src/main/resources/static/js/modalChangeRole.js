// Get the modal
var modal = document.getElementById('myModal');
var roleUserChange = document.getElementById('userRole');

var userId;

// When the user clicks the button, open the modal
function showModalConfirmChangeRole(sUserId) {
    modal.style.display = "block";
    userId=sUserId;
}

// When the user clicks on <span> (x), close the modal
function confirmChangeRole() {
    var roleChange = roleUserChange.options[roleUserChange.selectedIndex].value;
    reqChangeRole(userId,roleChange);
    modal.style.display = "none";
}

function cancel() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
