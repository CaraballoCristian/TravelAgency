export function validateForms(){
    const $inputs = document.querySelectorAll("[required]");
    
    
    const regexPatterns = {
        name: /^[a-z\s\d]{4,15}$/i,
        lastname: /^[a-z\s]{4,15}$/i,
        dni: /^[\d]{8,9}$/i,
        phone:/^[\d\s\+()]{8,20}$/i,
        email: /^([a-z_\d\.-]+)@([a-z\d-]+)\.([a-z]{2,8})(\.[a-z]{2,8})?$/i,
        nationality: /^[a-z\s]{4,15}$/i,
        description: /^[\s\S]{1,255}$/i,
        address: /^[\s\S]{1,255}$/i,
        
        birthdate: /^[0-9]{2}-[0-9]{2}-[0-9]{4}$/i,
        hireDate: /^[0-9]{2}-[0-9]{2}-[0-9]{4}$/i,
        serviceDate: /^[0-9]{2}-[0-9]{2}-[0-9]{4}$/i,
        
        username: /^[a-z_\d\.-]{4,8}$/i,
        password: /^[\s\S]{4,30}$/i,
        
        salary: /^([\d]{2,8})\.?([\d]{1,2})?$/i,
        price: /^([\d]{2,8})\.?([\d]{1,2})?$/i,
    }
    
    
    $inputs.forEach(input => {
        input.addEventListener("keyup", e => {
            const pattern = regexPatterns[e.target.getAttribute("name")];
            
            if(pattern.test(input.value)){
                e.target.classList.add("form-valid");
                e.target.classList.remove("form-invalid");
                
            }else{
                e.target.classList.add("form-invalid");
                e.target.classList.remove("form-valid");

            };
            
            if(e.target.value === ""){
                e.target.classList.remove("form-invalid");
                e.target.classList.remove("form-valid");
                
            }
        });
    });

}

