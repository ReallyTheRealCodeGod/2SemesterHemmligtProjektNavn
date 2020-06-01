function show(name){
    var a = document.getElementsByClassName(name);
    var button = document.getElementById(name);

    for(var i = 0; i < a.length; i++){
        a[i].disabled = !a[i].disabled;
    }
    if(button.innerHTML === "Gem"){
        document.getElementById("form").submit();
    } else{button.innerHTML = "Gem";}
}