import { getRow } from "./getRow.js";

export function generateBill(){
    const $printBtn = document.getElementById("generate-bill");
    const $checkbox = document.querySelectorAll(".input-bill-preview");
    const $billBody = document.getElementById("bill-body");
    const $totalBill = document.getElementById("total-bill");

    let typeSelector = document.getElementById("service-package-selector");
    let prefix;
    let total;
    
    let generatePaymentComision = (com) => {
        let per, 
            type,
            method;

        switch (com) {
            case "CREDIT":
                per = "9%";
                type = "Charge";
                method = "Debit";
                break;
            case "TRANSFER":
                per = "3%";
                type = "Charge";
                method = "Transference";   
                break;
            case "CASH":
                per = "-5%"
                type = "Discount";
                method = "Cash"
                break;
            default:
                per = "0%";
                break;
        }
            
        let tr = document.createElement("tr");
        tr.classList.add("tr-com");
        tr.innerHTML = ` 
            <td> -1 </td>
            <td>${type}</td>
            <td>${per} ${type}, payment method ${method}</td>
            <td id="total-pay-comision"></td>
        `;

        let payFactor = parseFloat(per.replace("%", ""));

        if(com != "DEBIT" && com != "VIRTUAL") return {tr, payFactor};
        else return {payFactor};
    }
   
    let generateData = () => {
        if(generatePaymentComision(document.getElementById("payment-select").value).tr) {
            $billBody.appendChild(generatePaymentComision(document.getElementById("payment-select").value).tr); 
        }
    }
    let clearData = () => {
        let arr = document.querySelectorAll(".tr-com"); 

        for(let i = 0; i < arr.length; i++){
            if(arr[i].classList.contains("tr-com")){
                $billBody.removeChild(arr[i]);
            }
        }  

        $totalBill.textContent = "";
    }
    let calculateTotal = () => {
        const payFact = generatePaymentComision(document.getElementById("payment-select").value).payFactor;
        const $totalpayCommission = document.getElementById("total-pay-comision");

        let pay;

        total = 0;
        
        for(let i = 0; i < $billBody.children.length; i++){
            if($billBody.children[i].hasAttribute("data-bill")){
                let strAmount = $billBody.children[i].lastElementChild.textContent.replace("$", "");
                let amount = parseFloat(strAmount);
                total += amount;
            }
        }
        
        pay = (total * payFact / 100);
        
        if($totalpayCommission) {
            $totalpayCommission.textContent = `$${pay.toFixed(2)}.-`;
        }

        $totalBill.textContent = `$${(total + pay).toFixed(2)}.-`;
    }
    let setPrefix = () => {
        if( typeSelector.value === "SERVICE") prefix = "S-";
        else prefix = "P-";
    }

    /* SETTING INITIAL PREFIX AS "P-" OR "S-" */
    setPrefix();
   
    $checkbox.forEach(chk => {

        chk.addEventListener("change", e => {

            if(chk.checked){
                /* CATCH THE ARRAY DATA */
                let rowData = getRow(chk, "data-row").children;

                /* CREATE THE ROW, SETTING DATA-BILL AS P-${ID} OR S-${ID} */
                let aux = document.createElement("tr");
                aux.setAttribute("data-bill", `${prefix}${rowData[0].textContent}` );

                /* FILLING THE CONTENT WITH THE DATA */
                aux.innerHTML = `
                    <td> ${rowData[0].textContent} </td>
                    <td> ${rowData[1].textContent} </td>
                    <td> ${rowData[2].textContent} </td>
                    <td> $${rowData[3].textContent}.- </td>
                `;

                /* CLEARING DATA TO UPGRADE THE CHANGES */
                clearData();
                
                /* APPENDING NEW ROW TO BILL PREVIEW */
                $billBody.appendChild(aux);

                /* WRITING UPGRADED DATA */
                generateData();
    
            }else{
                /* SETING ID(SEEKER) AS P-${ID} OR S-${ID} */
                let id = `${prefix}${getRow(chk, "data-row").children[0].textContent}`;
                
                /* COUNTER TO CHECK WHEN NO SERVICE OR PACKAGE ARE SELECTED */
                let count = 0;

                for(let i = 0; i < $billBody.children.length; i++){

                    /* SEARCHING MATCHES FOR EACH NODE, AND IF MATCH, REMOVING THEM FROM THE DOM */
                    if($billBody.children[i].getAttribute("data-bill") === id){
                        $billBody.removeChild($billBody.children[i]);
                    }

                    /* IF THERE IS A SERVICE OR PACKAGE, COUNT++ */
                    if($billBody.children[i].hasAttribute("data-bill")) count++;
                }  

                /* IF NO SERVICE OR PACKAGE SELECTED, THEN CLEAR ALL DATA*/
                if(count === 0) clearData();
            }

            /* CALC TOTAL, INCLUDING DISCOUNTS AND RECHARGES */
            calculateTotal();

        })
    })
    
    //load data to bill in edit mode
    $checkbox.forEach(chk => {

        if(chk.checked){
            /* CATCH THE ARRAY DATA */
            let rowData = getRow(chk, "data-row").children;

            /* CREATE THE ROW, SETTING DATA-BILL AS P-${ID} OR S-${ID} */
            let aux = document.createElement("tr");
            aux.setAttribute("data-bill", `${prefix}${rowData[0].textContent}` );

            /* FILLING THE CONTENT WITH THE DATA */
            aux.innerHTML = `
                <td> ${rowData[0].textContent} </td>
                <td> ${rowData[1].textContent} </td>
                <td> ${rowData[2].textContent} </td>
                <td> $${rowData[3].textContent}.- </td>
            `;

            /* CLEARING DATA TO UPGRADE THE CHANGES */
            clearData();

            /* APPENDING NEW ROW TO BILL PREVIEW */
            $billBody.appendChild(aux);

            /* WRITING UPGRADED DATA */
            generateData();

        }

        /* CALC TOTAL, INCLUDING DISCOUNTS AND RECHARGES */
        calculateTotal();
    })
    
    
    
    /* PAYMENT METHOD */
    document.addEventListener("change", e => {
        
        /* SETTING PREFIX AS "P-" OR "S-" */
        setPrefix();

        if(e.target.matches("#payment-select")){

            /* CLEARING DATA TO UPGRADE THE CHANGES */
            clearData();  

            /* WRITING UPGRADED DATA */
            generateData();

            /* CALC TOTAL, INCLUDING DISCOUNTS AND RECHARGES */
            calculateTotal();
        }
    })
    
    /* PRINT THE BILL */
    document.addEventListener("click", e => {
        if(e.target === $printBtn){
            window.print("")
        }
    })
}