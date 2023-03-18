export function crudEditAdd(){
    let btns = document.querySelectorAll(".crud-edit");
    let forms = document.querySelector(".bg-span");

    btns.forEach(btn => {
    btn.addEventListener("click", e => {
        if(forms) forms.classList.add("visible");
        btn.blur();
        }) ;
    });

    document.addEventListener("click", e => {
        if(e.target.matches("#crud-add-btn") || e.target.matches("#crud-add-btn *")){
            forms.classList.add("visible");
            
            document.getElementById("crud-add-btn").blur();
        }
    });

    document.addEventListener("keydown", e => {
        if(document.querySelector(".bg-span")){
            if(document.querySelector(".bg-span").classList.contains("visible")){
                if(e.code === "Escape"){
                    document.querySelector(".bg-span").classList.remove("visible") ;
                }
            }
        }
    });
}