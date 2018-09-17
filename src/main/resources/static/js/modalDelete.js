// Get the modal
var modal = document.getElementById('myModal');

var fileId;
var requestFormId;

// When the user clicks the button, open the modal
function showModalDelete(sFileId,sRequestFormId) {
    modal.style.display = "block";
    fileId=sFileId;
    requestFormId=sRequestFormId;
}

// When the user clicks on <span> (x), close the modal
function confirmDelete() {
    reqDelete(fileId,requestFormId);
    modal.style.display = "none";
}

function cancelDelete() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
