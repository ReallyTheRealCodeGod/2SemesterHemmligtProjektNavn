var slider;
var output;

window.onload = function(){
	console.log("hello");
	slider =  document.getElementById("slide");
	output = document.getElementById("price");
	slider.max = 0;
	for(var i = 0; i < autocamperPrices.length; i++){
		if(slider.max < autocamperPrices[i]){
			slider.max = Math.ceil(autocamperPrices[i] / 1000) * 1000;
		}
	}

};
// Update the current slider value (each time you drag the slider handle)
function update(){
	var filter = Math.ceil(slider.value/ 100) * 100;
	output.innerHTML = filter;
	hide();
	for(var i = 0; i < autocamperPrices.length; i++){
		var element = document.getElementById("autocamper_"+i);
		if(autocamperPrices[i] < filter){
			element.style.display = "flex";
		}
	}
}
function brandUpdate(){
	checkbox = document.querySelectorAll('input[name="brand"]:checked');
	hide();
	for(var a = 0; a < checkbox.length; a++){
		for(var b = 0; b < autocamperBrand.length; b++) {
			var element = document.getElementById("autocamper_" + b);
			if(autocamperBrand[b] == checkbox[a].value) {
				element.style.display = "flex";
			}
		}
	}
}
function brandUpdate(){
	checkbox = document.querySelectorAll('input[name="brand"]:checked');
	hide();
	for(var a = 0; a < checkbox.length; a++){
		for(var b = 0; b < autocamperBrand.length; b++) {
			var element = document.getElementById("autocamper_" + b);
			if(autocamperBrand[b] == checkbox[a].value) {
				element.style.display = "flex";
			}
		}
	}
}

function hide() {
	for (var i = 0; i <= count; i++) {
		var element = document.getElementById("autocamper_" + i);
		element.style.display = "none";
	}
}