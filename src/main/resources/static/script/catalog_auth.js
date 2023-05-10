var container = document.getElementById("container");

function func(event) {
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