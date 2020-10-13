$(document).ready(function () {


  $("#login").click(function () {
    var form = $("#form1");
    form.show();
    form.animate({ width: '100%', left: '-75%' } , 2000);
    
  });

  $("#register").click(function () {
    var form = $("#form2");
    form.show();
    form.animate({ width: '100%', left: '-75%' } , 2000);
  
  });

  $(".return").click(function (){
    var form = $("#form2");
    
    form.animate({ left: '75%' } , 2000);
    form.fadeOut();

    form = $("#form1");

    form.animate({ left: '75%' } , 2000);
    form.fadeOut();
  })

  $(".register").click(function (){
    var form = $("#form2");
    
    form.animate({ left: '75%' } , 2000);
    form.fadeOut();

    form = $("#form1");
    form.show();
    form.animate({ width: '100%', left: '-75%' } , 2000);
  })



  /*Home jsp */

  $(".createTreatments").click(function(){
    $(".treatments").slideToggle(1000);
    $(".routines").slideUp(1000);
  });
  $(".createRoutines").click(function(){
    $(".routines").slideToggle(1000);
    $(".treatments").slideUp(1000);
  });
});



/*file jsp */

let lastScroll = $(window).scrollTop();

$(window).scroll(function() {
   const currentScroll = $(window).scrollTop(); 
   if (currentScroll > lastScroll){
      // scroll down
      $(".scrollupload").fadeIn(3000);
   } else {
      // scroll up
      $(".scrollupload").hide();
   }
   
   // scroll update
   lastScroll = currentScroll <= 0 ? 0 : currentScroll;
});
