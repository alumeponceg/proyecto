$(document).ready(function(){

    $("#form1").css({"height":$(window).height()+ "px"});
    $("#form2").css({"height":$(window).height()+ "px"});
    $(".indexbody").css({"height":$(window).height()+ "px"});
    
    if($(".body").height()<= $(window).height()){
    	 $(".body").css({"height":$(window).height()+ "px"});
    }
    if($(".routine").height()<= $(window).height()){
   	 $(".routine").css({"height":$(window).height()+ "px"});
   }
//    $(".routine").css({"height":$(window).height()+ "px"});
    $(".measurements").css({"height":$(window).height()+ "px"});
    $(".appointment").css({"height":$(window).height()+ "px"});
    $(".proximalDate").css({"height":$(".calendarjs").height()+ "px"});
    $(".addDate").css({"height":$(".calendarjs").height()+ "px"});

});