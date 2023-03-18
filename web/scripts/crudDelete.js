export function crudDelete(){
    let btns = document.querySelectorAll(".crud-delete");
    let servlet = document.getElementById("crud-title-h2").dataset.servlet;
    let id;
    let confirm;


    //RETURNS THE NODE WHO HAS THE CLASS "tag" IN THE WHOLE HIERARCHY
    let getId = function(el, tag)  {
        if (el.hasAttribute(tag)) return el;
        return getId(el.parentNode, tag);
    }

    btns.forEach(btn => {
        btn.addEventListener("click", e => {
            let $BGSpan = document.createElement("span"),
            $span = document.createElement("span");

            id = getId(btn, "data-id").getAttribute("data-id");

            $BGSpan.classList.add("bg-span-delete");

            $span.classList.add("delete-span");
            $span.innerHTML = `
                <h4>Are you sure?</h4>
                <div>
                    <form  action="${servlet}" method="POST">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${id}">
                        <button class="btn-delete-asd" id="delete-confirm-yes" type="submit">YES</button>
                    </form
                    <div >
                        <button class="btn-delete-asd" id="delete-confirm-no">NO</button>
                    </div>
                </div>
            `;
            
            $BGSpan.appendChild($span);
            document.querySelector("body").appendChild($BGSpan);
          
            btn.blur();
        })
    })

    document.addEventListener("click", e => {
        if(e.target === document.getElementById("delete-confirm-no")){
            confirm = false;
            document.querySelector("body").removeChild(document.querySelector(".bg-span-delete"))
        }


    })
    
    document.addEventListener("keydown", e => {
        if(document.querySelector(".bg-span-delete")){
            if(e.code === "Escape"){
                if(document.querySelector(".bg-span-delete")) confirm = false;
                document.querySelector("body").removeChild(document.querySelector(".bg-span-delete"))  
            }
            if(e.code === "Enter" || e.code === "NumpadEnter" || e.code === "Space"){
                if(e.code === "Space" && !document.querySelector(".bg-span-delete")) return;
                e.preventDefault();
                if(document.querySelector(".bg-span-delete")) confirm = true;
                document.querySelector("body").removeChild(document.querySelector(".bg-span-delete"))
            }
        }
    })
}