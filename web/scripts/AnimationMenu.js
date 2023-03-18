const breakpoint = 1200;

export function AnimationMenu(){
    const $hamb = document.getElementById("hamb-menu-box")

    //CROSS ANIMATION
    let toCross = gsap.timeline({paused: true})
        .to(".hamb-menu-top",{duration: .5, opacity:0, y:"-100%"}, 0)
        .to(".hamb-menu-middle-top",{duration: .5, transformOrigin: "center", rotation: "45", width: "130%", x: "-10%"}, 0)
        .to(".hamb-menu-middle-bottom",{duration: .5 , transformOrigin: "center", rotation: "-45", width: "130%", x: "-10%"}, 0)
        .to(".hamb-menu-bottom",{duration: .5, opacity:0, y: "100%"}, 0);
    
    //MENU OPEN ANIMATION
    let toOpen = gsap.timeline({paused:true})
        .to(".aside", { opacity: 1, x: "0", duration :.4})
        

    //THOSE VALUES NEED TO BE RESET WHEN USER SWITCH TO A DIFERENT SECTION

    document.addEventListener("click", e => {
        if(e.target.matches("#hamb-menu-box") || e.target.matches("#hamb-menu-box *")){
            if(!$hamb.classList.contains("isOpen")){ 
                toCross.play();
                toOpen.play();
            }else{
                toCross.reverse();
                toOpen.reverse();
            }
            $hamb.classList.toggle("isOpen")
        }
    })

    //RESET AL VALUES TO ORIGINALS
    const reset = () => {
        toCross.reverse();
        
        toOpen.reverse();
        toOpen.revert();

        document.querySelector("#hamb-menu-box").classList.remove("isOpen");
    }

    window.addEventListener("resize", e => {
        if(window.innerWidth < breakpoint) reset();
        else reset();
    })   

    document.addEventListener("keydown", e => {
        if(e.code === "Escape" && $hamb.classList.contains("isOpen")) {
            toCross.reverse();
            toOpen.reverse();
            $hamb.classList.toggle("isOpen")
        }
    });
}