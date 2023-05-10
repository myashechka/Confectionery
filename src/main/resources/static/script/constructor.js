let inputBases = document.getElementsByClassName("bas");
let inputLevels = document.getElementsByClassName("cake_level");

let base_cont = document.getElementsByClassName("base_cont")[0];
let levels_cont = document.getElementsByClassName("yar_cont")[0];
let weight_cont = document.getElementsByClassName("weight")[0];

let res_p = document.getElementsByClassName("res_p")[0];

let price = 0;
let weight = document.getElementById("myRange").value;
let level = 0
let res = 0;

function get_res(){
    if (level !== 1) {
        res = price * weight * (level / 2 + 0.2)
    }else{
        res = price * weight
    }
    res_p.textContent = String(res);
    document.getElementById("order_price").value = res;
}

function get_weight(){
    weight = document.getElementById("myRange").value;
    get_res();
}

function get_price(){
    for(inp of inputBases){
        if(inp.checked){
            price = parseInt(inp.parentElement.lastElementChild.firstChild.textContent);
        }
    }
    get_res();
}

function get_level(){
    for(inp of inputLevels){
        if(inp.checked){
            level = parseInt(inp.value);
        }
    }
    get_res();
}


base_cont.onclick = get_price;
levels_cont.onclick = get_level;
weight_cont.onmouseup = get_weight;



var slider = document.getElementById("myRange");
var output = document.getElementById("demo");
output.innerHTML = slider.value;

slider.oninput = function() {
    output.innerHTML = this.value;
}