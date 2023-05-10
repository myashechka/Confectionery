var button = document.getElementsByClassName("btn_cart")[0];

button.onclick = function (event){
    event.preventDefault();
    var att = event.target.previousElementSibling;
    att.classList.toggle('att_active');
    setTimeout(() => {  att.classList.toggle("att_active"); }, 2000);
    return false;
}