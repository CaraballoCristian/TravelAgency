import { AnimationMenu } from "./AnimationMenu.js";
import { validateForms } from "./validateForms.js";
import { generateBill } from "./generateBill.js";
import { crudEditAdd } from "./crudEditAdd.js";
import { crudDelete } from "./crudDelete.js";
import { filter } from "./filter.js";

document.addEventListener("DOMContentLoaded", e => {
    let section = document.querySelector("[data-section]").dataset.section;
    
    AnimationMenu();
    validateForms();    
    filter();
    
    if(section !== "earns" && section !== "newSale"){
        crudEditAdd();
        crudDelete();
    }
    
    if(section === "newSale") generateBill();

});


