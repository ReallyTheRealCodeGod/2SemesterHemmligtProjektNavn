window.onload = function(){
    console.log("hello");
};

function show(name, button){
    var a = document.getElementsByClassName(name);
    console.log(a);
    console.log(" and ");
    console.log(button);
    for(var i = 0; i < a.length; i++){
        a[i].disabled = false;
    }
    button.text = "Gem";
    //button.type = "submit";
}