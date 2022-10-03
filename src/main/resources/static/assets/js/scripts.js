//Navbar
const navbarMenu = document.getElementById("navbar");
const burgerMenu = document.getElementById("burger");
const overlayMenu = document.querySelector(".overlay");


const toggleMenu = () => {
   navbarMenu.classList.toggle("active");
   overlayMenu.classList.toggle("active");
};


const collapseSubMenu = () => {
   navbarMenu
      .querySelector(".menu-dropdown.active .submenu")
      .removeAttribute("style");
   navbarMenu.querySelector(".menu-dropdown.active").classList.remove("active");
};


const toggleSubMenu = (e) => {
   if (e.target.hasAttribute("data-toggle") && window.innerWidth <= 992) {
      e.preventDefault();
      const menuDropdown = e.target.parentElement;

      // If Dropdown is Expanded, then Collapse It
      if (menuDropdown.classList.contains("active")) {
         collapseSubMenu();
      } else {
         // Collapse Existing Expanded Dropdown
         if (navbarMenu.querySelector(".menu-dropdown.active")) {
            collapseSubMenu();
         }

         // Expanded the New Dropdown
         menuDropdown.classList.add("active");
         const subMenu = menuDropdown.querySelector(".submenu");
         subMenu.style.maxHeight = subMenu.scrollHeight + "px";
      }
   }
};


const resizeWindow = () => {
   if (window.innerWidth > 992) {
      if (navbarMenu.classList.contains("active")) {
         toggleMenu();
      }
      if (navbarMenu.querySelector(".menu-dropdown.active")) {
         collapseSubMenu();
      }
   }
};

burgerMenu.addEventListener("click", toggleMenu);
overlayMenu.addEventListener("click", toggleMenu);
navbarMenu.addEventListener("click", toggleSubMenu);
window.addEventListener("resize", resizeWindow);


//Effects Navbar
var myBanner = $("#img-banner");
var myNav = $("#header");

$(window).on('scroll', function() {
  "use strict";
  if ($(window).scrollTop() > 50) {
   myBanner.addClass("banner-top")
    myNav.addClass("header");
  } else {
   myBanner.removeClass("banner-top")
    myNav.removeClass("header");

  }
});


//Slider
document.addEventListener('DOMContentLoaded', () => {
   const slider = document.querySelector('#slider');
   setTimeout(function moveSlide() {
       const max = slider.scrollWidth - slider.clientWidth;
       const left = slider.clientWidth;

       if (max === slider.scrollLeft) {
           slider.scrollTo({left: 0, behavior: 'smooth'})
       } else {
           slider.scrollBy({left, behavior: 'smooth'})
       }

       setTimeout(moveSlide, 9000)
   }, 9000)

})


function count(){
   var counter = { var: 0 };
   TweenMax.to(counter, 3, {
     var: 583, 
     onUpdate: function () {
       var number = Math.ceil(counter.var);
       $('.counter').html(number);
       if(number === counter.var){ counter.kill(); }
     },
     onComplete: function(){
       count();
     },    
     ease:Circ.easeOut
     
     
   });
 }
 
 count();

 function years(){
   var counter = { var: 0 };
   TweenMax.to(counter, 3, {
     var: 3, 
     onUpdate: function () {
       var number = Math.ceil(counter.var);
       $('.years').html(number);
       if(number === counter.var){ years.kill(); }
     },
     onComplete: function(){
      years();
     },    
     ease:Circ.easeOut
   });
 }
 
 years();

 function people(){
   var counter = { var: 0 };
   TweenMax.to(counter, 3, {
     var: 643, 
     onUpdate: function () {
       var number = Math.ceil(counter.var);
       $('.people').html(number);
       if(number === counter.var){ people.kill(); }
     },
     onComplete: function(){
      people();
     },    
     ease:Circ.easeOut
   });
 }
 
 people();

 $(document).ready(function() {
   $('.subTables').hide();
   $('.open').on('click', function() {
     $(this).parent('div').parent('div').find('.subTables').slideToggle();
     //$(this).find('.glyphicon-chevron-down').toggleClass('glyphicon-chevron-up');
     if ($(this).find('i').hasClass('glyphicon-chevron-down')) {
       $(this).find('i').removeClass('glyphicon-chevron-down');
       $(this).find('i').addClass('glyphicon-chevron-up');
     } else {
       $(this).find('i').removeClass('glyphicon-chevron-up');
       $(this).find('i').addClass('glyphicon-chevron-down');
     }
   });

   $("#checkAll").change(function(){
     var status = $(this).is(":checked") ? true : false;
     $(".checkChange").prop("checked",status);
   });
 });

 //SIDEBAR
 // Burger menus
document.addEventListener('DOMContentLoaded', function() {
  // open
  const burger = document.querySelectorAll('.navbar-burger');
  const menu = document.querySelectorAll('.navbar-menu');

  if (burger.length && menu.length) {
      for (var i = 0; i < burger.length; i++) {
          burger[i].addEventListener('click', function() {
              for (var j = 0; j < menu.length; j++) {
                  menu[j].classList.toggle('hidden');
              }
          });
      }
  }

  // close
  const close = document.querySelectorAll('.navbar-close');
  const backdrop = document.querySelectorAll('.navbar-backdrop');

  if (close.length) {
      for (var i = 0; i < close.length; i++) {
          close[i].addEventListener('click', function() {
              for (var j = 0; j < menu.length; j++) {
                  menu[j].classList.toggle('hidden');
              }
          });
      }
  }

  if (backdrop.length) {
      for (var i = 0; i < backdrop.length; i++) {
          backdrop[i].addEventListener('click', function() {
              for (var j = 0; j < menu.length; j++) {
                  menu[j].classList.toggle('hidden');
              }
          });
      }
  }
});