const d = document;

export function filter(){
    const $rows = d.querySelectorAll("tr[data-row]");
    const $input = d.getElementById("form-add-service-search");
    const $input2 = d.getElementById("crud-filter");
    let filter = "";

    d.addEventListener("keyup", e => {  
        if (e.target === $input) filter = $input.value.toLowerCase();
        if (e.target === $input2) filter = $input2.value.toLowerCase();

        if (e.key === "Escape") filter = "";

        for(let i = 0; i<$rows.length; i++){
            let $childs = $rows[i].children;

            for(let j = 0; j<$childs.length; j++){
                if($childs[j].hasAttribute("data-filter")){

                    let txtFilter = $childs[j].textContent.toLowerCase();
                    
                    if(txtFilter.includes(filter)) {
                        $rows[i].classList.remove("filtered");
                        break;
                    }else $rows[i].classList.add("filtered");
                } 
            }
        }
    });

    d.addEventListener("search", e => {
        if(!e.target.matches("#form-add-service-search") && !e.target.matches("#crud-filter")) return;
        if(!e.target.value) {
            
            for(let i = 0; i<$rows.length; i++){
                $rows[i].classList.remove("filtered");
            }
        }
    });

    /* SERVICE / PACKAGE SELECTOR */
    let section = d.querySelector("[data-section]").dataset.section;
    
    if(section === "newSale"){
        let selector = document.getElementById("service-package-selector");
        let packageTable = document.querySelector("table[data-package]");
        let serviceTable = document.querySelector("table[data-service]");

        packageTable.classList.add("filtered");

        selector.addEventListener("change", e =>{
            if(e.target.value.toLowerCase() === "package"){

                serviceTable.classList.add("filtered");
                packageTable.classList.remove("filtered");
            }else{
                serviceTable.classList.remove("filtered");
                packageTable.classList.add("filtered");
            }
        });
    }
}