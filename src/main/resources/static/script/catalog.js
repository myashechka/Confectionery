var container = document.getElementById("container");

var buttons = document.getElementsByClassName("btn_cart");

function func(event) {
    if(event.target.className === "btn_cart"){
        event.preventDefault();
        var att = event.target.parentElement.nextElementSibling;
        att.classList.toggle('att_active');
        setTimeout(() => {  att.classList.toggle("att_active"); }, 2000);
        return false;
    }

    if (event.target.className != 'add' && event.target.className != 'subtract'){return};

    let amount = event.target.closest('.amount');
    let p = amount.children[1];
    let a = amount.nextElementSibling;
    let item = parseInt(p.textContent.split()[0])
    console.log(a);

    if (event.target.className == 'add'){
        item += 1;
    }else{
        if (parseInt(p.textContent.split()[0]) === 1) {
            return;
        }
        item -= 1
    }
    p.textContent = String(item) + " шт.";
    let index = a.href.lastIndexOf('=');
    let address = a.href.substring(0, index + 1);
    a.setAttribute('href', address + String(item));

}

container.onclick = func;