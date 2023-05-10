var cart = document.getElementsByClassName("cart")[0];
var btn_cart = document.getElementsByClassName("btn_cart")[0];
var div_result = document.getElementsByClassName("result")[0];
var att = document.getElementsByClassName("att")[0];

console.log(div_result)
console.log(div_result.parentElement.children[0])
if (cart.children.length === 2){
    att.classList.remove("hidden");
    div_result.classList.add("hidden");
    btn_cart.classList.add("hidden");
}else{
    att.classList.add("hidden");
    div_result.classList.remove("hidden");
    btn_cart.classList.remove("hidden");
}


function func(event) {
    if(event.target.className == 'fa-solid fa-xmark'){
        let record = event.target.closest('.cart_record');
        record.remove();
    }else{return};
}
function price(){
    let p_res = document.getElementsByClassName('res_price');
    let p_one = document.getElementsByClassName('one_price');
    let amount = document.getElementsByClassName('p_amount');
    let summ = 0;

    for (let i = 0; i < p_res.length; i++) {
        let index_p_one = p_one[i].textContent.lastIndexOf(' ');
        let index_amount = amount[i].textContent.lastIndexOf(' ');
        let price_one = parseInt(p_one[i].textContent.substring(0, index_p_one));
        let am = parseInt(amount[i].textContent.substring(0, index_amount));
        p_res[i].textContent = String(price_one * am) + ' руб.';
        summ += price_one * am;
    }

    let result = document.getElementById('result');
    result.textContent = String(summ) + ' руб.';
}
cart.onclick = func;
price();