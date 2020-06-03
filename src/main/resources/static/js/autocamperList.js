var slider;
var output;

window.onload = function(){
	slider =  document.getElementById("slide");
	output = document.getElementById("price");
	var prices = document.getElementsByClassName("price");
	slider.max = 0;
	
	for(var i = 0; i < prices.length; i++){
		if(slider.max < prices[i].innerHTML){
			slider.max = Math.ceil(prices[i].innerHTML / 1000) * 1000;
		}
	}

};
// Update the current slider value (each time you drag the slider handle)
function update(){
	var filter = Math.ceil(slider.value/ 100) * 100;
	output.innerHTML = filter;
	var prices = document.getElementsByClassName("price");
	var items = document.getElementsByClassName("item");

	hide();
	for(var a = 0; a < prices.length; a++){
		if(prices[a].innerHTML < filter){
			for(var b = 0; b < items.length; b++){
				if(items[b].contains(prices[a])){
					items[b].style.display = "flex";
				}
			}
		}
	}
}
function brandUpdate(){
	checkbox = document.querySelectorAll('input[name="brand"]:checked');
	hide();
	for(var a = 0; a < checkbox.length; a++){
		var brand = document.getElementsByClassName(checkbox[a].value);
		if(brand != null){
			for(var b = 0; b < brand.length; b++){
				brand[b].style.display = "flex"
			}
		}
	}
}

function hide() {
	var items = document.getElementsByClassName("item");
	for (var i = 0; i < items.length; i++) {
		items[i].style.display = "none";
	}
}
function show() {
	var items = document.getElementsByClassName("item");
	for(var i = 0; i < items.length; i++){
		items[i].style.display = "flex";
	}
}