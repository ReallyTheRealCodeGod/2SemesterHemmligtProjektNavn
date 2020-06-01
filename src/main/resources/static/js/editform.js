function show(name){
    var a = document.getElementsByClassName(name);
    var edit = document.getElementById(name);
    var save = document.getElementById(name+"Save");

    for(var i = 0; i < a.length; i++){
        a[i].disabled =  !a[i].disabled;
    }

    if(a[0].disabled){
        edit.style.display="inline-block";
        save.style.display="none";
    }else{
        edit.style.display="none";
        save.style.display="inline-block";
    }



}